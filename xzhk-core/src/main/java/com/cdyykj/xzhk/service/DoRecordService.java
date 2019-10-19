package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.DoRecordMapper;
import com.cdyykj.xzhk.entity.DoRecord;
import com.cdyykj.xzhk.response.DoRecordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DoRecordService extends AbstractBaseCrudService<DoRecord,Integer> {
    @Autowired
    DoRecordMapper doRecordMapper;
    /**
     * 根据测试系统ID获取测试详情
     * @param testSystemId
     * @return
     */
    public List<DoRecordResponse> selectDoRecordResponse(Integer testSystemId,String keyword,List<Integer> ids) {
        keyword= StringUtils.isNotEmpty(keyword)?"%"+keyword+"%":null;
      return   doRecordMapper.selectDoRecordResponseTwo(testSystemId,keyword,ids);
    }

    /**
     * 获取总分
     * @param memberId
     * @param testSystemId
     * @return
     */
    public List<DoRecord> selectForMemberIdAndTestSystemId(Integer memberId, Integer testSystemId) {
        Example example=new Example(tClass);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("memberId",memberId).andEqualTo("testSystemId",testSystemId);
        return mapper.selectByExample(example);
    }
}
