package com.cdyykj.system.service;

import com.cdyykj.system.commons.MyPageInfo;
import com.cdyykj.system.commons.PageTools;
import com.cdyykj.system.commons.service.BaseCrudService;
import com.cdyykj.system.entity.SystemRole;
import com.cdyykj.system.entity.SystemRoleUser;
import com.cdyykj.system.entity.SystemUser;
import com.cdyykj.system.response.SystemUserRoleResponse;

import java.util.Collection;
import java.util.List;

/**
 * 用户管理接口
 * 
 * @author kimi
 * @dateTime 2012-3-9 下午3:28:54
 */
public interface SystemUserService extends BaseCrudService<SystemUser, Integer> {



	SystemUser getUserByEmail(String email);

	List<SystemUser> getUserByName(String userName, Integer schoolId);
	 SystemUser getUserByName(String userName);
	/**
	 * 根据手机号查询系统用户
		 * @return:
		 * @author lyb
		 * @date 2018年9月8日
		 * @param
	 */
	SystemUser getUserByPhone(String phone);
	/**
	 * 根据教师ID查询账号信息
	* @author chenbiao
	* @date 2018年9月3日 上午11:24:14
	* 参数说明
	*
	* @param teacherId
	* @return
	 */
	SystemUser getByTeacherId(Integer teacherId);

	/**
	 * 创建用户，关联角色
	 *
	 * @author kimi
	 * @dateTime 2012-3-9 下午3:25:56
	 * @param user
	 * @param roles
	 * @return
	 */
	boolean addUser(SystemUser user, List<Integer> roles);

	/**
	 * 批量创建用户
	 *
	 * @author kimi
	 * @dateTime 2012-3-9 下午3:25:56
	 * @param users
	 * @return
	 */
	public Object addUser(List<SystemUser> users);

	/**
	 * 验证用户名是否已被注册
	 *
	 * @author kimi
	 * @dateTime 2012-3-14 上午11:47:59
	 * @param username
	 * @return
	 */
	boolean validateUserNameExists(String username, Integer userId);

	/**
	 * 验证手机号是否存在
	* @author chenbiao
	* @date 2017年2月20日 上午11:55:51
	* 参数说明
	*
	* @param phone
	* @return
	 */
	boolean validatePhoneExists(String phone);
	boolean validatePhoneExists(String phone, int userId);


	/**
	 * 修改用户，修改角色
	 *
	 * @author kimi
	 * @dateTime 2012-3-9 下午3:28:21
	 * @param user
	 * @param rid
	 * @return
	 */
	int updateUser(SystemUser user, List<Integer> rid);


	/**
	 * 根据条件获取用户列表
	 *
	 * @author kimi
	 * @dateTime 2012-3-9 下午3:27:48
	 * @return
	 */
	MyPageInfo<SystemUser> getUserListByName(String name, PageTools page);

	List<SystemUser> getUserListByName(String name, Integer schoolId);
	/**
	 * 查询非超级管理员的用户
	 * @param name
	 * @param pageTools
	 * @return
	 */
	MyPageInfo<SystemUser> getUserListByNoAdmin(Integer schoolId, String name, PageTools pageTools);

	/**
	 * 根据条件获取用户列表
	 *
	 * @author kimi
	 * @dateTime 2012-3-9 下午3:27:48
	 * @return
	 */
	List<SystemUser> getUserListByName(String name, List<SystemRole> roles, PageTools page);

	List<SystemUser> selectUserListByPhone(String phone);
	/**
	 * 根据条件获取用户总数
	 *
	 * @author kimi
	 * @dateTime 2012-3-9 下午3:27:48
	 * @return
	 */
	long getUserCountsByName(String name);

	/**
	 * 根据条件获取用户总数
	 *
	 * @author kimi
	 * @dateTime 2012-3-9 下午3:27:48
	 * @return
	 */
	long getUserCountsByName(String name, List<SystemRole> roles);

	/**
	 * @author kimi
	 * @dateTime 2012-3-14 下午12:17:14
	 * @param user
	 * @return
	 */
	@Deprecated
	boolean updateUserStatus(SystemUser user);


	/**
	 * 获取所有系统用户
	 *
	 *
	 * @author cym
	 * @date 2018年1月30日 上午11:55:21
	 */
	List<SystemUserRoleResponse> getAll(String search, List<String> roleCodes, Integer schoolId);

