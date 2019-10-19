package com.cdyykj.system.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "r_sys_role_user")
public class SystemRoleUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer uid;

    private Integer roleId;
    
    private Integer schoolId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public SystemRoleUser(){

    }
    public SystemRoleUser(Integer uid,Integer roleId){
            this.uid=uid;
            this.roleId=roleId;
    }
}