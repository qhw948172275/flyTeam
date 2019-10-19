package com.cdyykj.xzhk.controller.api.wechat;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.annotation.MemberLoginRequired;
import com.cdyykj.xzhk.controller.api.BaseController;
import com.cdyykj.xzhk.entity.AnnexManage;
import com.cdyykj.xzhk.entity.DepartmentMember;
import com.cdyykj.xzhk.entity.FlightReport;
import com.cdyykj.xzhk.response.FlightReportResponse;
import com.cdyykj.xzhk.service.AnnexManageService;
import com.cdyykj.xzhk.service.FlightReportService;
import com.cdyykj.xzhk.service.RAnnexApplyService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "飞行报告")
@RestController(value = "memberFlightReportController")
@RequestMapping(value = "wechat/member/flightReport")
public class FlightReportController  extends BaseController {
    @Autowired
    FlightReportService flightReportService;
    @Autowired
    AnnexManageService annexManageService;
    @Autowired
    RAnnexApplyService rAnnexApplyService;
    /**
     * 飞行报告列表
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "flightReportList")
    @ApiOperation(value = "飞行报告列表",response = FlightReport.class)
    @MemberLoginRequired
    public JsonResult flightReportList(HttpServletRequest request,@RequestParam(required =false, defaultValue = "1") int page
            , @RequestParam(required =false,defaultValue = "10") int limit){
        try {
            ObjectNode json= JsonUtils.getMapperInstance().createObjectNode();
            Integer memberId=getDepartmentMember(request).getId();
            PageHelper.startPage(page,limit);
            List<FlightReport> flightReports=flightReportService.selectPage(memberId);
            json.putPOJO("flightReports",new PageInfo<>(flightReports));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 提交飞行报告
     * @return
     */
    @MemberLoginRequired
    @PostMapping(value = "submitFlightReport")
    @ApiOperation(value = "提交飞行报告,多选项已，拼接")
    public JsonResult submitFlightReport(HttpServletRequest request,@RequestBody FlightReportResponse flightReportResponse){
            try {
                DepartmentMember department=getDepartmentMember(request);

                ObjectNode json  = flightReportService.save(department,flightReportResponse,rAnnexApplyService);
                if(json.get("result").textValue().equals("ok")){
                    return JsonResultUtils.buildJsonOK(json);
                }
                return JsonResultUtils.buildJsonFail(json);
            }catch (Exception e){
                e.printStackTrace();
                return JsonResultUtils.buildJsonFailMsg(e.getMessage());
            }
    }

    /**
     * 飞行报告详情
     * @return
     */
    @MemberLoginRequired
    @GetMapping(value = "flightReportDetail")
    @ApiOperation(value="飞行报告详情",response =FlightReport.class )
    @ApiImplicitParam(value = "飞行报告ID",name="flightReportId")
   public JsonResult flightReportDetail(@RequestParam Integer flightReportId){
        try {
            ObjectNode json =JsonUtils.getMapperInstance().createObjectNode();
            FlightReport flightReport=flightReportService.getById(flightReportId);
            List<AnnexManage> annexManages=annexManageService.selectAnnexManage(flightReportId,1);
            json.putPOJO("annexManages",annexManages);
            json.putPOJO("flightReport",flightReport);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
   }

}
