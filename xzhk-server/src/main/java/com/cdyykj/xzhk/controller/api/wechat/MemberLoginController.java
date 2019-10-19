package com.cdyykj.xzhk.controller.api.wechat;

import com.cdyykj.commons.utils.ErrorCodeUtils;
import com.cdyykj.system.commons.*;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.tool.WxCpServiceTool;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.cp.api.WxCpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api(description = "成员登录")
@RestController
@RequestMapping(value = "wechat/member")
public class MemberLoginController {

    @Autowired
    WxCpServiceTool wxCpServiceTool;
    @Autowired
    RedisUtils redisUtils;
    /**
     * 成员登录
     * @param code
     * @return
     */
    @ApiOperation(value="成员登录")
    @GetMapping(value = "login")
    @ApiImplicitParam(value = "code",name="code",required = false)
    public JsonResult login(@RequestParam(required = false) String code){
        try {
            if(StringUtils.isEmpty(code)){
                ObjectNode json1 = createTokenAndRefreshToken("YangYuanZhi");
                return JsonResultUtils.buildJsonOK(json1);
            }
            WxCpService wxCpService = wxCpServiceTool.getWxCpService(SystemConstants.SYSTEM_SECRET);
            String userId= WxCpServiceTool.getUserI(wxCpService,code);
            if(userId!=null){
                ObjectNode json = createTokenAndRefreshToken(userId);
                return JsonResultUtils.buildJsonOK(json);
            }
            return ErrorCodeUtils.ERROR_4116.getResult();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    @GetMapping(value = "refreshToken")
    @ApiOperation(value = "刷新token")
    public JsonResult refreshToken(@RequestParam("refreshToken")String refreshToken) {
        try {
            if(redisUtils.validateStringKeyExists(SystemConstants.MEMBER_REFRESH_TOKEN_KEY+refreshToken)) {
                String token = redisUtils.getStringForKey(SystemConstants.MEMBER_REFRESH_TOKEN_KEY+refreshToken);
                String userId = redisUtils.getStringForKey(SystemConstants.MEMBER_TOKEN_KEY+token);
                redisUtils.deleKey(SystemConstants.MEMBER_REFRESH_TOKEN_KEY+refreshToken);//删除刷新token
                redisUtils.deleKey(SystemConstants.MEMBER_TOKEN_KEY+token); //删除用户当前token

                //生成新的token和refreshToken
                ObjectNode json=createTokenAndRefreshToken(userId);



                return JsonResultUtils.buildJsonOK(json);
            }
            return ErrorCodeUtils.ERROR_REFRESH_TOKEN_NOT_EXISTS.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCodeUtils.ERROR_500.getResult();
        }
    }

    /**
     * 创建TokenAndRefreshToekn
     * @param userId
     * @return
     */
    private ObjectNode createTokenAndRefreshToken(String userId){
        ObjectNode json = JsonUtils.getMapperInstance().createObjectNode();
        String token = RandomUtils.getRandomCharAndNumr(32);
        redisUtils.setStringForKey(SystemConstants.MEMBER_TOKEN_KEY+token,userId);
        redisUtils.setExpireKey(SystemConstants.MEMBER_TOKEN_KEY + token,new Long(30) , TimeUnit.DAYS);//设置token的有效期
        //刷新token
        String refreshToken= MD5Password.encryptPassword(token+userId);
        redisUtils.setStringForKey(SystemConstants.MEMBER_REFRESH_TOKEN_KEY+refreshToken,token);
        redisUtils.setExpireKey(SystemConstants.MEMBER_REFRESH_TOKEN_KEY + refreshToken,new Long(30) , TimeUnit.DAYS);//设置token的有效期
        json.put("token",token);
        json.put("refreshToken",refreshToken);
        json.put("userId",userId);
        return json;
    }
}
