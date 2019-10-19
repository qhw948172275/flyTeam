package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.QuestionBank;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class QuestionBankResponse extends QuestionBank {

    @ApiModelProperty(value="选项集合")
    List<QuestionResponse> questionResponses;

    @ApiModelProperty(value = "答案集合")
    List<Integer> resultResponses;

    @ApiModelProperty(value = "成员做题详情")
    DoRecordDetailResponse doRecordDetailResponse;

    public List<QuestionResponse> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(List<QuestionResponse> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public List<Integer> getResultResponses() {
        return resultResponses;
    }

    public void setResultResponses(List<Integer> resultResponses) {
        this.resultResponses = resultResponses;
    }

    public DoRecordDetailResponse getDoRecordDetailResponse() {
        return doRecordDetailResponse;
    }

    public void setDoRecordDetailResponse(DoRecordDetailResponse doRecordDetailResponse) {
        this.doRecordDetailResponse = doRecordDetailResponse;
    }
}
