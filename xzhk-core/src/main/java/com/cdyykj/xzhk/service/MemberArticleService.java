package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.MemberArticleMapper;
import com.cdyykj.xzhk.entity.MemberArticle;
import com.cdyykj.xzhk.response.MemberReadDetailResponse;
import com.cdyykj.xzhk.response.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MemberArticleService extends AbstractBaseCrudService<MemberArticle,Integer> {
    @Autowired
    MemberArticleMapper memberArticleMapper;
    /**
     * 批量插入文章阅读人员
     */
    public void insertList(List<MemberArticle> memberArticles) {
        memberArticleMapper.insertList(memberArticles) ;
    }

    /**
     * 根据文章ID删除成员阅读关系
     * @param id
     */
    public void deleteByArticleId(Integer id) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("article",id);
        mapper.deleteByExample(example);
    }

    /**
     * 根据文章ID查询成员信息
     * @param articleId
     * @return
     */
    public List<MemberResponse> selectMemberResponseByArticleId(Integer articleId) {
        return memberArticleMapper.selectMemberResponseByArticleId(articleId);
    }

    /**
     * 根据文章ID查询成员阅读情况
     * @param articleId
     * @return
     */
    public List<MemberReadDetailResponse> selectMemberReadDetailResponse(Integer articleId,String keyword) {
        if(StringUtils.isNotEmpty(keyword)){
            keyword="%"+keyword+"%";
        }else{
            keyword=null;
        }
        return memberArticleMapper.selectMemberReadDetailResponse(articleId,keyword);
    }

    /**
     * 根据成员文章关联ID查询成员阅读情况
     * @param idIs
     * @return
     */
    public List<MemberReadDetailResponse> selectMemberReadDetailResponseOutPut(List<Integer> idIs) {
        return memberArticleMapper.selectMemberReadDetailResponseOutPut(idIs);
    }

    /**
     * 根据成员ID和文章ID查询阅读情况
     * @param memberId
     * @param articleId
     * @return
     */
    public MemberArticle selectBymemberIdAndarticleId(Integer memberId, Integer articleId) {
        Example example=new Example(tClass);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("memberId",memberId).andEqualTo("article",articleId);
        return mapper.selectOneByExample(example);
    }
}
