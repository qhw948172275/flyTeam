package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.DepartmentMember;
import io.swagger.annotations.ApiModelProperty;

public class MemberInfoResponse extends DepartmentMember {
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