	/**
	 * 根据手机号获取系统用户
	 * @param mobiles
	 * @return
	 */
	List<SystemUser> getUserListByMobile(Collection<String> mobiles);


	SystemUser findUserByName(String name);

	/**
	 * 用户编辑时查询
	 * @param userId
	 * @return
	 */
	SystemUserRoleResponse getSystemUserRoleResponseById(Integer userId);

	/***
	 * 根据用户ID与学校ID删除用户角色管理
	 *
	 * @param userId
	 * @param schoolId
	 * @param notRoleId 不删除的角色
	 * @return
	 */
	int deleteUserRoleByUserId(Integer userId, Integer schoolId, Integer notRoleId);

	/**
	 * 根据用户id与角色id与学校id删除用户角色对应关系
	 * @param userId
	 * @param roleId
	 * @param schoolId
	 * @return
	 */
	int deleteUserRoleByUserIdAndRoleId(Integer userId, Integer roleId, Integer schoolId);

	/**
	 * 根据用户id与角色id列表与学校id删除用户角色对应关系
	 * @param userIds
	 * @param roleId
	 * @param schoolId
	 * @return
	 */
	int deleteUserRoleByUserIdsAndRoleId(List<Integer> userIds, Integer roleId, Integer schoolId);

	/**
	 * 批量插入用户角色关系表
	 * @param systemRoleUsers
	 * @return
	 */
	int insertUserRolleList(List<SystemRoleUser> systemRoleUsers);

	/**
	 * 插入用户角色关系表
	 * @param systemRoleUser
	 * @return
	 */
	int insertUserRoll(SystemRoleUser systemRoleUser);

	/**
	 * 根据用户Id与学校ID删除教师机构关联表
	 * @param userId
	 * @return
	 */
	int deleteTeacherOrganByUserId(Integer userId, Integer schoolId);



	/**
	 * 根据手机号码查询用户
	 * @param phone
	 * @return
	 */
	SystemUser selectUserByPhone(String phone);

	/**
	 * 校验教职工
	 */
	Boolean checkTeacher(Integer schoolId, String phone, Integer id);



	/***
	 * 查询所有用户手机号
	 *
	 * @return
	 */
	List<String> selectAllUserPhone();

	/**
	 * 根据用户ID更改状态
	 * @param userId
	 * @param status
	 * @return
	 */
	int enabledOrDisabled(Integer userId, Integer status);

	List<SystemUser> selectAllTeacherBySchoolId(Integer schoolId, String sreach);
	/**
	 * 根据学校,角色状态和角色代码查询用户列表
	 * @param schoolIds
	 * @param roleCodes
	 * @return
	 */
	List<SystemUser> selectBySchoolIdAndRoleCode(List<Integer> schoolIds, List<String> roleCodes, Integer status);

	/**
	 * 根据班级ID查询对应的班主任以及任课老师信息
	 *
	* @author chenbiao
	* @date 2019年5月21日 下午7:24:14
	* 参数说明
	*
	* @param classId
	* @return
	 */
	List<SystemUser> selectByClassId(Integer classId, String name, Integer schoolId);

	List<SystemUser> selectByClassIdIn(List<Integer> classIds, String name, Integer schoolId);


	int insertList(List<SystemUser> systemUserList);


	List<SystemUser> selectByOrganId(Integer organId, Integer schoolId, String keyword);



	/**
	 * 查询分组中不存在在的用户
	 * @param groupId
	 * @param schoolId
	 * @return
	 */
	List<SystemUser> selectTeacherForGroup(Integer groupId, Integer schoolId);

	/**
	 * 根据用户ID查询用户clientIDs
	 * @return
	 */
	List<String> selectClientIdsByUserIds(List<Integer> userIds);


	/**
	 * 根据角色编码查询用户信息
	 * @param roleCode
	 * @return
	 */
	List<SystemUser> selectByRoleCodeAndSchoolId(Integer schoolId, String roleCode);

	/**
	 * 根据学校ID查询clientIds
	 * @param schoolId
	 * @return
	 */
	List<String> selectClientIdsBySchoolId(Integer schoolId);


	void createRoleBySchoolUser(Integer schoolId, Integer userId, Integer roleId);

	List<SystemUser> selectTeacher(String username, String schoolCode);
}
