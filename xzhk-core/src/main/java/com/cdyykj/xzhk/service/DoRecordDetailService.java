package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.DoRecordDetailMapper;
import com.cdyykj.xzhk.entity.DoRecordDetail;
import org.etsi.uri.x01903.v13.CRLIdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DoRecordDetailService extends AbstractBaseCrudService<DoRecordDetail,Integer> {
    @Autowired
    DoRecordDetailMapper doRecordDetailMapper;
    /**
     * 根据做题统计ID查询做题详情
     * @param doRecordIds
     * @return
     */
    public List<DoRecordDetail> selectByIds(List<Integer> doRecordIds) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andIn("doRecordId",doRecordIds);
        example.setOrderByClause("question_brank_id");
        return mapper.selectByExample(example);
    }

    /**
     * 批量查询
     * @param doRecordDetails
     */
    public void insertList(List<DoRecordDetail> doRecordDetails) {
        doRecordDetailMapper.insertList(doRecordDetails);
    }

    /**
     * 查询成员做题详情
     * @param questionIds
     * @param memberId
     * @return
     */
    public List<DoRecordDetail> selectByQuestionIds(List<Integer> questionIds, Integer memberId) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("memberId",memberId);
        criteria.andIn("questionBrankId",questionIds);
        example.setOrderByClause("id desc");
        return mapper.selectByExample(example);
    }
}
