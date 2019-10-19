package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.TestSystem;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class TestSystemResponse extends TestSystem {
    /**
     * 是否测试0-否，1-是
     */
    @ApiModelProperty(value=" 是否测试0-否，1-是")
    @Column(name="is_test")
    private Byte isTest;

    /**
     * 是否合格，0-未通过，1-通过
     */
    @ApiModelProperty(value = "是否合格，0-未通过，1-通过")
    private Byte isPass;

    /**
     * 发布人
     */
    @ApiModelProperty(value = "发布人")
    private String publisher;
    /**
     * 测试人
     */
    private String memberName;
    /**
     * 成员总分
     */
    private Integer memberMarks;
    public Byte getIsTest() {
        return isTest;
    }

    public void setIsTest(Byte isTest) {
        this.isTest = isTest;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getMemberMarks() {
        return memberMarks;
    }

    public void setMemberMarks(Integer memberMarks) {
        this.memberMarks = memberMarks;
    }
}
