package com.cdyykj.xzhk.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AnswerListDto {

    /**
     * 测试系统ID
     */
    @ApiModelProperty(value = "测试系统ID")
    private Integer testSystemId;
    /**
     * 答案集合
     */
    @ApiModelProperty(value = "答案集合")
    private List<MemberAnswerDto> memberAnswerDtos;

    public List<MemberAnswerDto> getMemberAnswerDtos() {
        return memberAnswerDtos;
    }

    public void setMemberAnswerDtos(List<MemberAnswerDto> memberAnswerDtos) {
        this.memberAnswerDtos = memberAnswerDtos;
    }

    public Integer getTestSystemId() {
        return testSystemId;
    }

    public void setTestSystemId(Integer testSystemId) {
        this.testSystemId = testSystemId;
    }
}
