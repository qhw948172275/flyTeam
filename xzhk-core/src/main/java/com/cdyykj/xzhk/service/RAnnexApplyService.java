package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.RAnnexApplyMapper;
import com.cdyykj.xzhk.entity.RAnnexApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RAnnexApplyService extends AbstractBaseCrudService<RAnnexApply,Integer> {
    @Autowired
    RAnnexApplyMapper rAnnexApplyMapper;

    /**
     * 批量插入应用与附件的关系
     * @param rAnnexApplyList
     */
    public void insertList(List<RAnnexApply> rAnnexApplyList){
        rAnnexApplyMapper.insertList(rAnnexApplyList);
    }

    /**
     * 根据应用ID类型是删除与附件的关系
     * @param id
     * @param i
     */
    public void deleteByApplyIdAndType(Integer id, int i) {
        RAnnexApply rAnnexApply=new RAnnexApply();
        rAnnexApply.setApplyId(id);
        rAnnexApply.setType((byte)i);
        mapper.delete(rAnnexApply);
    }
}
