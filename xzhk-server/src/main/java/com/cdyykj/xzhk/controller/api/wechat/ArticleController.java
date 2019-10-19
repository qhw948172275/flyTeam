package com.cdyykj.xzhk.controller.api.wechat;

import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.NumberUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.annotation.MemberLoginRequired;
import com.cdyykj.xzhk.controller.api.BaseController;
import com.cdyykj.xzhk.dto.ArticleReadDto;
import com.cdyykj.xzhk.entity.Article;
import com.cdyykj.xzhk.entity.MemberArticle;
import com.cdyykj.xzhk.response.ArticleMemberResponse;
import com.cdyykj.xzhk.response.ArticleResponse;
import com.cdyykj.xzhk.service.ArticleService;
import com.cdyykj.xzhk.service.MemberArticleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Api(description = "文章模块")
@RestController(value = "memberArticleController")
@RequestMapping(value = "wechat/member/article")
public class ArticleController extends BaseController {
    @Autowired
    ArticleService articleService;
    @Autowired
    MemberArticleService memberArticleService;

    /**
     * 文章列表
     * @return
     */
    @GetMapping(value = "articleList")
    @MemberLoginRequired
    @ApiOperation(value = "文章列表",response = ArticleResponse.class)
    public JsonResult articleList(HttpServletRequest request, @RequestParam(required =false,defaultValue = "1") int page
            , @RequestParam(required =false,defaultValue = "10") int limit){
        try {
            ObjectNode json= JsonUtils.getMapperInstance().createObjectNode();
            Integer memberId=getDepartmentMember(request).getId();//成员ID
            PageHelper.startPage(page,limit);
            List<ArticleResponse> articleResponseList=articleService.selectArticleResponse(memberId);
            json.putPOJO("articleResponseList",new PageInfo<>(articleResponseList));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 文章详情
     * @return
     */
    @GetMapping(value = "articleDetail")
    @ApiOperation(value = "文章详情",response = ArticleMemberResponse.class)
    public JsonResult articleDetail(@RequestParam Integer articleId){
        try {
            ObjectNode json =JsonUtils.getMapperInstance().createObjectNode();
            ArticleMemberResponse articleMemberResponse=articleService.selectArticleMemberResponse(articleId);
            json.putPOJO("articleMemberResponse",articleMemberResponse);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 提交阅读时间
     * @param request
     * @return
     */

    @MemberLoginRequired
    @ApiOperation(value = "提交阅读时间")
    @PostMapping(value = "submitRead")
    public JsonResult submitRead(HttpServletRequest request, @RequestBody ArticleReadDto articleReadDto){
        try {
            Integer memberId=getDepartmentMember(request).getId();//成员ID
            Article article=articleService.getById(articleReadDto.getArticleId());
            Date endReadTime= CalendarUtils.stringToDate(articleReadDto.getEndReadTime(),CalendarUtils.yyyy_MM_dd__HH_mm_ss);
            Date startReadTime= CalendarUtils.stringToDate(articleReadDto.getStartReadTime(),CalendarUtils.yyyy_MM_dd__HH_mm_ss);
            long readeTime= (endReadTime.getTime()-startReadTime.getTime())/1000;//本次阅读时长
            MemberArticle memberArticle=memberArticleService.selectBymemberIdAndarticleId(memberId,articleReadDto.getArticleId());

            if(memberArticle==null){
                memberArticle =new MemberArticle();
                memberArticle.setMemberId(memberId);
                memberArticle.setArticle(articleReadDto.getArticleId());
                memberArticle.setStartReadTime(startReadTime);
                memberArticle.setEndReadTime(endReadTime);
                memberArticle.setIp(getIpAddr(request));
                memberArticle.setReadTime(readeTime);
                memberArticle.setIsReadSuccess((byte)0);
            }else{
                if(StringUtils.isEmpty(memberArticle.getIp())){
                    memberArticle.setIp(getIpAddr(request));
                }
                if(memberArticle.getStartReadTime()==null){
                    memberArticle.setStartReadTime(startReadTime);
                }
                memberArticle.setEndReadTime(endReadTime);
                readeTime+=memberArticle.getReadTime();
                memberArticle.setReadTime(readeTime);
            }
            if(memberArticle.getIsReadSuccess().intValue()==0){//如果已经成功阅读，不需要再计算
                if(readeTime>=article.getReadTime().longValue()){
                    memberArticle.setIsReadSuccess((byte)1);//成功阅读
                    article.setSuccessReadNumber(article.getSuccessReadNumber()+1);
                    if(article.getReceiveNumber().intValue()>0){//更新阅读率
                        double rate= NumberUtils.doubleDivide(article.getSuccessReadNumber(),article.getReceiveNumber());
                        BigDecimal decimal = new BigDecimal(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
                        article.setReadSuccessRate(decimal.doubleValue());
                    }
                }else{
                    memberArticle.setIsReadSuccess((byte)0);//否
                }
            }
            article.setClickCount(article.getClickCount()+1);//更新点击量
            articleService.updateById(article);
            if(memberArticle.getId()!=null){
                memberArticleService.updateById(memberArticle);
            }else{
                memberArticleService.insert(memberArticle);
            }
            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }
}
