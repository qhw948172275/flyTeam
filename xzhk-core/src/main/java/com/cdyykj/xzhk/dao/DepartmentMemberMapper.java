package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.DepartmentMember;
import com.cdyykj.xzhk.response.DepartmentMemberResponse;
import com.cdyykj.xzhk.response.MemberInfoResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMemberMapper extends MyMapper<DepartmentMember> {
    /**
     * 清空成员表
     */
    void deleteAll();

    /**
     *根据层级查询成员列表
     * @param levelPath
     * @return
     */
    List<DepartmentMemberResponse> selectListByLevelPath(@Param("levelPath") String levelPath);

    /**
     * 根据关键字搜索成员
     * @param keyword
     * @return
     */
    List<DepartmentMemberResponse> searchKeyword(@Param("keyword") String keyword);

    /**
     * 根据成员ID获取用户信息
     * @param memberId
     * @return
     */
    MemberInfoResponse selectMemberInfoResponse(@Param("memberId") Integer memberId);

    /**
     * 插入带有ID的成员信息
     * @param oldDepartmentMembers
     */
    void insertListById(@Param("oldDepartmentMembers") List<DepartmentMember> oldDepartmentMembers);

    /**
     * 查询管理员的userId
     * @return
     */
    List<String> selectUserId();
}