package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.DepartmentMember;
import io.swagger.annotations.ApiModelProperty;

public class DepartmentMemberResponse extends DepartmentMember {
    /**
     * 部门列表集合
     */
    @ApiModelProperty(value = "部门名称列表集合")
    private String dptNames;

    public String getDptNames() {
        return dptNames;
    }

    public void setDptNames(String dptNames) {
        this.dptNames = dptNames;
    }
}
