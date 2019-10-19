package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.Department;
import com.cdyykj.xzhk.response.RDepartmentMemberResponse;
import com.cdyykj.xzhk.response.RDepartmentMemberResponseDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper extends MyMapper<Department> {
    /**
     * 清空部门表
     */
    void deleteAll();

    int insertListFor(@Param("departments") List<Department> departments);

    /**
     * 获取全部部门和成员列表，除开顶级部门
     * @return
     */
    List<RDepartmentMemberResponseDto> selectRDepartmentMemberResponse();

    /**
     * 获取全部顶级部门和成员列表
     * @return
     */
    List<RDepartmentMemberResponseDto> selectRDepartmentMemberResponseParent();
}