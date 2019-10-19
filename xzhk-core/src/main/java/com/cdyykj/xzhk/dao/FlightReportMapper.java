package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.FlightReport;
import com.cdyykj.xzhk.response.FlightReportResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlightReportMapper extends MyMapper<FlightReport> {
    /**
     * 查询飞行报告
     * @param keyword
     * @return
     */
    List<FlightReportResponse> selectList(@Param("keyword") String keyword, @Param("dateStr")String dateStr);
}