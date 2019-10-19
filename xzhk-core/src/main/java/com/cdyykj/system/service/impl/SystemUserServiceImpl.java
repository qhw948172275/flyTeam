package com.cdyykj.system.service.impl;


import com.cdyykj.system.commons.MyPageInfo;
import com.cdyykj.system.commons.PageTools;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.system.dao.SystemResourceMapper;
import com.cdyykj.system.dao.SystemRoleMapper;
import com.cdyykj.system.dao.SystemRoleUserMapper;
import com.cdyykj.system.dao.SystemUserMapper;
import com.cdyykj.system.entity.SystemRole;
import com.cdyykj.system.entity.SystemRoleUser;
import com.cdyykj.system.entity.SystemUser;
import com.cdyykj.system.response.SystemUserRoleResponse;
import com.cdyykj.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.List;

@Service
public class SystemUserServiceImpl extends AbstractBaseCrudService<SystemUser, Integer> implements SystemUserService {

	@Autowired
    SystemUserMapper systemUserMapper;

	@Autowired
    SystemRoleUserMapper systemRoleUserMapper;
	@Autowired
    SystemResourceMapper systemResourceMapper;
	@Autowired
    SystemRoleMapper systemRoleMapper;

	@Override
	public SystemUser getUserByEmail(String email) {
		return null;
	}

	@Override
	public List<SystemUser> getUserByName(String userName,Integer schoolId) {
//		Example example = new Example(tClass);
//		Example.Criteria criteria = example.createCriteria();
//		criteria.andEqualTo("name", userName).andEqualTo("status", 0);
//		if(schoolId!=null){
//			criteria.andEqualTo("schoolId",schoolId);
//		}
//		return mapper.selectOneByExample(example);
	return 	systemUserMapper.getUserByName(userName,schoolId);
	}
	@Override
	public SystemUser getUserByName(String userName) {
		List<SystemUser> systemUserList=systemUserMapper.getUserByName(userName,null);
		return systemUserList.get(0);
	}

	@Override
	public SystemUser getUserByPhone(String phone) {
		Example example = new Example(tClass);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("phone",phone);
		List<SystemUser> systemUsers = mapper.selectByExample(example);
		if(null!=systemUsers&&systemUsers.size()>0){
			return systemUsers.get(0);
		}else {
			return null;
		}
	}

	@Override
	public SystemUser getByTeacherId(Integer teacherId) {
		Example example = new Example(tClass);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("teacherId", teacherId);
		return mapper.selectOneByExample(example);
	}

	@Override
	public boolean addUser(SystemUser user, List<Integer> roles) {
		return false;
	}

	@Override
	public Object addUser(List<SystemUser> users) {
		return null;
	}

