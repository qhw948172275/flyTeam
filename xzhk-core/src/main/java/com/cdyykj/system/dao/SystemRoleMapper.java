package com.cdyykj.system.dao;


import com.cdyykj.system.entity.SystemResource;
import com.cdyykj.system.entity.SystemRole;
import com.cdyykj.system.response.RoleUserResponse;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SystemRoleMapper extends Mapper<SystemRole> {

	List<SystemResource> selectResourceListByRoleIdList(@Param("list") List<Integer> list);
	
	/**
	 * 根据用户ID查询用户所属角色
	 * (一个用户可能有多个角色)
	 * 
	 * @author kimi
	 * @dateTime 2012-3-23 上午11:27:00
	 * @param uid
	 * @return
	 */
	List<SystemRole> getRoleByUserId(@Param("uid") int uid);

	/**
	 * 根据学校ID查询角色及对应的用户
	 * @param schoolIdd
	 * @return
	 */
	List<RoleUserResponse> selectRoleUserResponseBySchoolId(@Param("schoolId") Integer schoolIdd);

	/**
	 * 根据当前用户ID获取对应角色信息
	 * @return
	 */
	 List<SystemRole> selectByUserId(@Param("userId") Integer userId, @Param("status") Integer status);
}