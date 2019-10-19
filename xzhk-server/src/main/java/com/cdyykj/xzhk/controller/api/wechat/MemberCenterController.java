package com.cdyykj.xzhk.controller.api.wechat;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.annotation.MemberLoginRequired;
import com.cdyykj.xzhk.controller.api.BaseController;
import com.cdyykj.xzhk.response.MemberInfoResponse;
import com.cdyykj.xzhk.service.DepartmentMemberService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(description = "个人中心")
@RestController
@RequestMapping(value = "wechat/member/center")
public class MemberCenterController extends BaseController {
    @Autowired
    DepartmentMemberService departmentMemberService;
    /**
     * 获取成员信息
     * @param request
     * @return
     */
    @GetMapping(value = "memberInfo")
    @ApiOperation(value = "获取成员信息")
    @MemberLoginRequired
    public JsonResult memberInfo(HttpServletRequest request){
        try {
            ObjectNode json = JsonUtils.getMapperInstance().createObjectNode();
            Integer memberId=getDepartmentMember(request).getId();
            MemberInfoResponse memberInfoResponse=departmentMemberService.selectMemberInfoResponse(memberId);
            json.putPOJO("memberInfoResponse",memberInfoResponse);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }
}
