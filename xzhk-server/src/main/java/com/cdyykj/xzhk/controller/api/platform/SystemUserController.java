package com.cdyykj.xzhk.controller.api.platform;

import com.cdyykj.system.commons.*;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.system.entity.SystemRoleUser;
import com.cdyykj.system.entity.SystemUser;
import com.cdyykj.system.response.SystemUserRoleResponse;
import com.cdyykj.system.service.SystemUserService;
import com.cdyykj.system.service.impl.SystemRoleUserService;
import com.cdyykj.xzhk.config.MyRedisTokenStore;
import com.cdyykj.xzhk.controller.api.BaseController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(description = "用户管理接口")
@RestController
@RequestMapping("api/systemUser")
public class SystemUserController extends BaseController {
    public Logger log= LoggerFactory.getLogger(SystemUserController.class);
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private MyRedisTokenStore myRedisTokenStore;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SystemRoleUserService systemRoleUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    /**
     * 用户管理主页面
     * @param page
     * @param limit
     * @param search
     * @return
     */
    @ApiOperation(value="用户管理主页面",response =SystemUserRoleResponse.class )
    @GetMapping(value="")
    public JsonResult getSystemUserList(@RequestParam(value="page",defaultValue="1")Integer page,
                                        @RequestParam(value="limit",defaultValue="20")Integer limit, String search){
        try {
            PageHelper.startPage(page,limit);
            List<SystemUserRoleResponse> userList=systemUserService.getAll(search,null,0);
            PageInfo<SystemUserRoleResponse> pageInfo=new PageInfo<>(userList);
            return JsonResultUtils.buildJson(JsonResultUtils.OK,pageInfo);
        }catch (Exception e){
                log.error(e.getLocalizedMessage());
        }
        return JsonResultUtils.buildJson(JsonResultUtils.FAIL);


    }

    /**
     * 账户退出
     * @return
     */
    @ApiOperation(value="用户退出接口")
    @GetMapping(value="loginOut")
    public JsonResult loginOut(){
        redisUtils.deleKey(SystemConstants.SYS_USER_KEY+getToken());
        myRedisTokenStore.removeAccessToken(getToken());
        return JsonResultUtils.buildJson("退出成功！");
    }

    /**
     *校验密码
     * @param oldPassword
     * @return
     */
    @ApiOperation(value="校验密码")
    @GetMapping(value="chackPassword")
    public JsonResult chackPassword(@RequestParam(value="oldPassword")String oldPassword){
       try{
           String passowrd=getSystemUser().getPassword();
           if(passwordEncoder.matches(oldPassword,passowrd)){
               return JsonResultUtils.buildJsonOK();
           }
           return JsonResultUtils.buildJsonFail("密码错误");
       }catch (Exception e){
            log.info(e.getLocalizedMessage());
       }
       return JsonResultUtils.buildJsonFail();
    }

    /**
     * 保存用户信息
     * @param systemUser
     * @return
     */
    @ApiOperation(value = "保存用户信息")
    @PostMapping(value = "save")
    public JsonResult save(@RequestBody SystemUser systemUser){
        try {
            if(systemUser.getId()==null||systemUser.getId()<=0) {
                if(systemUserService.getUserByName(systemUser.getName(),null).size()>0){
                    return JsonResultUtils.buildJsonFail("该用户名已存在！");
                }
                if (StringUtils.isEmpty(systemUser.getPassword())) {
                    systemUser.setPassword(SystemConstants.defaultPassword);
                }
                systemUser.setPassword(CommonUtils.encode(systemUser.getPassword()));
                systemUser.setCreatetime(CalendarUtils.getTimeStamp());
                systemUser.setCreator(getSystemUser().getRealName());
                //systemUser.setSchoolId(getSchoolId());
                systemUserService.insert(systemUser);
            }else{
                if(systemUserService.validateUserNameExists(systemUser.getName(),systemUser.getId())){
                    return JsonResultUtils.buildJsonFail("该账号已存在！");
                }
                if(StringUtils.isNotEmpty(systemUser.getPassword())){
                    systemUser.setPassword(CommonUtils.encode(systemUser.getPassword()));
                }else{
                    systemUser.setPassword(null);
                }
                systemRoleUserService.deleteByUserId(systemUser.getId());//删除该用户的旧角色
                systemUserService.updateById(systemUser) ;
            }
            if(systemUser.getRoleIds()!=null&&!systemUser.getRoleIds().isEmpty()){
                SystemRoleUser systemRoleUser;
                List<SystemRoleUser>  systemRoleUsers=new ArrayList<>(systemUser.getRoleIds().size());
                for(Integer integer:systemUser.getRoleIds()){
                    systemRoleUser  = new SystemRoleUser(systemUser.getId(),integer);
                    systemRoleUsers.add(systemRoleUser);
                }
                systemRoleUserService.insertList(systemRoleUsers);
            }
            redisUtils.deleKey(SystemConstants.SYS_USER_KEY+getToken());
            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResultUtils.buildJsonFail();
    }

    /**
     * 更改用户状态
     * @param status
     * @param userId
     * @return
     */
    @ApiOperation(value = "更改用户状态")
    @GetMapping(value = "changStatus")
    public JsonResult changStatus(@RequestParam Integer status, @RequestParam Integer userId){
        try {
            SystemUser systemUser=new SystemUser();
            systemUser.setId(userId);
            systemUser.setStatus(status);
            systemUserService.updateById(systemUser);
            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResultUtils.buildJsonFail();
    }

    /**
     * 用户详情
     * @return
     */
    @ApiOperation(value = "用户详情")
    @GetMapping(value = "userDetail")
   public JsonResult userDetail(@RequestParam Integer userId){
        try {
            SystemUserRoleResponse systemUserRoleResponse=systemUserService.getSystemUserRoleResponseById(userId);
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            json.putPOJO("systemUserRoleResponse",systemUserRoleResponse);
            return JsonResultUtils.buildJsonOK(systemUserRoleResponse);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
   }
}
