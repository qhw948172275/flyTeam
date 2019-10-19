package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.Department;
import com.cdyykj.xzhk.entity.DepartmentMember;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RDepartmentMemberResponse extends Department {
    /**
     * 成员列表
     */
    @ApiModelProperty(value = "成员列表")
    private   List<DepartmentMember>  departmentMembers;
    /**
     * 子集列表
     */
    @ApiModelProperty(value = "子集列表")
    private List<RDepartmentMemberResponseDto> responseDtos;

    public List<DepartmentMember> getDepartmentMembers() {
        return departmentMembers;
    }

    public void setDepartmentMembers(List<DepartmentMember> departmentMembers) {
        this.departmentMembers = departmentMembers;
    }

    public List<RDepartmentMemberResponseDto> getResponseDtos() {
        return responseDtos;
    }

    public void setResponseDtos(List<RDepartmentMemberResponseDto> responseDtos) {
        this.responseDtos = responseDtos;
    }
}
