package com.cdyykj.system.dao;


import com.cdyykj.system.entity.SystemUser;
import com.cdyykj.system.response.SystemUserRoleResponse;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SystemUserMapper extends Mapper<SystemUser>, InsertListMapper<SystemUser> {
	/**
	 * 查询单个角色下的用户信息
	 * 
	 * @author kimi
	 * @dateTime 2013-4-19 下午1:28:38
	 * @param rid
	 * @return
	 */
	List<SystemUser> getUserEntityListInRid(@Param("roleid") int rid);

	/**
	 * 查询多个角色下的用户信息
	 * 
	 * @author kimi
	 * @dateTime 2013-4-19 下午1:28:41
	 * @param rid
	 * @return
	 */
	List<SystemUser> getUsersListInRids(@Param("userName") String userName, @Param("roleList") List<Integer> rid,
                                        @Param("limitFrom") Integer limitFrom, @Param("limitTo") Integer limitTo);

	/**
	 * 查询多个角色下的用户信息
	 *
	 * @author kimi
	 * @dateTime 2013-4-19 下午1:28:41
	 * @param rid
	 * @return
	 */
	int getUserCountInRids(@Param("userName") String userName, @Param("roleList") List<Integer> rid);

	/**
	 * 查询不属于当前角色的用户信息
	 *
	 * @author kimi
	 * @dateTime 2013-4-19 下午1:28:45
	 * @param rid
	 * @return
	 */
	List<SystemUser> getUserEntityListNotInRid(@Param("roleid") int rid);

	/**
	 * 查询非超级用户列表
	 */
	List<SystemUser> getUserListByNoAdmin(@Param("name") String name, @Param("schoolId") Integer schoolId);

	/**
	 * 用户管理主页面查询
	 * @param search
	 * @return
	 */
	List<SystemUserRoleResponse> getSystemUserBySearch(@Param("search") String search, @Param("roleCodes") List<String> roleCodes, @Param("schoolId") Integer schoolId);

	/**
	 * 编辑用户时查询
	 * @param userId
	 * @return
	 */
	SystemUserRoleResponse getSystemUserRoleResponseById(@Param("userId") Integer userId);

	/**
	 * 根据角色代码和学校ID查询用户信息
 	 * @param schoolId
	 * @param roleCode
	 * @return
	 */
	List<SystemUser> selectByRoleCodeAndSchoolId(@Param("schoolId") Integer schoolId, @Param("roleCode") String roleCode);


	/**
	 * 校验教职工
	 */
	Integer checkTeacher(@Param("schoolId") Integer schoolId, @Param("phone") String phone, @Param("id") Integer id);

	/**
	 * 根据用户状态更改状态
	 * @param userId
	 * @param status
	 * @return
	 */
	int enabledOrDisabled(@Param("userId") Integer userId, @Param("status") Integer status);

	/**
	 * 查询学校所有的教职工
	 * @param schoolId
	 * @param roleList
	 * @return
	 */
	List<SystemUser> selectAllTeacherBySchoolId(@Param("schoolId") Integer schoolId, @Param("roleList") List<String> roleList);


	/**
	 * 查询分组中没有的教师
	 * @param groupId
	 * @param schoolId
	 * @param roleList
	 * @return
	 */
	List<SystemUser> selectTeacherForGroup(@Param("groupId") Integer groupId, @Param("schoolId") Integer schoolId, @Param("roleList") List<String> roleList);

	/**
	 * 根据学校,角色状态和角色代码查询用户列表
	 * @param schoolIds
	 * @param roleCodes
	 * @return
	 */
	List<SystemUser> selectBySchoolIdAndRoleCode(@Param("schoolIds") List<Integer> schoolIds, @Param("roleCodes") List<String> roleCodes, @Param("status") Integer status);

	/**
	 * 根据班级ID查询对应的班主任以及任课老师信息
	 *
	* @author chenbiao
	* @date 2019年5月23日 下午4:23:31
	* 参数说明
	*
	* @return
	 */
	List<SystemUser> selectByClassId(@Param("classId") Integer classId, @Param("name") String name, @Param("schoolId") Integer schoolId);

	/**
	 *
	* 根据班级ID集合查询对应的班主任以及任课老师信息
	* @author chenbiao
	* @date 2019年5月23日 下午5:42:29
	* 参数说明
	*
	* @param classIds
	* @param name
	* @param schoolId
	* @return
	 */
	List<SystemUser> selectByClassIdIn(@Param("classIds") List<Integer> classIds, @Param("name") String name, @Param("schoolId") Integer schoolId);

	/**
	 * 根据部门ID查询老师
	 * @param organId
	 * @param schoolId
	 * @return
	 */
	List<SystemUser> selectByOrganId(@Param("organId") Integer organId, @Param("schoolId") Integer schoolId, @Param("keyword") String keyword);


	/**
	 * 根据用户IDs查询用户clientIds
	 * @param userIds
	 * @return
	 */
	List<String> selectClientIdsByUserIds(@Param("userIds") List<Integer> userIds);

	/**
	 * 根据学校ID查询clientIds
	 * @param schoolId
	 * @return
	 */
	List<String> selectClientIdsBySchoolId(@Param("schoolId") Integer schoolId);
	/**
	 * 查询学校老师信息
	 * @param phone
	 * @return
	 */
    List<SystemUser> selectTeacher(@Param("phone") String phone, @Param("schoolCode") String schoolCode);

	/**
	 * 根据用户账号查询
	 * @param userName
	 * @param schoolId
	 * @return
	 */
	List<SystemUser> getUserByName(@Param("userName") String userName, @Param("schoolId") Integer schoolId);
}