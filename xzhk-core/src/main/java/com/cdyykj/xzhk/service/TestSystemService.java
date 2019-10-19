package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.TestSystemMapper;
import com.cdyykj.xzhk.dto.AnswerListDto;
import com.cdyykj.xzhk.dto.MemberAnswerDto;
import com.cdyykj.xzhk.entity.*;
import com.cdyykj.xzhk.response.TestSystemResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class TestSystemService extends AbstractBaseCrudService<TestSystem,Integer> {
    @Autowired
    TestSystemMapper testSystemMapper;
    /**
     * 查询测试系统列表
     * @param keyword
     * @return
     */
    public List<TestSystem> selectPage(String keyword,Integer status) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andNotEqualTo("status",2);//2--排除删除
        if(StringUtils.isNotEmpty(keyword)){
            criteria.andLike("name","%"+keyword+"%");
        }
        if(status!=null){
            criteria.andEqualTo("status",status);
        }
        example.setOrderByClause("create_time desc");
        return mapper.selectByExample(example);
    }

    /**
     * 根据成员ID获取测试系统列表
     * @param memberId
     * @return
     */
    public List<TestSystemResponse> selectTestSystemResponse(Integer memberId) {
        return testSystemMapper.selectTestSystemResponse(memberId);
    }

    /**
     *
     * @param testSystem
     * @param fireTime
     */
    public void updateSendTime(TestSystem testSystem, Date fireTime) {
        testSystem.setStatus((byte)1);
        testSystemMapper.updateByPrimaryKeySelective(testSystem);
    }

    /**
     * 验证答案
     * @param questionBankService
     * @return
     */
    @Transactional
    public JsonNode submitAnswer(QuestionBankService questionBankService, AnswerListDto answerListDto,
                                 RTestSystemMemberService rTestSystemMemberService,Integer memberId,
                                DoRecordService doRecordService,DoRecordDetailService doRecordDetailService) {
        ObjectNode json =JsonUtils.getMapperInstance().createObjectNode();
        try {
            List<QuestionBank> questionBanks=questionBankService.selectListByTestSystemId(answerListDto.getTestSystemId());

            TestSystem testSystem=this.getById(answerListDto.getTestSystemId());

            Map<Integer,QuestionBank> questionBankMap=new HashMap<>();
            for(QuestionBank questionBank:questionBanks){
                questionBankMap.put(questionBank.getId(),questionBank);
            }
            List<DoRecordDetail> doRecordDetails=new ArrayList<>();//做题详情

            DoRecord doRecord=new DoRecord();
            Integer mCount=0;//分数统计
            Integer rightCount=0;//正确题统计
            Integer errorCount=0;//错误题统计
            for(MemberAnswerDto memberAnswerDto:answerListDto.getMemberAnswerDtos()){
                if(questionBankMap.containsKey(memberAnswerDto.getQuestionId())){
                    QuestionBank questionBank=questionBankMap.get(memberAnswerDto.getQuestionId());
                    //获取题的正确答案
                    List<String> responses= JsonUtils.jsonStrToBean(questionBank.getChooseResult(),String.class);
                    DoRecordDetail doRecordDetail=new DoRecordDetail();//做题详情
                    doRecordDetail.setAnswer(JsonUtils.beanToJson(memberAnswerDto.getResultResponses()));
                    doRecordDetail.setMemberId(memberId);
                    doRecordDetail.setQuestionBrankId(questionBank.getId());
                    if(responses.size()==memberAnswerDto.getResultResponses().size()){//先判断成员答案和正确答案的长度
                        Collections.sort(responses);
                        Collections.sort(memberAnswerDto.getResultResponses());
                        String rightAnswer= StringUtils.listToString(responses).toLowerCase();
                        String memberAnswer=StringUtils.listToString(memberAnswerDto.getResultResponses()).toLowerCase();
                        if(rightAnswer.equals(memberAnswer)){
                            doRecordDetail.setIsRight((byte)1);//0-否,1-是
                            doRecordDetail.setMark(questionBank.getMark().byteValue());
                            mCount+=questionBank.getMark();
                            rightCount+=1;
                        }else {
                            doRecordDetail.setIsRight((byte)0);//0-否,1-是
                            errorCount+=1;
                        }
                    }else{
                        doRecordDetail.setIsRight((byte)0);//0-否,1-是
                        errorCount+=1;
                    }
                    doRecordDetails.add(doRecordDetail);

                }
            }

            doRecord.setMarks(mCount);
            if(doRecord.getMarks().intValue()>=testSystem.getPassMark().intValue()){
                testSystem.setPassCount(testSystem.getPassCount().intValue()+1);
                RTestSystemMember rTestSystemMember=rTestSystemMemberService.selectMemberByTestSystemIdandMemberId(testSystem.getId(),memberId);
                rTestSystemMember.setIsPass((byte)1);//1--通过
                rTestSystemMemberService.updateById(rTestSystemMember);
            }
            doRecord.setErrorCount(errorCount.byteValue());
            doRecord.setRightCount(rightCount.byteValue());
            doRecord.setMemberId(memberId);
            doRecord.setTestSystemId(answerListDto.getTestSystemId());
            doRecordService.insert(doRecord);
            for(DoRecordDetail doRecordDetail:doRecordDetails){
                doRecordDetail.setDoRecordId(doRecord.getId());
            }
            doRecordDetailService.insertList(doRecordDetails);
            //更新成员测试状态
            rTestSystemMemberService.updatetsms(answerListDto.getTestSystemId(),memberId);
            this.updateById(testSystem);//更新测试统计
            json.putPOJO("doRecord",doRecord);
            json.put("type","ok");
            json.put("msg","提交成功");
           return json;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            json.put("type","fail");
            json.put("msg",e.getMessage());
            return json;
        }

    }

}
