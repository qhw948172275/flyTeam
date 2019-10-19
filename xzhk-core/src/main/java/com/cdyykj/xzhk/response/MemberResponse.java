package com.cdyykj.xzhk.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

public class MemberResponse {

    @ApiModelProperty(value = "会员ID")
    private Integer id;
    @ApiModelProperty(value = "会员名称")
    private String name;
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "userId")
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
