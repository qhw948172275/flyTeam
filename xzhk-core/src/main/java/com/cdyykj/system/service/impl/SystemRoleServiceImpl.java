package com.cdyykj.system.service.impl;



import com.cdyykj.system.commons.RandomUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.SystemConstants;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.system.dao.SystemResourceMapper;
import com.cdyykj.system.dao.SystemRoleMapper;
import com.cdyykj.system.dao.SystemRoleResourceMapper;
import com.cdyykj.system.entity.SystemRole;
import com.cdyykj.system.entity.SystemRoleResource;
import com.cdyykj.system.response.RoleResourceResponse;
import com.cdyykj.system.response.RoleUserResponse;
import com.cdyykj.system.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemRoleServiceImpl extends AbstractBaseCrudService<SystemRole,Integer> implements SystemRoleService {
    @Autowired
    private SystemRoleResourceMapper systemRoleResourceMapper;
    @Autowired
    private SystemRoleMapper systemRoleMapper;
    @Autowired
    SystemResourceMapper systemResourceMapper;

    @Override
    public List<SystemRole> getAllRole(Integer schoolId, String sreach, Integer status, List<String> roleCodes,String roleCode) {
        Example example =new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtils.isNotEmpty(sreach)){
            Example.Criteria criteria1=example.and();
            criteria1.andLike("roleName","%"+sreach+"%").orLike("roleCode","%"+sreach+"%");
        }
        if(status!=null){
            criteria.andEqualTo("status",status);
        }
        if(roleCodes!=null){
            criteria.andIn("roleCode",roleCodes) ;
        }
        if(roleCode!=null){
            criteria.andNotEqualTo("roleCode",roleCode);
        }
        return mapper.selectByExample(example);
    }

    @Override
    public SystemRole getByCode(String code) {
        Example example =new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("roleCode",code);
        return mapper.selectByExample(example).get(0);
    }

    /**
     * 生成角色代码
     * @return
     */
    public String createRoleCode(){
        String roleCode= RandomUtils.getRandomCode(6);
        Example example =new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("roleCode",roleCode);
        List<SystemRole> systemRoles=mapper.selectByExample(example);
        if(systemRoles.size()>0){
            createRoleCode();
        }
        return roleCode;
    }

    /**
     * 更新角色信息及权限
     * @param resourceResponse
     */
    public void updateOrInsertByRoleResourceResponse(RoleResourceResponse resourceResponse, int type){
        SystemRole systemRole=resourceResponse.getSystemRole();
        if(type==1){
            if(updateById(systemRole)>0){
                updateOrInsertSystemRoleResource(systemRole.getId(),resourceResponse.getResourceIds(),type);
            }
        }else if(type==0){
            systemRole.setRoleType(0);//普通角色
            systemRole.setRoleCode(createRoleCode());
            if(insert(systemRole)>0){
                updateOrInsertSystemRoleResource(systemRole.getId(),resourceResponse.getResourceIds(),type);
            }
        }
    }

    /**
     *
     * @param roleId
     * @param resourceIds
     * @param type 0:插入，1：更新
     */
    public void updateOrInsertSystemRoleResource(Integer roleId,List<Integer> resourceIds,int type){
        if(type==0){
            insertSystemRoleResourceList(roleId,resourceIds);
        }else if(type==1){
            if(deleteSystemRoleResourceByRoleId(roleId)>=0){
                insertSystemRoleResourceList(roleId,resourceIds);
            }
        }
    }

    /**
     * 删除指定角色ID的角色资源关系对应表
     * @param roleId
     * @return
     */
    public int deleteSystemRoleResourceByRoleId(Integer roleId){
        Example example=new Example(SystemRoleResource.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        return  systemRoleResourceMapper.deleteByExample(example);
    }

    /**
     * 批量插入角色资源关系表
     * @param roleId
     * @param resourceIds
     */
    public void insertSystemRoleResourceList(Integer roleId,List<Integer> resourceIds){
        List<SystemRoleResource> systemRoleResources=new ArrayList<>();
        SystemRoleResource systemRoleResource;
        for(Integer resourceId:resourceIds){
            systemRoleResource=new SystemRoleResource();
            systemRoleResource.setResourceId(resourceId);
            systemRoleResource.setRoleId(roleId);
            systemRoleResources.add(systemRoleResource);
        }
        systemRoleResourceMapper.insertList(systemRoleResources);
    }

    /**
     * 查询当前角色拥有的资源ID
     * @param roleId
     * @return
     */
    public List<SystemRoleResource> selectRoleResourceIds(Integer roleId){
        Example example=new Example(SystemRoleResource.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        return systemRoleResourceMapper.selectByExample(example);
    }

    /**
     * 根据角色代码查询角色列表
     * @param roleCodes
     * @return
     */
    public List<SystemRole> selectByRoleCode(List<String> roleCodes){
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andIn("roleCode",  roleCodes);
        return mapper.selectByExample(example);
    }

    /**
     * 根据学校ID查询角色及对应的用户
     * @param schoolId
     * @return
     */
    public List<RoleUserResponse> selectRoleUserResponseBySchoolId(Integer schoolId){
        return systemRoleMapper.selectRoleUserResponseBySchoolId(schoolId);
    }

    /**
     * 根据当前用户ID获取对应角色信息
     * @return
     */
   public List<SystemRole> selectByUserId(Integer userId,Integer status){
       return systemRoleMapper.selectByUserId(userId,status);
    }

    /**
     * 默认校管理眼的权限
     * @param roleId
     */
    @Override
    public void createRoleBySchoolUser(Integer roleId) {

        List<Integer> systemResourceList=systemResourceMapper.selectResourceByRoleId(SystemConstants.SCHOOL_ADMIN_ID);
        List<SystemRoleResource> systemRoleResourceList=new ArrayList<>();
        for(Integer integer:systemResourceList){
            SystemRoleResource  systemRoleResource=new SystemRoleResource();
            systemRoleResource.setRoleId(roleId);
            systemRoleResource.setResourceId(integer);
            systemRoleResourceList.add(systemRoleResource);
        }
        systemRoleResourceMapper.insertList(systemRoleResourceList);
    }

    /**
     * 根据状态获取角色列表
     * @param status
     * @return
     */
    public List<SystemRole> selectRoleListByStatus(int status) {
        Example example=new Example(tClass);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("status",status);
        return mapper.selectByExample(example);
    }
}
