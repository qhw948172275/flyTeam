package com.cdyykj.xzhk.controller.api;

import com.cdyykj.system.commons.RedisUtils;
import com.cdyykj.system.commons.SystemConstants;
import com.cdyykj.system.entity.SystemUser;
import com.cdyykj.system.service.SystemUserService;
import com.cdyykj.xzhk.entity.DepartmentMember;
import com.cdyykj.xzhk.service.DepartmentMemberService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
public class BaseController {
    public Logger log= LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    public RedisUtils redisUtils;
    @Autowired
    DepartmentMemberService departmentMemberService;

    /**
     * 获取token
     * @return
     */
    public String getToken(){
        SecurityContext securityContext= SecurityContextHolder.getContext();
        System.out.println(securityContext);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((OAuth2AuthenticationDetails)authentication.getDetails()).getTokenValue();
    }

    /**
     * 获取当前用户
     * @return
     */
    public SystemUser getSystemUser(){
        SystemUser systemUser;
        String stringSystemUser=redisUtils.getStringForKey(SystemConstants.SYS_USER_KEY+getToken());
        if(stringSystemUser == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            systemUser=systemUserService.getUserByName(authentication.getName());
            redisUtils.setStringForKey(SystemConstants.SYS_USER_KEY+getToken(),redisUtils.beanToString(systemUser));
            redisUtils.setExpireKey(SystemConstants.SYS_USER_KEY + getToken(),new Long(3000) , TimeUnit.DAYS);//设置token的有效期
        }else{
            systemUser=redisUtils.stringToBean(stringSystemUser,SystemUser.class);
        }
        return systemUser;
    }


    /**
     * 获取当前角色代码
     * @return
     */
   public Set<String> getRoleCode(){
       Set<String> roleCodes=new HashSet<>();
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
       for (GrantedAuthority grantedAuthority : authorities) {
           roleCodes.add(grantedAuthority.getAuthority());
       }
       return roleCodes;
   }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 获取成员信息
     * @param request
     * @return
     */
   public DepartmentMember getDepartmentMember(HttpServletRequest request){
       String token = request.getHeader("token");
       String userId=redisUtils.getStringForKey(SystemConstants.MEMBER_TOKEN_KEY+token);
       return  departmentMemberService.selectByUserId(userId);
   }
}
