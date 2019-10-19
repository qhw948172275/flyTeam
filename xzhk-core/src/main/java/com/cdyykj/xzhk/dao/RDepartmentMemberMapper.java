package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.RDepartmentMember;

public interface RDepartmentMemberMapper extends MyMapper<RDepartmentMember> {
    /**
     * 清空成员部门关系表
     */
    void deleteAll();
}