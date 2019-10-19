package com.cdyykj.xzhk.dto;


import java.util.List;

public class MemberAnswerDto {

    /**
     * 题ID
     */
    private Integer questionId;
    /**
     * 成员答案
     */
    private List<String> resultResponses;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<String> getResultResponses() {
        return resultResponses;
    }

    public void setResultResponses(List<String> resultResponses) {
        this.resultResponses = resultResponses;
    }
}
