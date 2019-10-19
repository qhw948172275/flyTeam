package com.cdyykj.system.dto;

import com.cdyykj.xzhk.entity.Department;

import java.io.Serializable;
import java.util.List;

public class DepartmentDto extends Department implements Serializable {
    private List<DepartmentDto> departments;

    public List<DepartmentDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentDto> departments) {
        this.departments = departments;
    }
}
