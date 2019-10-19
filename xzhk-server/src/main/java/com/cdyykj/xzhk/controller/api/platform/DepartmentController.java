package com.cdyykj.xzhk.controller.api.platform;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.system.dto.DepartmentDto;
import com.cdyykj.xzhk.entity.Department;
import com.cdyykj.xzhk.entity.DepartmentMember;
import com.cdyykj.xzhk.response.DepartmentMemberResponse;
import com.cdyykj.xzhk.response.RDepartmentMemberResponse;
import com.cdyykj.xzhk.response.RDepartmentMemberResponseDto;
import com.cdyykj.xzhk.service.DepartmentMemberService;
import com.cdyykj.xzhk.service.DepartmentService;
import com.cdyykj.xzhk.tool.DepartmentTool;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "部门管理接口")
@RestController
@RequestMapping(value = "api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentMemberService departmentMemberService;
    @Autowired
    DepartmentTool departmentTool;
    /**
     * 获取部门列表
     * @return
     */
    @ApiOperation(value = "获取部门列表")
    @GetMapping(value = "getDepartmentList")
    public JsonResult getDepartmentList(){
        try {
            ObjectNode json= JsonUtils.getMapperInstance().createObjectNode();
            List<DepartmentDto> departments=departmentService.selectAll();
            json.putPOJO("departments",departments);
           return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 关键字搜索
     * @param keyword
     * @return
     */
    @ApiOperation(value = "关键字搜索")
    @GetMapping(value = "searchKeyword")
    public JsonResult searchKeyword(String keyword){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            if(StringUtils.isNotEmpty(keyword)){
                keyword="%"+keyword+"%";
            }
            List<Department> departments=departmentService.selectByKeyword(keyword);
            json.putPOJO("departments",departments);
            List<DepartmentMemberResponse> departmentMembers=departmentMemberService.searchKeyword(keyword);
            json.putPOJO("departmentMembers",departmentMembers);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 获取全部部门和成员列表
     * @return
     */
    @ApiOperation(value = "获取全部部门和成员列表",response =RDepartmentMemberResponse.class )
    @GetMapping(value = "getAll")
    public JsonResult getAll(){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            List<RDepartmentMemberResponseDto> responseDtos=departmentService.selectRDepartmentMemberResponseParent();
            List<RDepartmentMemberResponseDto>  rDepartmentMemberResponses=departmentService.selectRDepartmentMemberResponse();
            List<RDepartmentMemberResponseDto> responseDtoList=RDepartmentMemberResponseDto.getRecursiver(responseDtos,rDepartmentMemberResponses);
            json.putPOJO("rDepartmentMemberResponses",responseDtoList);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }


    /**
     * 同步通讯录
     * @return
     */
    @ApiOperation(value = "同步通讯录")
    @GetMapping(value = "synchroDepartMember")
    public JsonResult synchroDepartMember(){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            if(departmentTool.startSynchro()){
                json.put("result",true);
            }else{
                json.put("result",false);
            }

            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 增量同步通讯录
     * @return
     */
    @ApiOperation(value = "增量同步通讯录")
    @GetMapping(value = "synchroDepartMemberAdd")
    public JsonResult synchroDepartMemberAdd(){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            if(departmentTool.spickSynchroAdd()){
                json.put("result",true);
            }else{
                json.put("result",false);
            }

            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }
}
