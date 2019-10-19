package com.cdyykj.xzhk.controller.api.platform;

import com.cdyykj.commons.OutPutFolderConfig;
import com.cdyykj.commons.excel.BaseUtils;
import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.controller.api.commons.PdfUtils;
import com.cdyykj.xzhk.entity.AnnexManage;
import com.cdyykj.xzhk.entity.FlightReport;
import com.cdyykj.xzhk.response.FlightReportResponse;
import com.cdyykj.xzhk.service.AnnexManageService;
import com.cdyykj.xzhk.service.FlightReportService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "飞行报告管理")
@RestController
@RequestMapping(value = "api/flightReport")
public class FlightReportController {
    @Autowired
    FlightReportService flightReportService;
    @Autowired
    OutPutFolderConfig outPutFolderConfig;
    @Autowired
    AnnexManageService annexManageService;
    /**
     * 飞行报告列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "index")
    @ApiOperation(value="飞行报告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码"),
            @ApiImplicitParam(name="limit",value = "每页数量"),
            @ApiImplicitParam(name="keyword",value = "关键字") ,
            @ApiImplicitParam(name="dateStr",value = "日期")
    })
    public JsonResult index(@RequestParam(required =false,defaultValue = "1") int page
            ,@RequestParam(required =false,defaultValue = "10") int limit,String keyword,String dateStr){
            try {
                ObjectNode json = JsonUtils.getMapperInstance().createObjectNode();
                PageHelper.startPage(page,limit);
                List<FlightReportResponse>  flightReports=flightReportService.selectList(keyword,dateStr);
                json.putPOJO("flightReports",new PageInfo<>(flightReports));
                return JsonResultUtils.buildJsonOK(json);
            }catch (Exception e){
                e.printStackTrace();
                return JsonResultUtils.buildJsonFailMsg(e.getMessage());
            }
    }

    /**
     * 查看飞行报告详情
     * @param flightReportId
     * @return
     */
    @ApiOperation(value = "查看飞行报告详情")
    @GetMapping(value = "getDetail")
    public JsonResult getDetail(@RequestParam Integer flightReportId){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
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

    /**
     * 飞行报告详情导出
     * @return
     */
    @ApiOperation(value = "飞行报告详情导出")
    @GetMapping(value = "output")
    public JsonResult output(@RequestParam Integer flightReportId){
        try {

            FlightReport flightReport=flightReportService.getById(flightReportId);
            Map<String,String> map=createFlightMap(flightReport);
            String newPath= PdfUtils.pdfout(map,outPutFolderConfig.getPrefix(),flightReport.getSubmitter(),CalendarUtils.dateToString(flightReport.getCreateTime(),CalendarUtils.yyyy_MM_dd__HH_mm_ss));
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            if(StringUtils.isNotEmpty(newPath)){
                json.put("fileName",newPath);
                return JsonResultUtils.buildJsonOK(json);
            }else{
                return JsonResultUtils.buildJsonFailMsg("文件创建失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 创建PDF模板需要的map数据
     * @return
     */
   private Map<String,String> createFlightMap(FlightReport flightReport){
       Map<String,String> map=new HashMap<>();
       String[] sDate=flightReport.getInputTime().split("-");
       map.put("e_year",sDate[0]);
       map.put("e_month",sDate[1]);
       map.put("e_day",sDate[2]);
       map.put("flightModel",flightReport.getFlightModel());
       map.put("flightNumber",flightReport.getFlightNumber());
       map.put("route",flightReport.getBasePlace()+"——"+flightReport.getDestination());
       map.put("submitter",flightReport.getSubmitter());
       map.put("leftSeatMemberName",flightReport.getLeftSeatMemberName());
       map.put("rightSeatMemberName",flightReport.getRightSeatMemberName());
       map.put("otherMemberNames",flightReport.getOtherMemberNames());
       map.put("title",flightReport.getTitle());
       //天气
       String[] weatherIds=flightReport.getWeatherCondition().split(",");
       for(int i=0;i<weatherIds.length;i++){
           String key="weather_"+(Integer.valueOf(weatherIds[i])+1);
           map.put(key,"On");
           if(weatherIds[i].equals("8")){
               map.put("w_remake",flightReport.getWeatherConditionRamark());
           }
       }
       //影响
        int impact=flightReport.getFlightImpact().intValue();
        map.put("fly_"+(impact+1),"On");
        //事件阶段
        int eventStage=flightReport.getEventStage().intValue();
        map.put("event_"+(eventStage+1),"On");
        if(eventStage==12){
            map.put("event_remake",flightReport.getEventStageRemarke());
        }
        //故障情况
       String[] faults=flightReport.getFlightFault().split(",");
        for(int i=0;i<faults.length;i++){
            map.put("fault_"+(Integer.valueOf(faults[i])+1),"On");
            if(faults[i].equals("6")){
                map.put("fault_remake",flightReport.getFlightFaultRemark());
            }
        }
        //给行记录填写情况
        int fiRe=flightReport.getIsFlightRecord().intValue();
        if(fiRe==0){
            map.put("input_2","On");
        }
        if(fiRe==1){
            map.put("input_1","On");
        }
        //事件内容
        map.put("content",flightReport.getEventContent());
        //提交时间
        String[] subDate= CalendarUtils.dateToString(flightReport.getCreateTime(),CalendarUtils.yyyy_MM_dd).split("-");
        map.put("s_year",subDate[0]);
        map.put("s_month",subDate[1]);
        map.put("s_day",subDate[2]);
        return map;
  }


    /**
     * 飞行报告列表导出
     * @return
     */
    @ApiOperation(value = "飞行报告列表导出")
    @GetMapping(value = "listOutput")
  public JsonResult listOutput(){
      ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
       try {
           List<FlightReportResponse>  flightReports=flightReportService.selectList(null,null);
           String[] titles={"提交日期","ID","姓名","职位","事件日期","标题","机型/机号","航班号","航线","左座","右座"
           ,"事件其他成员","标题","当时天气情况","对飞行的影响","事件发生阶段","飞机故障情况","《飞行记录本》填写情况"};
           String[] weather={"雨","雪","雷暴","冰雹","雾","结冰","乱流","风切变"};
           String[] flight={"轻微","轻度","中度","严重"};
           String[] event={"地面准备","起飞前滑行","起飞","爬升","巡航/作业","下降","接近","复飞","着陆","接地后滑跑","脱离跑到后滑行","关车"};
           String[] fault={"发动机故障","仪表设备故障","电气系统故障","操作系统故障","液压系统故障","电子系统故障"};
           List<List<String>> lists=new ArrayList<>(flightReports.size());
           for(FlightReportResponse flightReportResponse:flightReports){
               List<String> stringList=new ArrayList<>(titles.length);
               stringList.add(CalendarUtils.dateToString(flightReportResponse.getCreateTime(),CalendarUtils.yyyy_MM_dd__HH_mm_ss));
               stringList.add(flightReportResponse.getId().toString());
               stringList.add(flightReportResponse.getMemberName());
               stringList.add(flightReportResponse.getPosition());
               stringList.add(flightReportResponse.getInputTime());
               stringList.add(flightReportResponse.getTitle());
               stringList.add("TV "+flightReportResponse.getFlightModel());
               stringList.add(flightReportResponse.getFlightNumber());
               stringList.add(flightReportResponse.getBasePlace()+"--"+flightReportResponse.getDestination());
               stringList.add(flightReportResponse.getLeftSeatMemberName());
               stringList.add(flightReportResponse.getRightSeatMemberName());
               stringList.add(flightReportResponse.getOtherMemberNames());
               stringList.add(flightReportResponse.getTitle());
               if(StringUtils.isNotEmpty(flightReportResponse.getWeatherCondition())){
                   String[] weathers=flightReportResponse.getWeatherCondition().split(",");
                   StringBuffer wstr=new StringBuffer();
                   int weatherL=weathers.length;
                   for (int i=0;i<weatherL;i++){
                       int index=Integer.valueOf(weathers[i]);
                       if(index<weather.length){
                           wstr.append(weather[index]);
                           if(i+1<weatherL){
                               wstr.append(",");
                           }
                       }else{
                           weatherL=weatherL-1;
                       }
                   }
                   if(flightReportResponse.getWeatherConditionRamark()!=null){
                       if(weathers.length>1){
                           wstr.append(",");
                       }
                       wstr.append(flightReportResponse.getWeatherConditionRamark());
                   }
                   stringList.add(wstr.toString());
               }else{
                   stringList.add("");
               }
               stringList.add(flight[flightReportResponse.getFlightImpact().intValue()]);
               int fevent=flightReportResponse.getEventStage().intValue();
               if(fevent==12){
                   stringList.add(flightReportResponse.getEventStageRemarke());
               }else{
                   stringList.add(event[fevent]);
               }
               if(StringUtils.isNotEmpty(flightReportResponse.getFlightFault())){
                   String[] faultC=flightReportResponse.getFlightFault().split(",");
                   StringBuffer flightFault=new StringBuffer();
                   int faultL=faultC.length;
                   for(int i=0;i<faultL;i++){
                       int index=Integer.valueOf(faultC[i]);
                       if(index<fault.length){
                           flightFault.append(fault[index]);
                           if(i+1<faultL){
                               flightFault.append(",");
                           }
                       }else{
                           faultL=faultL-1;
                       }
                   }
                   if(flightReportResponse.getFlightFaultRemark()!=null){
                       if(faultL>1){
                           flightFault.append(",");
                       }
                       flightFault.append(flightReportResponse.getFlightFaultRemark());
                   }
                  stringList.add(flightFault.toString());
               }else{
                   stringList.add("");
               }

               stringList.add(flightReportResponse.getIsFlightRecord().intValue()==1?"已填":"未填");
               lists.add(stringList);
           }
           String[] regionTitle = new String[] {"飞行报告列表", CalendarUtils.dateToString(CalendarUtils.getDate(),CalendarUtils.yyyy_MM_dd__HH_mm_ss)};
           String fileName = BaseUtils.outputExcel("飞行报告列表",regionTitle,titles,lists,outPutFolderConfig.getPrefix(),false);
           json.put("fileName",fileName);
           return JsonResultUtils.buildJsonOK(json);
       }catch (Exception e){
           e.printStackTrace();
           return JsonResultUtils.buildJsonFailMsg(e.getMessage());
       }
  }
}
