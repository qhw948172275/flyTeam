package com.cdyykj.xzhk.config;

import com.cdyykj.system.entity.SystemRole;
import com.cdyykj.system.entity.SystemUser;

import com.cdyykj.system.commons.SystemConstants;
import com.cdyykj.system.service.SystemRoleService;
import com.cdyykj.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;

import java.util.*;

/**
 * Created by chenshiliu
 * on 2018/12/15.
 */
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private SystemUserService userService;
    @Autowired
    private SystemRoleService roleService;
    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SystemUser userInfo=null;
        Set<SystemRole> sysRole=null;
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        /**
         * 平台用户登录
         */
        if(authentication.getName().equals(SystemConstants.PLATFORM)){
            List<SystemUser> systemUserList= userService.getUserByName(username,null);
            if(systemUserList == null||systemUserList.size()<=0){
                throw new InvalidGrantException("用户名："+ username + "不存在！");
            }
            userInfo=systemUserList.get(0);
            sysRole=new HashSet<>(roleService.selectByUserId(userInfo.getId(),0));
            Iterator<SystemRole> iterator =  sysRole.iterator();

            while (iterator.hasNext()){
                      collection.add(new SimpleGrantedAuthority(iterator.next().getRoleCode()));
            }
        }
        return new User(username,userInfo.getPassword(),collection);
    }
}
