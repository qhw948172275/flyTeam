package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.QuestionBank;
import com.cdyykj.xzhk.response.QuestionBankResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionBankMapper extends MyMapper<QuestionBank> {
    /**
     * 根据测试系统ID获取详情
     * @param testSystemId
     * @return
     */
    List<QuestionBankResponse> selectQuestionBankResponse(@Param("testSystemId") Integer testSystemId);
}