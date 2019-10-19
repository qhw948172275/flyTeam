package com.cdyykj.system.service.impl;


import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.system.dao.SystemResourceMapper;
import com.cdyykj.system.dao.SystemRoleResourceMapper;
import com.cdyykj.system.entity.SystemResource;
import com.cdyykj.system.entity.SystemRoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SystemResourceServiceImpl extends AbstractBaseCrudService<SystemResource,Integer> {
    @Autowired
    SystemRoleResourceMapper systemRoleResourceMapper;
    @Autowired
    SystemResourceMapper systemResourceMapper;

    /**
     * 删除资源及与角色关联
     * @param resourceId
     */
    public void delete(Integer resourceId){
        SystemResource systemResource=new SystemResource();
        systemResource.setId(resourceId);
        systemResourceMapper.delete(systemResource);
        SystemRoleResource systemRoleResource=new SystemRoleResource();
        systemRoleResource.setResourceId(resourceId);
        systemRoleResourceMapper.delete(systemRoleResource);
    }

}