	@Override
	public boolean validateUserNameExists(String username,Integer userId) {
		Example example=new Example(tClass);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("name",username).andNotEqualTo("id",userId);
		List<SystemUser> systemUserList=mapper.selectByExample(example);
		if(systemUserList.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean validatePhoneExists(String phone) {
		Example example=new Example(tClass);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("phone",phone);
		List<SystemUser> systemUserList=mapper.selectByExample(example);
		if(systemUserList.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean validatePhoneExists(String phone, int userId) {
		Example example=new Example(tClass);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("phone",phone).andNotEqualTo("id",userId);
		List<SystemUser> systemUserList=mapper.selectByExample(example);
		if(systemUserList.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public int updateUser(SystemUser user, List<Integer> rid) {
		return 0;
	}

	@Override
	public MyPageInfo<SystemUser> getUserListByName(String name, PageTools page) {
		return null;
	}

	@Override
	public List<SystemUser> getUserListByName(String name, Integer schoolId) {
		return null;
	}

	@Override
	public MyPageInfo<SystemUser> getUserListByNoAdmin(Integer schoolId, String name, PageTools pageTools) {
		return null;
	}

	@Override
	public List<SystemUser> getUserListByName(String name, List<SystemRole> roles, PageTools page) {
		return null;
	}

	@Override
	public long getUserCountsByName(String name) {
		return 0;
	}

	@Override
	public long getUserCountsByName(String name, List<SystemRole> roles) {
		return 0;
	}

	@Override
	public boolean updateUserStatus(SystemUser user) {
		return false;
	}

	@Override
	public List<SystemUserRoleResponse> getAll(String search, List<String> roleCodes, Integer schoolId) {
		if (StringUtils.isNotEmpty(search)) {
			search = "%" + search + "%";
		}else{
			search=null;
		}
		return systemUserMapper.getSystemUserBySearch(search,roleCodes,schoolId);
	}

	@Override
	public List<SystemUser> getUserListByMobile(Collection<String> mobiles) {
		return null;
	}

	@Override
	public SystemUser findUserByName(String name) {
		return null;
	}

	public SystemUserRoleResponse getSystemUserRoleResponseById(Integer userId) {
		return systemUserMapper.getSystemUserRoleResponseById(userId);
	}

	/***
	 * 根据用户ID与学校ID删除用户角色管理
	 *
	 * @param userId
	 * @param schoolId
	 * @param notRoleId 不删除的角色
	 * @return
	 */
	public int deleteUserRoleByUserId(Integer userId, Integer schoolId,Integer notRoleId) {
		Example example = new Example(SystemRoleUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("uid", userId);
		criteria.andEqualTo("schoolId", schoolId);
		if(null!=notRoleId){
			criteria.andNotEqualTo("roleId",notRoleId);
		}
		return systemRoleUserMapper.deleteByExample(example);
	}

	/**
	 * 根据用户id与角色id与学校id删除用户角色对应关系
	 * @param userId
	 * @param roleId
	 * @param schoolId
	 * @return
	 */
	@Override
	public int deleteUserRoleByUserIdAndRoleId(Integer userId, Integer roleId, Integer schoolId) {
		Example example = new Example(SystemRoleUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("uid", userId);
		criteria.andEqualTo("schoolId", schoolId);
		criteria.andEqualTo("roleId", roleId);
		return systemRoleUserMapper.deleteByExample(example);
	}

	/**
	 * 根据用户id与角色id列表与学校id删除用户角色对应关系
	 * @param userIds
	 * @param roleId
	 * @param schoolId
	 * @return
	 */
	@Override
	public int deleteUserRoleByUserIdsAndRoleId(List<Integer> userIds, Integer roleId, Integer schoolId) {
		Example example = new Example(SystemRoleUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("uid", userIds);
		criteria.andEqualTo("schoolId", schoolId);
		criteria.andEqualTo("roleId", roleId);
		return systemRoleUserMapper.deleteByExample(example);
	}



	/**
	 * 插入用户角色关系表
	 * 
	 * @param systemRoleUser
	 * @return
	 */
	@Override
	public int insertUserRoll(SystemRoleUser systemRoleUser) {
		return systemRoleUserMapper.insert(systemRoleUser);
	}

	@Override
	public int deleteTeacherOrganByUserId(Integer userId, Integer schoolId) {
		return 0;
	}

	/**
	 * 批量插入用户角色关系表
	 * 
	 * @param systemRoleUserList
	 * @return
	 */
	@Override
	public int insertUserRolleList(List<SystemRoleUser> systemRoleUserList) {
		return systemRoleUserMapper.insertList(systemRoleUserList);
	}

	/**
	 * 根据手机号码查询用户
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public SystemUser selectUserByPhone(String phone) {
		Example example = new Example(SystemUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("phone", phone);
		List<SystemUser> systemUsers = mapper.selectByExample(example);
		if (null != systemUsers && systemUsers.size() > 0) {
			return systemUsers.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 校验教职工
	 */
	@Override
	public Boolean checkTeacher(Integer schoolId, String phone, Integer id) {
		Integer count = systemUserMapper.checkTeacher(schoolId, phone, id);
		if (count > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<String> selectAllUserPhone() {
		return null;
	}


	@Override
	public int enabledOrDisabled(Integer userId, Integer status) {

		return systemUserMapper.enabledOrDisabled(userId, status);
	}

	@Override
	public List<SystemUser> selectAllTeacherBySchoolId(Integer schoolId,String sreach) {
		Example example=new Example(tClass);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("schoolId",schoolId).andEqualTo("isTeacher",1);
		if(StringUtils.isNotEmpty(sreach)){
			Example.Criteria criteria1=example.and();
			criteria1.andLike("realName","%"+sreach+"%").orLike("phone","%"+sreach+"%");
		}
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}

	/**
	 * 根据角色代码和学校ID查询用户信息
	 * 
	 * @param schoolId
	 * @param roleCode
	 * @return
	 */
	public List<SystemUser> selectByRoleCodeAndSchoolId(Integer schoolId, String roleCode) {
		return systemUserMapper.selectByRoleCodeAndSchoolId(schoolId, roleCode);
	}

	@Override
	public List<String> selectClientIdsBySchoolId(Integer schoolId) {
		return null;
	}

	@Override
	public void createRoleBySchoolUser(Integer schoolId, Integer userId,Integer roleId) {
	}


	/**
	 * 根据学校,角色状态和角色代码查询用户列表
	 * 
	 * @param schoolIds
	 * @param roleCodes
	 * @return
	 */
	public List<SystemUser> selectBySchoolIdAndRoleCode(List<Integer> schoolIds, List<String> roleCodes,
			Integer status) {
		return systemUserMapper.selectBySchoolIdAndRoleCode(schoolIds, roleCodes, status);
	}

	@Override
	public List<SystemUser> selectByClassId(Integer classId, String name, Integer schoolId) {
		return null;
	}

	@Override
	public List<SystemUser> selectByClassIdIn(List<Integer> classIds, String name, Integer schoolId) {
		return null;
	}


	/**
	 * 批量插入用户
	 *
	 * @param systemUserList
	 * @return
	 */
	@Override
	public int insertList(List<SystemUser> systemUserList) {
		return systemUserMapper.insertList(systemUserList);
	}

	/**
	 * 根据部门ID查询老师
	 * @param organId
	 * @param schoolId
	 * @return
	 */
	public  List<SystemUser> selectByOrganId(Integer organId,Integer schoolId,String keyword){
		if(StringUtils.isNotEmpty(keyword)){
			keyword="%"+keyword+"%";
		}else{
			keyword=null;
		}
		return systemUserMapper.selectByOrganId(organId,schoolId,keyword);
	}

	@Override
	public List<SystemUser> selectTeacherForGroup(Integer groupId, Integer schoolId) {
		return null;
	}


	/**
	 * 根据用户ID查询用户clientIds
	 * @param userIds
	 * @return
	 */
	public List<String> selectClientIdsByUserIds(List<Integer> userIds){
		return systemUserMapper.selectClientIdsByUserIds(userIds);
	}

	/**
	 * 根据电话号码查询用户列表
	 * @param phone
	 * @return
	 */
	public List<SystemUser> selectUserListByPhone(String phone){
		Example example=new Example(tClass);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("phone",phone).andEqualTo("status",0);
		return mapper.selectByExample(example);
	}


	/**
	 *
	 * @param phone
	 * @return
	 */
	public List<SystemUser> selectTeacher(String phone,String schoolCode){
//		Example example=new Example(tClass);
//		Example.Criteria criteria=example.createCriteria();
//		criteria.andEqualTo("phone",phone).andEqualTo("isTeacher",1);

		return systemUserMapper.selectTeacher(phone,schoolCode);
	}
}
