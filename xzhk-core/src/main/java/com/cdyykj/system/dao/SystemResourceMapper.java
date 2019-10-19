package com.cdyykj.system.dao;

import com.cdyykj.system.entity.SystemResource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SystemResourceMapper extends Mapper<SystemResource> {
	/**
	 * 根据角色ID查询角色所能管理权限
	 * 
	 * @author kimi
	 * @dateTime 2012-3-9 下午4:55:15
	 * @param rid
	 * @return
	 */
	public List<SystemResource> getResourceListByRid(@Param("rid") Integer rid);
	/**
	 * 根据角色ID查询角色所能管理权限
	 * 
	 * @author kimi
	 * @dateTime 2012-3-9 下午4:55:15
	 * @return
	 */
	public List<SystemResource> getResourceListByRidIn(@Param("rids") List<Integer> list);

	/**
	 * 根据当前用户ID查询对应角色对应的资源
	 * @param userId
	 * @return
	 */
	List<SystemResource> selectByUserId(@Param("userId") Integer userId);

	/**
	 * 根据角色Id查询权限配置
	 * @param roleId
	 * @return
	 */
    List<Integer> selectResourceByRoleId(@Param("roleId") Integer roleId);
}