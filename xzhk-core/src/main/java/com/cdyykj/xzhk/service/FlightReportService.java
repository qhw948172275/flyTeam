package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.xzhk.dao.FlightReportMapper;
import com.cdyykj.xzhk.entity.AnnexManage;
import com.cdyykj.xzhk.entity.DepartmentMember;
import com.cdyykj.xzhk.entity.FlightReport;
import com.cdyykj.xzhk.entity.RAnnexApply;
import com.cdyykj.xzhk.response.FlightReportResponse;
import com.cdyykj.xzhk.tool.WxCpServiceTool;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightReportService extends AbstractBaseCrudService<FlightReport,Integer> {
    @Autowired
    FlightReportMapper flightReportMapper;
    @Autowired
    WxCpServiceTool wxCpServiceTool;
    /**
     * 查询飞行报告
     * @param keyword
     * @return
     */
    public List<FlightReportResponse> selectList(String keyword, String dateStr) {
        keyword= StringUtils.isNotEmpty(keyword)?"%"+keyword+"%":null;
        return flightReportMapper.selectList(keyword,dateStr);
    }

    /**
     * 查询飞行报告离别
     * @param memberId
     * @return
     */
    public List<FlightReport> selectPage(Integer memberId) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("createId",memberId);
        example.setOrderByClause("create_time desc");
        return mapper.selectByExample(example);
    }

    /**
     * 飞行报告保存
     * @param department
     * @param rAnnexApplyService
     */
    @Transactional
    public ObjectNode save(DepartmentMember department,FlightReportResponse flightReportResponse, RAnnexApplyService rAnnexApplyService) {
        ObjectNode json= JsonUtils.getMapperInstance().createObjectNode();
        try {
            FlightReport flightReport=new FlightReport();
            BeanUtils.copyProperties(flightReportResponse,flightReport);
            flightReport.setSubmitter(department.getName());
            flightReport.setCreateId(department.getId());
            flightReport.setCreateTime(CalendarUtils.getDate());
            flightReport.setStatus((byte)0);
            this.insert(flightReport);
            if(flightReportResponse.getAnnexManageList()!= null &&flightReportResponse.getAnnexManageList().size()>0){
                List<RAnnexApply> rAnnexApplies=this.getRAnnexApplyList(flightReportResponse.getAnnexManageList(),flightReport);
                rAnnexApplyService.insertList(rAnnexApplies);
            }

            wxCpServiceTool.sendMessageFlightReport(flightReport);
            json.put("result","ok");
            json.put("flightReportId",flightReport.getId());
            return json;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            json.put("result","fail");
            json.put("msg",e.getMessage());
            return json;
        }
    }

    /**
     * 构建附件与应用的关系集合
     * @param annexManages
     * @param flightReport
     * @return
     */
    private   List<RAnnexApply> getRAnnexApplyList(List<AnnexManage> annexManages, FlightReport flightReport){
        List<RAnnexApply> rAnnexApplies=new ArrayList<>(annexManages.size());
        RAnnexApply rAnnexApply;
        for(AnnexManage annexManage:annexManages){
            rAnnexApply=new RAnnexApply();
            rAnnexApply.setType((byte)1);//1-飞行报告
            rAnnexApply.setAnnexId(annexManage.getId());
            rAnnexApply.setApplyId(flightReport.getId());
            rAnnexApplies.add(rAnnexApply);
        }
        return rAnnexApplies;
    }
}
