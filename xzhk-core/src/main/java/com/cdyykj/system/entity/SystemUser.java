package com.cdyykj.system.entity;

import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.CommonUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "t_sys_user")
public class SystemUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty(value = "账号")
    private String name;

    private String email;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "0-禁用，1-启用")
    private Integer status;

    private String creator;
    @JsonFormat(pattern = CalendarUtils.yyyy_MM_dd__HH_mm_ss)
    private Long createtime;
    /**
     * 课时
     */
    @ApiModelProperty(value = "课时")
    private Integer period;

    private Long lastLoginTime;

    private String lastLoginIp;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @Column(name="school_id")
    private Integer schoolId;
    @ApiModelProperty(value = "电话")
    private String phone;

    @Column(name="teacher_id")
    private Integer teacherId;
    /**
     * 头像地址
     */
    @Column(name="avatar_url")
    private String avatarUrl;
    /**
     * 是否是老师0：不是，1：是
     */
    @ApiModelProperty(value = "是否是老师0：不是，1：是")
    @Column(name="is_teacher")
    private Integer isTeacher;
    /**
     * 推送Id
     */
    @Column(name="client_id")
    private String clientId;

    /**
     * 性别 男/女
     */
    @ApiModelProperty(value = "性别 男/女")
    private String sex;
    /**
     * 职务ID
     */
    @Column(name="school_job_id")
    private Integer schoolJobId;
    @Transient
    private Integer userType=0;
    @Transient
    private Date creatorTime;
    @Transient
    @ApiModelProperty(value = "角色IDs")
    private List<Integer> roleIds;

    private static final long serialVersionUID = 1L;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    public Integer getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Integer isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSchoolJobId() {
        return schoolJobId;
    }

    public void setSchoolJobId(Integer schoolJobId) {
        this.schoolJobId = schoolJobId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public SystemUser(){

    }
    public SystemUser(String name,String password,Integer schoolId){
        this.name=name;
        this.password= CommonUtils.encode(password);
        this.schoolId=schoolId;
    }

    public Date getCreatorTime() {
        if(this.createtime!=null){
            return CalendarUtils.timeStampToDate(this.createtime);
        }
        return creatorTime;
    }

    public void setCreatorTime(Date creatorTime) {
        this.creatorTime = creatorTime;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}