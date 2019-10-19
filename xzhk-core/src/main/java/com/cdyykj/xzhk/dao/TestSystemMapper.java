package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.TestSystem;
import com.cdyykj.xzhk.response.TestSystemResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestSystemMapper extends MyMapper<TestSystem> {
    /**
     * 根据成员ID获取测试系统列表
     * @param memberId
     * @return
     */
    List<TestSystemResponse> selectTestSystemResponse(@Param("memberId") Integer memberId);
}