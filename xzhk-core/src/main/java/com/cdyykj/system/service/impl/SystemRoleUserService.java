package com.cdyykj.system.service.impl;

import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.system.dao.SystemRoleUserMapper;
import com.cdyykj.system.entity.SystemRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SystemRoleUserService extends AbstractBaseCrudService<SystemRoleUser,Integer> {
    @Autowired
    SystemRoleUserMapper systemRoleUserMapper;
    /**
     * 根据用户ID删除用户与角色关联关系
     * @param uid
     */
    public int deleteByUserId(Integer uid) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("uid",uid);
        return mapper.deleteByExample(example);
    }

    /**
     * 批量插入用户与角色关联关系
     * @param systemRoleUserList
     */
    public void insertList(List<SystemRoleUser> systemRoleUserList){
        systemRoleUserMapper.insertList(systemRoleUserList);
    }
}
