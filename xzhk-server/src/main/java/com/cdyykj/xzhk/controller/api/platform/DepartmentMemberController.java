package com.cdyykj.xzhk.controller.api.platform;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.response.DepartmentMemberResponse;
import com.cdyykj.xzhk.service.DepartmentMemberService;
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

import java.util.List;

@Api(description = "部门成员管理")
@RestController
@RequestMapping(value = "api/departmentMember")
public class DepartmentMemberController {
    @Autowired
    DepartmentMemberService departmentMemberService;

    /**
     * 根据部门ID获取成员列表
     * @return
     */
    @ApiOperation(value = "根据部门层级获取成员列表",response =DepartmentMemberResponse.class )
    @GetMapping(value = "getMemberList")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",dataType = "int"),
            @ApiImplicitParam(name="limit",value = "每页数量",dataType = "int")
    })
    public JsonResult getMemberList(@RequestParam String  levelPath,@RequestParam(required =false,defaultValue = "1") int page
                                             ,@RequestParam(required =false,defaultValue = "20") int limit){
        try {
            ObjectNode json= JsonUtils.getMapperInstance().createObjectNode();
            PageHelper.startPage(page,limit);
            if(StringUtils.isNotEmpty(levelPath)){
                levelPath=levelPath+"%";
            }
            List<DepartmentMemberResponse> departmentMembers=departmentMemberService.selectListByLevelPath(levelPath);
            json.putPOJO("departmentMembers",new PageInfo<>(departmentMembers));
            return JsonResultUtils.buildJsonOK(json);
            }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }
}
