package com.cdyykj.system.response;

import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.entity.SystemRole;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 用户角色关联对象
 */
public class SystemUserRoleResponse {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;
    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private String userStatus;
    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String phone;
    /**
     * 角色名字
     */
    @ApiModelProperty(value = "角色名字")
    private String roleName;
    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色代码")
    private String roleCode;
    /**
     * 学校ID
     */
    @ApiModelProperty(value = "学校ID")
    private Integer schoolId;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    private Long createtime;
    private Date createTime;
    @ApiModelProperty(value = "角色集合")
    private List<SystemRole> systemRoles;
    /**
     * 职务ID
     */
    private Integer schoolJobId;
    private String avatarUrl;
    private String sex;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<SystemRole> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(List<SystemRole> systemRoles) {
        this.systemRoles = systemRoles;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Date getCreateTime() {
        if(this.createtime!=null){
            return CalendarUtils.timeStampToDate(this.createtime);
        }
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSchoolJobId() {
        return schoolJobId;
    }

    public void setSchoolJobId(Integer schoolJobId) {
        this.schoolJobId = schoolJobId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
