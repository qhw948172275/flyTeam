package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.QuestionBankMapper;
import com.cdyykj.xzhk.entity.QuestionBank;
import com.cdyykj.xzhk.response.QuestionBankResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class QuestionBankService extends AbstractBaseCrudService<QuestionBank,Integer> {
    @Autowired
    QuestionBankMapper questionBankMapper;

    /**
     * 皮批量插入题
     * @param questionBanks
     */
    public void insertList(List<QuestionBank> questionBanks) {
        questionBankMapper.insertList(questionBanks);
    }

    /**
     * 根据测试系统ID获取详情
     * @param testSystemId
     * @return
     */
    public List<QuestionBankResponse> selectQuestionBankResponse(Integer testSystemId) {
       return questionBankMapper.selectQuestionBankResponse(testSystemId);
    }

    /**
     * 根据测试系统ID获取题列表
     * @param testSystemId
     * @return
     */
    public List<QuestionBank> selectListByTestSystemId(Integer testSystemId) {
        Example example=new Example(tClass);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("testSystemId",testSystemId);
        return mapper.selectByExample(example);
    }

    /**
     * 删除该系统的所有题
     * @param testSystemId
     */
    public void deleteByTestSystemId(Integer testSystemId) {
        Example example=new Example(tClass);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("testSystemId",testSystemId);
        mapper.deleteByExample(example);
    }
}
