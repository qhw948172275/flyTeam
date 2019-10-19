package com.cdyykj.xzhk.controller.api.platform;

import com.cdyykj.commons.OutPutFolderConfig;
import com.cdyykj.commons.excel.BaseUtils;
import com.cdyykj.commons.task.JobUtils;
import com.cdyykj.commons.task.TestSystemJob;
import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.ConvertUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.controller.api.BaseController;
import com.cdyykj.xzhk.dto.TestSystemStatusDto;
import com.cdyykj.xzhk.entity.DoRecordDetail;
import com.cdyykj.xzhk.entity.QuestionBank;
import com.cdyykj.xzhk.entity.RTestSystemMember;
import com.cdyykj.xzhk.entity.TestSystem;
import com.cdyykj.xzhk.response.*;
import com.cdyykj.xzhk.service.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "测试系统管理")
@RestController
@RequestMapping(value = "api/testSystem")
public class TestSystemController extends BaseController {
    @Autowired
    TestSystemService testSystemService;
    @Autowired
    RTestSystemMemberService rTestSystemMemberService;
    @Autowired
    QuestionBankService questionBankService;
    @Autowired
    DoRecordService doRecordService;
    @Autowired
    DoRecordDetailService doRecordDetailService;
    @Autowired
    JobUtils jobUtils;
    @Autowired
    OutPutFolderConfig outPutFolderConfig;
    /**
     * 测试系统列表
     * @return
     */
    @ApiOperation(value = "测试系统列表",response = TestSystem.class)
    @GetMapping(value = "index")
    public JsonResult index(@RequestParam(value="page",defaultValue="1")Integer page,
                            @RequestParam(value="limit",defaultValue="20")Integer limit, String keyword){
        try {
            ObjectNode json = JsonUtils.getMapperInstance().createObjectNode();
            PageHelper.startPage(page,limit);
            List<TestSystem> testSystems=testSystemService.selectPage(keyword,null);
            json.putPOJO("testSystems",new PageInfo<>(testSystems));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 测试系统保存
     * @return
     */
    @ApiOperation(value = "测试系统保存")
    @PostMapping(value = "save")
    public JsonResult save(@RequestBody TestSystemQuestionResponse testSystemQuestionResponse){
            try {
                Integer marks=0;//总份数

                TestSystem testSystem=new TestSystem();
                BeanUtils.copyProperties(testSystemQuestionResponse,testSystem);
                testSystem.setCreateTime(CalendarUtils.getDate());
                testSystem.setCreateId(getSystemUser().getId());
                testSystem.setQuestionCount((byte)testSystemQuestionResponse.getQuestionBankResponses().size());
                testSystem.setReceiveNumber(testSystemQuestionResponse.getMemberResponses().size());
                if(testSystem.getStatus().intValue()==1){//直接发布
                    testSystem.setPushTime(CalendarUtils.getDate());
                }
                testSystemService.insert(testSystem);

                if(testSystem.getStatus().intValue()==3){//定时发布测试系统
                    jobUtils.addCron(TestSystemJob.class.getName(),"TestSystemJob:"+testSystem.getId(),testSystemQuestionResponse.getPushTime(),JsonUtils.beanToJson(testSystem),0);
                }

                if(testSystemQuestionResponse.getMemberResponses().size()>0){
                    List<RTestSystemMember> rTestSystemMembers=new ArrayList<>();
                    for(MemberResponse memberResponse:testSystemQuestionResponse.getMemberResponses()){
                        RTestSystemMember rTestSystemMember=new RTestSystemMember();
                        rTestSystemMember.setMemberId(memberResponse.getId());
                        rTestSystemMember.setTestSystemId(testSystem.getId());
                        rTestSystemMember.setIsTest((byte)0);//0-未测试
                        rTestSystemMember.setIsPass((byte)0);//0-未合格
                        rTestSystemMembers.add(rTestSystemMember);
                    }
                    rTestSystemMemberService.insertList(rTestSystemMembers) ;
                }
                if(testSystemQuestionResponse.getQuestionBankResponses().size()>0){
                    List<QuestionBank> questionBanks=new ArrayList<>();
                    for(QuestionBankResponse questionBankResponse:testSystemQuestionResponse.getQuestionBankResponses()){
                        List<QuestionResponse> questionResponses=questionBankResponse.getQuestionResponses();
                        String content=JsonUtils.beanToJson(questionResponses);
                        List<Integer> resultResponses=questionBankResponse.getResultResponses();
                        String chooseResult=JsonUtils.beanToJson(resultResponses);
                        QuestionBank questionBank=new QuestionBank();
                        BeanUtils.copyProperties(questionBankResponse,questionBank);
                        marks+=questionBank.getMark();
                        questionBank.setChooseResult(chooseResult);
                        questionBank.setContent(content);
                        questionBank.setTestSystemId(testSystem.getId());
                        questionBanks.add(questionBank);
                    }
                    questionBankService.insertList(questionBanks);
                }
                testSystem.setMarks(marks);
                testSystemService.updateById(testSystem);
                return JsonResultUtils.buildJsonOK();
            }catch (Exception e){
                e.printStackTrace();
                return JsonResultUtils.buildJsonFailMsg(e.getMessage());
            }
    }

    /**
     * 编辑测试系统保存
     * @return
     */
    @ApiOperation(value = "编辑测试系统保存")
    @PostMapping(value = "edit")
    public JsonResult edit(@RequestBody TestSystemQuestionResponse testSystemQuestionResponse){
        try {
            TestSystem testSystem=new TestSystem();
            BeanUtils.copyProperties(testSystemQuestionResponse,testSystem);
            testSystem.setQuestionCount((byte)testSystemQuestionResponse.getQuestionBankResponses().size());
            testSystem.setReceiveNumber(testSystemQuestionResponse.getMemberResponses().size());
            //删除定时任务
            jobUtils.jobDelete(TestSystemJob.class.getName(),"TestSystemJob:"+testSystem.getId());
            if(testSystem.getStatus().intValue()==3){//定时发布测试系统
                jobUtils.addCron(TestSystemJob.class.getName(),"TestSystemJob:"+testSystem.getId(),testSystemQuestionResponse.getPushTime(),JsonUtils.beanToJson(testSystem),0);
            }else if(testSystem.getStatus().intValue()==1){//直接发布
                testSystem.setPushTime(CalendarUtils.getDate());
            }
            testSystemService.updateById(testSystem);
            if(testSystemQuestionResponse.getMemberResponses().size()>0){
                List<RTestSystemMember> rTestSystemMembers=new ArrayList<>();
                for(MemberResponse memberResponse:testSystemQuestionResponse.getMemberResponses()){
                    RTestSystemMember rTestSystemMember=new RTestSystemMember();
                    rTestSystemMember.setMemberId(memberResponse.getId());
                    rTestSystemMember.setTestSystemId(testSystem.getId());
                    rTestSystemMember.setIsTest((byte)0);//0-未测试
                    rTestSystemMembers.add(rTestSystemMember);
                }
                //删除该测试系统的所有成员
                rTestSystemMemberService.deleteByTestSystemId(testSystem.getId());
                rTestSystemMemberService.insertList(rTestSystemMembers) ;
            }
            if(testSystemQuestionResponse.getQuestionBankResponses().size()>0){
                List<QuestionBank> questionBanks=new ArrayList<>();
                for(QuestionBankResponse questionBankResponse:testSystemQuestionResponse.getQuestionBankResponses()){
                    List<QuestionResponse> questionResponses=questionBankResponse.getQuestionResponses();
                    String content=JsonUtils.beanToJson(questionResponses);
                    List<Integer> resultResponses=questionBankResponse.getResultResponses();
                    String chooseResult=JsonUtils.beanToJson(resultResponses);
                    QuestionBank questionBank=new QuestionBank();
                    BeanUtils.copyProperties(questionBankResponse,questionBank);
                    questionBank.setChooseResult(chooseResult);
                    questionBank.setContent(content);
                    questionBank.setTestSystemId(testSystem.getId());
                    questionBanks.add(questionBank);
                }
                //删除该系统的所有题
                questionBankService.deleteByTestSystemId(testSystem.getId());
                questionBankService.insertList(questionBanks);
            }
            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }



    /**
     * 根据测试系统ID获取提详情
     * @return
     */
    @ApiOperation(value="根据测试系统ID获取题详情",response = QuestionBankResponse.class)
    @ApiImplicitParam(value = "测试系统ID",name = "testSystemId")
    @GetMapping(value="getTestSystemQuestionDetail")
    public JsonResult getTestSystemQuestionDetail(@RequestParam Integer testSystemId,@RequestParam(value="page",defaultValue="1")Integer page,
                                          @RequestParam(value="limit",defaultValue="20")Integer limit){
        try {
            ObjectNode json =JsonUtils.getMapperInstance().createObjectNode();
            PageHelper.startPage(page,limit);
            List<QuestionBankResponse> questionBankResponses=questionBankService.selectQuestionBankResponse(testSystemId);
             for(QuestionBankResponse questionBankResponse:questionBankResponses){
                 List<QuestionResponse> questionResponses=JsonUtils.jsonStrToBean(questionBankResponse.getContent(),QuestionResponse.class);
                 questionBankResponse.setQuestionResponses(questionResponses);
                 List<Integer> resultResponses=JsonUtils.jsonStrToBean(questionBankResponse.getChooseResult(),Integer.class);
                 questionBankResponse.setResultResponses(resultResponses);
             }
            json.putPOJO("questionBankResponses",new PageInfo<>(questionBankResponses));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 根据测试系统ID获取测试详情
     * @return
     */
    @GetMapping(value = "getTestDetail")
    @ApiOperation(value="根据测试系统ID获取测试详情",response = DoRecordResponse.class)
    @ApiImplicitParam(value = "测试系统ID",name = "testSystemId")
    public JsonResult getTestDetail(@RequestParam Integer testSystemId,@RequestParam(required =false,defaultValue = "1") int page
            , @RequestParam(required =false,defaultValue = "20") int limit,String keyword){
        try {
            ObjectNode json =JsonUtils.getMapperInstance().createObjectNode();
            PageHelper.startPage(page,limit);
            List<DoRecordResponse> doRecords=doRecordService.selectDoRecordResponse(testSystemId,keyword,null);
            if(doRecords.size()>0){
                List<Integer> doRecordIds=new ArrayList<>();
                for(DoRecordResponse doRecordResponse:doRecords){
                    doRecordIds.add(doRecordResponse.getId());
                }
                List<DoRecordDetail> doRecordDetails=doRecordDetailService.selectByIds(doRecordIds);
                Map<Integer,List<DoRecordDetail>> doMap=new HashMap<>();
                for(DoRecordDetail detail:doRecordDetails){
                    if(doMap.containsKey(detail.getMemberId())){
                        doMap.get(detail.getMemberId()).add(detail);
                    }else{
                        List<DoRecordDetail> detailList=new ArrayList<>();
                        detailList.add(detail);
                        doMap.put(detail.getMemberId(),detailList);
                    }
                }
                for(DoRecordResponse doRecordResponse:doRecords){
                    if(doMap.containsKey(doRecordResponse.getMemberId())){
                        doRecordResponse.setDoRecordDetails(doMap.get(doRecordResponse.getMemberId()));
                    }
                }
            }
            json.putPOJO("doRecords",new PageInfo<>(doRecords));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 更改测试系统的状态
     * @param testSystemStatusDto
     * @return
     */
    @PostMapping(value = "changeTestSystem")
    @ApiOperation(value = "更改测试系统的状态")
    public JsonResult changeTestSystem(@RequestBody TestSystemStatusDto testSystemStatusDto){
        try {
            TestSystem testSystem=new TestSystem();
            testSystem.setId(testSystemStatusDto.getTestSystemId());
            testSystem.setStatus(testSystemStatusDto.getStatus());
            testSystemService.updateById(testSystem);
            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 根据测试系统ID获取测试系统详情
     * @return
     */
    @GetMapping(value = "testSystemDetail")
    @ApiOperation(value = "根据测试系统ID获取测试系统详情")
    public  JsonResult testSystemDetail(@RequestParam Integer testSystemId ){
        try {
            TestSystem testSystem=testSystemService.getById(testSystemId);
            TestSystemQuestionResponse testSystemQuestionResponse=new TestSystemQuestionResponse();
            BeanUtils.copyProperties(testSystem,testSystemQuestionResponse);
            List<QuestionBankResponse> questionBankResponses=questionBankService.selectQuestionBankResponse(testSystemId);
            for(QuestionBankResponse questionBankResponse:questionBankResponses){
                List<QuestionResponse> questionResponses=JsonUtils.jsonStrToBean(questionBankResponse.getContent(),QuestionResponse.class);
                questionBankResponse.setQuestionResponses(questionResponses);
                List<Integer> resultResponses=JsonUtils.jsonStrToBean(questionBankResponse.getChooseResult(),Integer.class);
                questionBankResponse.setResultResponses(resultResponses);
            }
            testSystemQuestionResponse.setQuestionBankResponses(questionBankResponses);
            List<MemberResponse>  memberResponses= rTestSystemMemberService.selectMemberByTestSystemId(testSystemId);
            testSystemQuestionResponse.setMemberResponses(memberResponses);
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            json.putPOJO("testSystemQuestionResponse",testSystemQuestionResponse);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }

    }

    /**
     * 导出测试情况
     * @return
     */
    @GetMapping(value = "output")
    @ApiOperation(value = "导出测试情况,默认全部导出，批量带出以，拼接")
    public JsonResult output(@RequestParam Integer testSystemId,String ids){
        ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
        try {
            List<DoRecordResponse> doRecords;
            List<Integer> doreIds;
            if(StringUtils.isNotEmpty(ids)){
                doreIds= ConvertUtils.convertString(ids);
                doRecords=doRecordService.selectDoRecordResponse(testSystemId,null,doreIds);
            }else{
                doRecords=doRecordService.selectDoRecordResponse(testSystemId,null,null);
                doreIds=new ArrayList<>(doRecords.size());
                for(DoRecordResponse doRecordResponse:doRecords){
                    doreIds.add(doRecordResponse.getId());
                }
             }
            if(!doRecords.isEmpty()){
                List<DoRecordDetail> doRecordDetails=doRecordDetailService.selectByIds(doreIds);
                Map<Integer,List<DoRecordDetail>> doMap=new HashMap<>();
                for(DoRecordDetail detail:doRecordDetails){
                    if(doMap.containsKey(detail.getMemberId())){
                        doMap.get(detail.getMemberId()).add(detail);
                    }else{
                        List<DoRecordDetail> detailList=new ArrayList<>();
                        detailList.add(detail);
                        doMap.put(detail.getMemberId(),detailList);
                    }
                }
                for(DoRecordResponse doRecordResponse:doRecords){
                    if(doMap.containsKey(doRecordResponse.getMemberId())){
                        doRecordResponse.setDoRecordDetails(doMap.get(doRecordResponse.getMemberId()));
                    }
                }
                String[] titles=getTitles(doRecordDetails.size());
                List<List<String>> lists=new ArrayList<>(doRecords.size());
                List<String> stringList;
                for(DoRecordResponse doRecordResponse:doRecords){
                    stringList=new ArrayList<>();
                    stringList.add(doRecordResponse.getMemberName());
                    stringList.add(doRecordResponse.getDptName());
                    stringList.add(doRecordResponse.getMarks().toString());
                    for(DoRecordDetail doRecordDetail:doRecordResponse.getDoRecordDetails()){
                        if(doRecordDetail.getIsRight().intValue()==1){
                            stringList.add("√");
                        }else{
                            stringList.add("×");
                        }
                    }
                    lists.add(stringList);
                }
                String[] regionTitle = new String[] {"成员测试详情", CalendarUtils.dateToString(CalendarUtils.getDate(),CalendarUtils.yyyy_MM_dd__HH_mm_ss)};
                String fileName = BaseUtils.outputExcel("成员测试详情",regionTitle,titles,lists,outPutFolderConfig.getPrefix(),false);
                json.put("fileName",fileName);
                return JsonResultUtils.buildJsonOK(json);
            }

            return JsonResultUtils.buildJsonFail();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 获取做题详情的excel标题
     * @return
     */
    private String[] getTitles(int length){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("姓名").append(",").append("所属部门").append(",").append("分数").append(",");
        for (int i=0;i<length;i++){
            stringBuffer.append("第"+(i+1)+"题");
            if(i+1<length){
                stringBuffer.append(",");
            }
        }
        String[] titles=stringBuffer.toString().split(",");
        return titles;
    }

    /**
     * 测试系统列表导出
     * @return
     */
    @GetMapping(value = "listOutput")
    public JsonResult listOutput(){
        ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
        try {
            List<TestSystem> testSystems=testSystemService.selectPage(null,1);
            String[] titles={"名称","发布日期","接收人数","及格人数","总分","及格分"};
            List<List<String>> lists=new ArrayList<>(testSystems.size());
            for(TestSystem testSystem:testSystems){
                List<String> stringList=new ArrayList<>(titles.length);
                stringList.add(testSystem.getName());
                stringList.add(CalendarUtils.dateToString(testSystem.getCreateTime(),CalendarUtils.yyyy_MM_dd__HH_mm_ss));
                stringList.add(testSystem.getReceiveNumber().toString());
                stringList.add(testSystem.getPassCount().toString());
                stringList.add(testSystem.getMarks().toString());
                stringList.add(testSystem.getPassMark().toString());
                lists.add(stringList);
            }
            String[] regionTitle = new String[] {"测试系统列表", CalendarUtils.dateToString(CalendarUtils.getDate(),CalendarUtils.yyyy_MM_dd__HH_mm_ss)};
            String fileName = BaseUtils.outputExcel("测试系统列表",regionTitle,titles,lists,outPutFolderConfig.getPrefix(),false);
            json.put("fileName",fileName);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }
}
