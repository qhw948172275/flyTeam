package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.RTestSystemMember;
import com.cdyykj.xzhk.response.MemberResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RTestSystemMemberMapper extends MyMapper<RTestSystemMember> {
    /**
     * 根据测试系统ID查询成员列表
     * @param testSystemId
     * @return
     */
    List<MemberResponse> selectMemberByTestSystemId(@Param("testSystemId") Integer testSystemId);
}