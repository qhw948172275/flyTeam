package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.DepartmentMemberMapper;
import com.cdyykj.xzhk.entity.DepartmentMember;
import com.cdyykj.xzhk.response.DepartmentMemberResponse;
import com.cdyykj.xzhk.response.MemberInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DepartmentMemberService extends AbstractBaseCrudService<DepartmentMember,Integer> {
    @Autowired
    DepartmentMemberMapper departmentMemberMapper;
    /**
     * 清空成员表
     */
    public void deleteAll() {
        departmentMemberMapper.deleteAll();
    }

    /**
     * 批量添加成员
     * @param departmentMembers
     */
    public void insertList(List<DepartmentMember> departmentMembers){
        departmentMemberMapper.insertList(departmentMembers);
    }
    /**
     *根据层级查询成员列表
     * @param levelPath
     * @return
     */
    public List<DepartmentMemberResponse> selectListByLevelPath(String levelPath) {
        return departmentMemberMapper.selectListByLevelPath(levelPath);
    }

    /**
     * 根据关键字搜索成员
     * @param keyword
     * @return
     */
    public List<DepartmentMemberResponse> searchKeyword(String keyword){
        keyword= StringUtils.isNotEmpty(keyword)?"%"+keyword+"%":null;
        return departmentMemberMapper.searchKeyword(keyword);
    }

    /**
     * 根据userID获取成员信息
     * @param userId
     */
    public DepartmentMember selectByUserId(String userId) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("userId",userId);
        return mapper.selectOneByExample(example);
    }

    /**
     * 根据成员ID获取用户信息
     * @param memberId
     * @return
     */
    public MemberInfoResponse selectMemberInfoResponse(Integer memberId) {
        return departmentMemberMapper.selectMemberInfoResponse(memberId);
    }

    /**
     * 插入带有ID的成员信息
     * @param oldDepartmentMembers
     */
    public void insertListById(List<DepartmentMember> oldDepartmentMembers) {
         departmentMemberMapper.insertListById(oldDepartmentMembers);
    }

    /**
     * 查询管理员的UserId
     */
    public List<String> selectUserId() {
        return departmentMemberMapper.selectUserId();
    }
}
