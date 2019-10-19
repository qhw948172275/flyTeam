package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.MemberArticle;
import com.cdyykj.xzhk.response.MemberReadDetailResponse;
import com.cdyykj.xzhk.response.MemberResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberArticleMapper extends MyMapper<MemberArticle> {
    /**
     * 根据文章ID查询成员信息
     * @param articleId
     * @return
     */
    List<MemberResponse> selectMemberResponseByArticleId(@Param("articleId") Integer articleId);

    /**
     * 根据文章ID查询成员阅读情况
     * @param articleId
     * @return
     */
    List<MemberReadDetailResponse> selectMemberReadDetailResponse(@Param("articleId") Integer articleId
            ,@Param("keyword")String keyword);

    /**
     * 根据成员文章关联ID查询成员阅读情况
     * @param idIs
     * @return
     */
    List<MemberReadDetailResponse> selectMemberReadDetailResponseOutPut(@Param("idIs") List<Integer> idIs);
}