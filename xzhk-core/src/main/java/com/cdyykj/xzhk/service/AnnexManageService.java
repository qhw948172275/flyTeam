package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.AnnexManageMapper;
import com.cdyykj.xzhk.entity.AnnexManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AnnexManageService extends AbstractBaseCrudService<AnnexManage,Integer> {

    @Autowired
    AnnexManageMapper annexManageMapper;
    /**
     * 批量更新附件的应用ID
     * @param annexIds
     * @param applyId
     */
    @Deprecated
    public void updateList(List<Integer> annexIds, Integer applyId) {
        Example example=new Example(tClass);
        Example.Criteria criteria= example.createCriteria();
        criteria.andIn("id",annexIds);
        AnnexManage annexManage=new AnnexManage();
        //annexManage.setApplyId(applyId);
        mapper.updateByExampleSelective(annexManage,example);
    }

    /**
     * 删除指定文章ID的附件
     * @param applyId
     * @param type 0-文章，1-飞行报告
     */
    @Deprecated
    public void deleteByapplyId(Integer applyId,Integer type) {
        Example example=new Example(tClass);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("applyId",applyId).andEqualTo("type",type);
        AnnexManage annexManage=new AnnexManage();
        annexManage.setStatus((byte)1);
        mapper.updateByExampleSelective(annexManage,example);
    }

    /**
     * 根据应用Id获取附件列表
     * @param applyId
     * @param type 0-文章，1-飞行报告
     * @return
     */
    public List<AnnexManage> selectAnnexManage(Integer applyId,Integer type) {

     return    annexManageMapper.selectAnnexManage(applyId,type);
    }
}
