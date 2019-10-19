package com.cdyykj.xzhk.controller.api.platform;


import com.cdyykj.commons.OutPutFolderConfig;
import com.cdyykj.system.commons.RedisUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.system.dto.ResourceDto;
import com.cdyykj.system.entity.SystemUser;
import com.cdyykj.system.service.ResourceDtoService;
import com.cdyykj.xzhk.controller.api.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@Api(description = "主页面接口")
@RestController
@RequestMapping(value="api/system/index")
public class IndexController extends BaseController {
    @Autowired
    private ResourceDtoService resourceDtoService;

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    OutPutFolderConfig outPutFolderConfig;

    /**
     * 获取本用户所有用的菜单
     * @return
     */
    @ApiOperation(value ="获取本用户所有用的菜单")
    @GetMapping(value="")
    public JsonResult index(){
        try {
           List<ResourceDto>resourceDtos= resourceDtoService.selectByUserId(getSystemUser().getId());
            if(resourceDtos.size()==0){
                return JsonResultUtils.buildJsonOK("该用户没有权限");
            }
            return JsonResultUtils.buildJsonOK(resourceDtos);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping(value = "getUserInfo")
    @ApiOperation(value = "获取用户信息",response = SystemUser.class )
    public JsonResult getUserInfo(){
        try {
            return JsonResultUtils.buildJsonOK(getSystemUser());
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResultUtils.buildJsonFail();
    }
}
