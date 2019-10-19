package com.cdyykj.xzhk.controller.api.platform;

import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.system.dto.ResourceDto;
import com.cdyykj.system.entity.SystemRole;
import com.cdyykj.system.entity.SystemRoleResource;
import com.cdyykj.system.response.RoleResourceResponse;
import com.cdyykj.system.service.ResourceDtoService;
import com.cdyykj.system.service.impl.SystemRoleServiceImpl;
import com.cdyykj.xzhk.controller.api.BaseController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "角色管理接口")
@RestController
@RequestMapping(value="api/system/role")
public class SystemRoleController extends BaseController {
    @Autowired
    private SystemRoleServiceImpl systemRoleService;
    @Autowired
    private ResourceDtoService resourceDtoService;
    /**
     * 获取本校所有角色
     */
    @ApiOperation(value="获取本校所有角色",response = SystemRole.class)
    @GetMapping(value="")
    public JsonResult getRoles(@RequestParam(value="page",defaultValue="1")Integer page,
                               @RequestParam(value="limit",defaultValue="20")Integer limit, String sreach) {
        try{
            PageHelper.startPage(page,limit);
            List<SystemRole> roleList=systemRoleService.getAllRole(null,sreach,null,null,null);
            return JsonResultUtils.buildJsonOK(new PageInfo<>(roleList));
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
    }

    /**
     * 添加角色
     * @return
     */
    @ApiOperation(value="添加角色")
    @GetMapping(value="add")
    public JsonResult add(){
        try{
            ObjectNode obj = JsonUtils.getMapperInstance().createObjectNode();
          //  List<ResourceDto> resourceDtos=resourceDtoService.selectByUserId(getSystemUser().getId());
            List<ResourceDto> resourceDtos=resourceDtoService.selectAll();
            obj.putPOJO("resourceDtos",resourceDtos);
            return JsonResultUtils.buildJsonOK(obj);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
    }

    /**
     * 编辑角色
     * @param roleId
     * @return
     */
    @ApiOperation(value="编辑角色",response =SystemRoleResource.class )
    @GetMapping(value="edit")
    public JsonResult edit(@RequestParam(value="roleId") Integer roleId){
        try{
            ObjectNode obj = JsonUtils.getMapperInstance().createObjectNode();
            SystemRole systemRole=systemRoleService.getById(roleId);
            obj.putPOJO("systemRole",systemRole);
            /**
             * ----查询该角色所拥有的资源
             */
            List<SystemRoleResource> roleResourceIds=systemRoleService.selectRoleResourceIds(roleId);
            obj.putPOJO("roleResourceIds",roleResourceIds);
            List<ResourceDto> resourceDtos=resourceDtoService.selectAll();
            obj.putPOJO("resourceDtos",resourceDtos);
            return JsonResultUtils.buildJsonOK(obj);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
    }
    /**
     * 保存角色
     * @param resourceResponse
     * @return
     */
    @ApiOperation(value="保存角色")
    @PostMapping(value="save")
    public JsonResult save(@RequestBody RoleResourceResponse resourceResponse){
       try{
           if(resourceResponse.getId()!=null){
               resourceResponse.setLastUpdateCreatetime(CalendarUtils.getTimeStamp());
               resourceResponse.setLastUpdateCreator(getSystemUser().getName());
               systemRoleService.updateOrInsertByRoleResourceResponse(resourceResponse,1);
           }else{
               resourceResponse.setCreatetime(CalendarUtils.getTimeStamp());
               resourceResponse.setCreator(getSystemUser().getName());
               systemRoleService.updateOrInsertByRoleResourceResponse(resourceResponse,0);
           }
           return JsonResultUtils.buildJsonOK();
       }catch(Exception e){
           log.error(e.getLocalizedMessage());
       }
       return JsonResultUtils.buildJsonFail();
    }

    /**
     * 更改角色状态
     * @param roleId
     * @param status
     * @return
     */
    @ApiOperation(value="更改角色状态")
    @GetMapping(value="changeStatus")
    public JsonResult changeStatus(@RequestParam(value="roleId")Integer roleId, @RequestParam(value="status")Integer status){
        try {
            SystemRole systemRole=new SystemRole();
            systemRole.setId(roleId);
            systemRole.setStatus(status);
            systemRoleService.updateById(systemRole);
            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
    }

    /**
     * 获取全部已启用的角色
     * @return
     */
    @GetMapping(value = "roleList")
    @ApiOperation(value = "获取全部已启用的角色")
    public JsonResult roleList(){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            List<SystemRole> systemRoles=systemRoleService.selectRoleListByStatus(0);
            json.putPOJO("systemRoles",systemRoles);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }
}
