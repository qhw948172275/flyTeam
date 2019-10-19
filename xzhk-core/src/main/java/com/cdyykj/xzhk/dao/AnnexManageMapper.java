package com.cdyykj.xzhk.dao;

import com.cdyykj.system.commons.service.MyMapper;
import com.cdyykj.xzhk.entity.AnnexManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnexManageMapper extends MyMapper<AnnexManage> {
    /**
     * 根据应用Id获取附件列表
     * @param applyId
     * @param type
     * @return
     */
    List<AnnexManage> selectAnnexManage(@Param("applyId") Integer applyId,@Param("type") Integer type);
}