package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.Article;
import com.cdyykj.xzhk.response.ArticleMemberResponse;
import com.cdyykj.xzhk.response.ArticleResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends MyMapper<Article> {

    /**
     * 关键字搜索文章
     * @param keyword
     * @param dateStr
     * @return
     */
    List<Article> selectByKeyword(@Param("keyword") String keyword,@Param("dateStr") String dateStr);

    /**
     * 根据成员ID获取文章列表
     * @param memberId
     * @return
     */
    List<ArticleResponse> selectArticleResponse(@Param("memberId") Integer memberId);

    /**
     * 根据文章ID查询详情
     * @param articleId
     * @return
     */
    ArticleMemberResponse selectArticleMemberResponse(@Param("articleId") Integer articleId);
}