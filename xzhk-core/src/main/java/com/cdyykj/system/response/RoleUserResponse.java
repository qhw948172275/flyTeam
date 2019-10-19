package com.cdyykj.system.response;


import com.cdyykj.system.entity.SystemUser;

import java.util.List;

public class RoleUserResponse {
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色对应的用户信息
     */
    private List<SystemUser> userList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SystemUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SystemUser> userList) {
        this.userList = userList;
    }
}
