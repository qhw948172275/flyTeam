package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.TestSystem;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TestSystemQuestionResponse extends TestSystem {
    @ApiModelProperty(value="成员集合")
    private List<MemberResponse> memberResponses;
    @ApiModelProperty(value="题集合")
    private List<QuestionBankResponse> questionBankResponses;

    public List<MemberResponse> getMemberResponses() {
        return memberResponses;
    }

    public void setMemberResponses(List<MemberResponse> memberResponses) {
        this.memberResponses = memberResponses;
    }

    public List<QuestionBankResponse> getQuestionBankResponses() {
        return questionBankResponses;
    }

    public void setQuestionBankResponses(List<QuestionBankResponse> questionBankResponses) {
        this.questionBankResponses = questionBankResponses;
    }
}
