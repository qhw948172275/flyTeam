package com.cdyykj.xzhk.controller.api.platform;


import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.system.dto.ResourceDto;
import com.cdyykj.system.entity.SystemResource;
import com.cdyykj.system.service.ResourceDtoService;
import com.cdyykj.system.service.impl.SystemResourceServiceImpl;
import com.cdyykj.xzhk.controller.api.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 资源访问控制类
 *
 * @author kimi
 * @dateTime 2012-3-20 下午8:16:45
 */
@Api(description = "资源管理接口")
@Controller("systemResourceController")
@RequestMapping("api/system/resource")
public class ResourceController extends BaseController {
    public Logger log= LoggerFactory.getLogger(ResourceController.class);
   @Autowired
    private ResourceDtoService resourceDtoService;
   @Autowired
   private SystemResourceServiceImpl systemResourceService;
    /**
     * 获取所有资源
     * @return
     */
    @ApiOperation(value="获取所有资源")
    @GetMapping("")
    public JsonResult getTree(){
        try{
            List<ResourceDto> resourceDtos=resourceDtoService.selectAll();
            return JsonResultUtils.buildJsonOK(resourceDtos);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
    }

    /**
     * 编辑资源
     * @param resourceId
     * @return
     */
    @ApiOperation(value="编辑资源")
    @GetMapping(value="edit")
   public JsonResult edit(Integer resourceId){
        try{
            SystemResource systemResource=systemResourceService.getById(resourceId);
            return JsonResultUtils.buildJsonOK(systemResource);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
   }
    /**
     * 保存资源
     * @param systemResource
     * @return
     */
    @ApiOperation(value="保存资源")
    @PostMapping(value="save")
    public JsonResult save(@RequestBody SystemResource systemResource){
        try{
            if(systemResource.getParentId()==0||systemResource.getParentId()==null){
                systemResource.setParentId(-1);
            }
            if(systemResource.getId()==null){
                systemResourceService.insert(systemResource);
            }else{
                systemResourceService.updateById(systemResource);
            }
            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
    }

    /**
     * 删除资源
     * @param resourceId
     * @return
     */
    @ApiOperation(value="删除资源")
    @GetMapping(value="delete")
    public JsonResult delete(@RequestParam(value="resourceId")Integer  resourceId){
        try{

            systemResourceService.delete(resourceId);

            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJsonFail();
    }
}
