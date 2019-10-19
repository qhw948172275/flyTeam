package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.RDepartmentMemberMapper;
import com.cdyykj.xzhk.entity.RDepartmentMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RDepartmentMemberService extends AbstractBaseCrudService<RDepartmentMember,Integer> {
    @Autowired
    RDepartmentMemberMapper rDepartmentMemberMapper;
    /**
     * 清空成员部门关系表
     */
    public void deleteAll() {
        rDepartmentMemberMapper.deleteAll();
    }

    /**
     * 批量插入成员部门关系表
     * @param rDepartmentMembers
     */
    public void insertList(List<RDepartmentMember> rDepartmentMembers){
        rDepartmentMemberMapper.insertList(rDepartmentMembers);
    }

    /**
     *根据成员ID删除
     * @param memberId
     */
    public void deleteByMemberId(Integer memberId) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("memberId",memberId);
        mapper.deleteByExample(example);
    }

    /**
     * 删除该部门与成员的关联
     */
    public void deleteByDepartmentId(Integer departmentId){
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("departmentId",departmentId);
        mapper.deleteByExample(example);
    }
}
