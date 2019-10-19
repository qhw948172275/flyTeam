package com.cdyykj.xzhk.controller.api.wechat;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.annotation.MemberLoginRequired;
import com.cdyykj.xzhk.controller.api.BaseController;
import com.cdyykj.xzhk.dto.AnswerListDto;
import com.cdyykj.xzhk.entity.*;
import com.cdyykj.xzhk.response.*;
import com.cdyykj.xzhk.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api(description = "测试系统")
@RestController(value="memberTestSystemController")
@RequestMapping(value = "wechat/member/testSystem")
public class TestSystemController extends BaseController {
    @Autowired
    TestSystemService testSystemService;
    @Autowired
    QuestionBankService questionBankService;
    @Autowired
    DoRecordService doRecordService;
    @Autowired
    DoRecordDetailService doRecordDetailService;
    @Autowired
    RTestSystemMemberService rTestSystemMemberService;
    /**
     * 获取测试系统列表
     * @return
     */
    @GetMapping(value="testSystemList")
    @ApiOperation(value = "获取测试系统列表",response = TestSystemResponse.class)
    @MemberLoginRequired
    public JsonResult testSystemList(HttpServletRequest request, @RequestParam(required =false,defaultValue = "1") int page
            , @RequestParam(required =false,defaultValue = "10") int limit){
        try {
            ObjectNode json = JsonUtils.getMapperInstance().createObjectNode();
            Integer memberId=getDepartmentMember(request).getId();
            PageHelper.startPage(page,limit);
            List<TestSystemResponse> testSystemResponses=testSystemService.selectTestSystemResponse(memberId);
            json.putPOJO("testSystemResponses",new PageInfo<>(testSystemResponses));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 根据测试系统ID获取提详情
     * @return
     */
    @ApiOperation(value="根据测试系统ID获取题详情",response =QuestionBankResponse.class )
    @ApiImplicitParam(value = "测试系统ID",name = "testSystemId")
    @GetMapping(value="getTestSystemQuestionDetail")
    @MemberLoginRequired
    public JsonResult getTestSystemQuestionDetail(HttpServletRequest request,@RequestParam Integer testSystemId,@RequestParam(value="page",defaultValue="1")Integer page,
                                                  @RequestParam(value="limit",defaultValue="10")Integer limit){
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
     * 提交答案
     * @param answerListDto
     * @return
     */
    @PostMapping(value = "submitAnswer")
    @ApiOperation(value = "提交答案",response = DoRecord.class)
    @MemberLoginRequired
    public JsonResult submitAnswer(HttpServletRequest request,@RequestBody AnswerListDto answerListDto){
        try {
            if(answerListDto.getMemberAnswerDtos().size()>0) {
                Integer memberId = getDepartmentMember(request).getId();
                JsonNode json = testSystemService.submitAnswer(questionBankService,answerListDto,rTestSystemMemberService,memberId,doRecordService,doRecordDetailService);
                if(json.get("type").textValue().equals("ok")){
                    return JsonResultUtils.buildJsonOK(json);
                }
                if(json.get("type").textValue().equals("fail")){
                    return  JsonResultUtils.buildJsonFail(json);
                }
            }
            return JsonResultUtils.buildJsonFailMsg("无效提交");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 查看测试详情
     * @return
     */
    @GetMapping(value="testDetail")
    @ApiOperation(value = "查看测试详情",response =QuestionBankResponse.class )
    public JsonResult testDetail(HttpServletRequest request,@RequestParam Integer testSystemId,@RequestParam(value="page",defaultValue="1")Integer page,
                                 @RequestParam(value="limit",defaultValue="10")Integer limit){
        try {
            ObjectNode json =JsonUtils.getMapperInstance().createObjectNode();
            PageHelper.startPage(page,limit);

            List<QuestionBankResponse> questionBankResponses=questionBankService.selectQuestionBankResponse(testSystemId);
            List<Integer> questionId=new ArrayList<>();
            for(QuestionBankResponse questionBankResponse:questionBankResponses){
                List<QuestionResponse> questionResponses=JsonUtils.jsonStrToBean(questionBankResponse.getContent(),QuestionResponse.class);
                questionBankResponse.setQuestionResponses(questionResponses);
                List<Integer> resultResponses=JsonUtils.jsonStrToBean(questionBankResponse.getChooseResult(),Integer.class);
                questionBankResponse.setResultResponses(resultResponses);
                questionId.add(questionBankResponse.getId());
            }
            Integer memberId=getDepartmentMember(request).getId();
            //获取成员做的所有题
            List<DoRecordDetail> doRecordDetails=doRecordDetailService.selectByQuestionIds(questionId,memberId);
            //按题ID分类
            Map<Integer,DoRecordDetailResponse> integerListMap=new HashMap<>();
            for(DoRecordDetail doRecordDetail:doRecordDetails){
                DoRecordDetailResponse doRecordDetailResponse=new DoRecordDetailResponse();
                BeanUtils.copyProperties(doRecordDetail,doRecordDetailResponse);
                doRecordDetailResponse.setResults(JsonUtils.jsonStrToBean(doRecordDetail.getAnswer(),String.class));
                integerListMap.put(doRecordDetail.getQuestionBrankId(),doRecordDetailResponse);
            }
            for(QuestionBankResponse questionBankResponse:questionBankResponses){
                if(integerListMap.containsKey(questionBankResponse.getId())){
                    questionBankResponse.setDoRecordDetailResponse(integerListMap.get(questionBankResponse.getId()));
                }
            }
            json.putPOJO("questionBankResponses",new PageInfo<>(questionBankResponses));
            RTestSystemMember rTestSystemMember=rTestSystemMemberService.selectMemberByTestSystemIdandMemberId(testSystemId,memberId);
            json.putPOJO("rTestSystemMember",rTestSystemMember);
            List<DoRecord> doRecords=doRecordService.selectForMemberIdAndTestSystemId(memberId,testSystemId);
            json.putPOJO("doRecord",doRecords.get(0));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 开始时间
     * @return
     */
    @ApiOperation(value = "开始时间")
    @PostMapping(value = "startTime")
    public JsonResult startTime(@RequestBody TestSystem testSystem){
        try {
            TestSystem testSystem1=testSystemService.getById(testSystem.getId());
            testSystem1.setStartTime(testSystem.getStartTime());
            testSystemService.updateById(testSystem1);
            return JsonResultUtils.buildJsonOK();
        } catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }
}
