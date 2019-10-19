package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.MemberArticle;
import io.swagger.annotations.ApiModelProperty;


public class MemberReadDetailResponse extends MemberArticle {

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String departmentName;
    /**
     * 成员名称
     */
    @ApiModelProperty(value = "成员名称")
    private String memberName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
