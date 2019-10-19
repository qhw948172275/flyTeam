package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.RTestSystemMemberMapper;
import com.cdyykj.xzhk.entity.RTestSystemMember;
import com.cdyykj.xzhk.response.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RTestSystemMemberService extends AbstractBaseCrudService<RTestSystemMember,Integer> {
    @Autowired
    RTestSystemMemberMapper rTestSystemMemberMapper;

    /**
     * 批量插入测试系统与成员关系
     * @param rTestSystemMembers
     */
    public void insertList(List<RTestSystemMember> rTestSystemMembers){
        rTestSystemMemberMapper.insertList(rTestSystemMembers);
    }

    /**
     * 更新成员已测试该测试系统
     * @param testSystemId
     * @param memberId
     */
    public void updatetsms(Integer testSystemId,Integer memberId){
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("testSystemId",testSystemId).andEqualTo("memberId",memberId);
        RTestSystemMember rTestSystemMember=new RTestSystemMember();
        rTestSystemMember.setIsTest((byte)1);//已测试
        mapper.updateByExampleSelective(rTestSystemMember,example);
    }

    /**
     * 删除该测试系统的所有成员
     * @param testSystemId
     */
     public void deleteByTestSystemId(Integer testSystemId ){
         Example example=new Example(tClass);
         Example.Criteria criteria=example.createCriteria();
         criteria.andEqualTo("testSystemId",testSystemId);
         mapper.deleteByExample(example);
     }

    /**
     * 根据测试系统ID查询成员列表
     * @param testSystemId
     * @return
     */
    public List<MemberResponse> selectMemberByTestSystemId(Integer testSystemId) {
         return rTestSystemMemberMapper.selectMemberByTestSystemId(testSystemId);
    }

    /**
     * 根据测试系统ID和成员ID查询成员做题
     * @param testSystemId
     * @param memberId
     * @return
     */
    public RTestSystemMember selectMemberByTestSystemIdandMemberId(Integer testSystemId, Integer memberId) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("testSystemId",testSystemId).andEqualTo("memberId",memberId);
        return mapper.selectOneByExample(example);
    }
}
