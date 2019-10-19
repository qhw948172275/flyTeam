package com.cdyykj.system.service;



import com.cdyykj.system.commons.service.BaseCrudService;
import com.cdyykj.system.entity.SystemRole;

import java.util.List;

/**
 * 角色管理接口
 * 
 * @author kimi
 * @dateTime 2012-3-19 下午12:00:11
 */
public interface SystemRoleService extends BaseCrudService<SystemRole, Integer> {


	/**
	 * 查询本校所有启用角色
	 * @param schoolId
	 * @param sreach
	 * @return
	 */
	List<SystemRole> getAllRole(Integer schoolId, String sreach, Integer status, List<String> roleCodes, String roleCode);

	/**
	 * 根据代码获取角色
	 * @param code
	 * @return
	 */
	SystemRole getByCode(String code);

	/**
	 * 根据当前用户ID获取对应角色信息
	 * @return
	 */
	List<SystemRole> selectByUserId(Integer userId, Integer status);

    void createRoleBySchoolUser(Integer roleId);
}
