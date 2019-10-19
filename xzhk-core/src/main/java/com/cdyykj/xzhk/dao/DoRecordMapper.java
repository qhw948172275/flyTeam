package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.DoRecord;
import com.cdyykj.xzhk.response.DoRecordResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoRecordMapper extends MyMapper<DoRecord> {
    /**
     * 根据测试系统ID获取测试详情
     * @param testSystemId
     * @return
     */
    List<DoRecordResponse> selectDoRecordResponseTwo(@Param("testSystemId") Integer testSystemId,@Param("keyword")String keyword,@Param("ids") List<Integer> ids);
}