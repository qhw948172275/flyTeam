package com.cdyykj.xzhk.dto;

import io.swagger.annotations.ApiModelProperty;

public class TestSystemStatusDto {
    /**
     * 测试系统ID
     */
    @ApiModelProperty(value = "测试系统ID")
    private Integer testSystemId;
    /**
     * 1-发布，2-删除
     */
    @ApiModelProperty(value = "1-发布，2-删除")
    private Byte status;

    public Integer getTestSystemId() {
        return testSystemId;
    }

    public void setTestSystemId(Integer testSystemId) {
        this.testSystemId = testSystemId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
