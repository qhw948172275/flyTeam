package com.cdyykj.xzhk.service;

import com.cdyykj.commons.task.ArticleJob;
import com.cdyykj.commons.task.JobUtils;
import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.system.entity.SystemUser;
import com.cdyykj.xzhk.dao.ArticleMapper;
import com.cdyykj.xzhk.entity.AnnexManage;
import com.cdyykj.xzhk.entity.Article;
import com.cdyykj.xzhk.entity.MemberArticle;
import com.cdyykj.xzhk.entity.RAnnexApply;
import com.cdyykj.xzhk.response.ArticleMemberResponse;
import com.cdyykj.xzhk.response.ArticleResponse;
import com.cdyykj.xzhk.response.MemberResponse;
import com.cdyykj.xzhk.tool.WxCpServiceTool;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService extends AbstractBaseCrudService<Article,Integer> {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    WxCpServiceTool wxCpServiceTool;
    @Autowired
    JobUtils jobUtils;

    /**
     * 关键字搜索文章
     * @param keyword
     * @param dateStr
     * @return
     */
    public List<Article> selectByKeyword(String keyword, String dateStr) {
       return articleMapper.selectByKeyword(keyword,dateStr);
    }

    /**
     * 更新文章发布状态
     * @param article
     * @param fireTime
     */
    public void updateSendTime(Article article, Date fireTime) {
        article.setSendStatus((byte)1);//1表示发布
        article.setUpdateTime(fireTime);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    /**
     * 根据文章ID查询文章内容
     * @param articleId
     * @return
     */
    public Article selectArticleByArticleId(Integer articleId) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("id",articleId);
        return mapper.selectOneByExample(example);
    }

    /**
     * 根据成员ID获取文章列表
     * @param memberId
     * @return
     */
    public List<ArticleResponse> selectArticleResponse(Integer memberId) {
        return articleMapper.selectArticleResponse(memberId);
    }

    /**
     * 根据文章ID查询详情
     * @param articleId
     * @return
     */
    public ArticleMemberResponse selectArticleMemberResponse(Integer articleId) {
        return articleMapper.selectArticleMemberResponse(articleId);
    }

    /**
     * 保存文章
     * @param memberArticleService
     * @param rAnnexApplyService
     * @param articleMemberResponse
     */
    @Transactional
    public ObjectNode save(MemberArticleService memberArticleService, RAnnexApplyService rAnnexApplyService,
                         ArticleMemberResponse articleMemberResponse, SystemUser systemUser) {
        ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
        try {
            Article article=new Article();
            BeanUtils.copyProperties(articleMemberResponse,article);
            article.setCreateTime(CalendarUtils.getDate());
            article.setCreateId(systemUser.getId());
            article.setStatus((byte)0);
            article.setReceiveNumber(articleMemberResponse.getMemberResponses().size());//应接收人数

            if(article.getSendStatus().intValue()==1&&article.getSendType().intValue()==0){//直接发布
                article.setSendTime(new Date());
            }
            this.insert(article);
            articleMemberResponse.setId(article.getId());
            if(article.getSendType().intValue()==1){//定时发布
                jobUtils.addCron(ArticleJob.class.getName(),"sendArticle"+article.getId(),article.getSendTime(), JsonUtils.beanToJson(articleMemberResponse),0);
            }


            if(articleMemberResponse.getMemberResponses()!=null&&articleMemberResponse.getMemberResponses().size()>0){//创建文章和成员关联
                List<MemberArticle> memberArticles=this.getMemberArticles(articleMemberResponse.getMemberResponses(),article);
                if(memberArticles.size()>0){
                    memberArticleService.insertList(memberArticles);
                }
            }
            if(articleMemberResponse.getAnnexManages()!=null&&articleMemberResponse.getAnnexManages().size()>0){//附件
                List<RAnnexApply> rAnnexApplies=this.getRAnnexApplyList(articleMemberResponse.getAnnexManages(),article);
                rAnnexApplyService.insertList(rAnnexApplies);
            }
            if(article.getSendStatus().intValue()==1&&article.getSendType().intValue()==0){
                wxCpServiceTool.sendMessageArticle(articleMemberResponse);
            }
            json.put("result","ok");
            return json;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            json.put("result","fail");
            json.put("msg",e.getMessage());
            return json;
        }

    }


    /**
     * 构建附件与应用的关系集合
     * @param annexManages
     * @param article
     * @return
     */
    private   List<RAnnexApply> getRAnnexApplyList(List<AnnexManage> annexManages, Article article){
        List<RAnnexApply> rAnnexApplies=new ArrayList<>(annexManages.size());
        RAnnexApply rAnnexApply;
        for(AnnexManage annexManage:annexManages){
            rAnnexApply=new RAnnexApply();
            rAnnexApply.setType((byte)0);//0-文章
            rAnnexApply.setAnnexId(annexManage.getId());
            rAnnexApply.setApplyId(article.getId());
            rAnnexApplies.add(rAnnexApply);
        }
        return rAnnexApplies;
    }

    /**
     * 构建文章与成员的关系
     * @param memberResponses
     * @param article
     * @return
     */
    private  List<MemberArticle> getMemberArticles(List<MemberResponse> memberResponses,Article article){
        List<MemberArticle> memberArticles=new ArrayList<>(memberResponses.size());
        MemberArticle memberArticle;
        for (MemberResponse memberResponse:memberResponses){
            memberArticle=new MemberArticle();
            memberArticle.setArticle(article.getId());
            memberArticle.setMemberId(memberResponse.getId());
            memberArticle.setIsReadSuccess((byte)0);
            memberArticles.add(memberArticle);
        }
        return memberArticles;
    }

    /**
     * 编辑保存
     * @param memberArticleService
     * @param rAnnexApplyService
     * @param articleMemberResponse
     * @param systemUser
     */
    @Transactional
    public ObjectNode editSave(MemberArticleService memberArticleService, RAnnexApplyService rAnnexApplyService, ArticleMemberResponse articleMemberResponse, SystemUser systemUser) {
        ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
        try {
            Article article=new Article();
            BeanUtils.copyProperties(articleMemberResponse,article);
            article.setUpdateTime(CalendarUtils.getDate());
            jobUtils.jobDelete(ArticleJob.class.getName(),"sendArticle"+article.getId());
            if(article.getSendType().equals((byte)0)){//直接发布
                article.setSendTime(new Date());
            }else if(article.getSendType().equals((byte)1)){//定时发布
                jobUtils.addCron(ArticleJob.class.getName(),"sendArticle"+article.getId(),article.getSendTime(),JsonUtils.beanToJson(articleMemberResponse),0);
            }
            if(articleMemberResponse.getMemberResponses()!=null){
                article.setReceiveNumber(articleMemberResponse.getMemberResponses().size());
            }
            this.updateById(article);

            if(articleMemberResponse.getMemberResponses()!=null&&articleMemberResponse.getMemberResponses().size()>0){//创建文章和成员关联
                List<MemberArticle> memberArticles=this.getMemberArticles(articleMemberResponse.getMemberResponses(),article);

                 memberArticleService.deleteByArticleId(article.getId());
                 memberArticleService.insertList(memberArticles);

            }

            if(articleMemberResponse.getAnnexManages()!=null&&articleMemberResponse.getAnnexManages().size()>0){
                List<RAnnexApply> rAnnexApplies=this.getRAnnexApplyList(articleMemberResponse.getAnnexManages(),article);

                rAnnexApplyService.deleteByApplyIdAndType(article.getId(),0);//删除 文章与附件的关联关系
                rAnnexApplyService.insertList(rAnnexApplies);
            }

            if(article.getSendStatus().intValue()==1&&article.getSendType().intValue()==0){
                wxCpServiceTool.sendMessageArticle(articleMemberResponse);
            }
            json.put("result","ok");
            return json;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            json.put("result","fail");
            json.put("msg",e.getMessage());
            return json;
        }
    }
}
