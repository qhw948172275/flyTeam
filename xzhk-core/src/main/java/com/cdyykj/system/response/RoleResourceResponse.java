package com.cdyykj.system.response;




import com.cdyykj.system.entity.SystemRole;

import java.util.List;

public class RoleResourceResponse {
    private Integer id;

    private String roleName;

    private String description;

    private Integer status;

    private String creator;

    private Long createtime;

    private String lastUpdateCreator;

    private Long lastUpdateCreatetime;

    /**
     * 学校ID
     */
    private Integer schoolId;

    private String roleCode;
    private List<Integer> resourceIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLastUpdateCreator() {
        return lastUpdateCreator;
    }

    public void setLastUpdateCreator(String lastUpdateCreator) {
        this.lastUpdateCreator = lastUpdateCreator;
    }

    public Long getLastUpdateCreatetime() {
        return lastUpdateCreatetime;
    }

    public void setLastUpdateCreatetime(Long lastUpdateCreatetime) {
        this.lastUpdateCreatetime = lastUpdateCreatetime;
    }


    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * 获取角色对象
     * @return
     */
    public SystemRole getSystemRole(){
        SystemRole systemRole=new SystemRole();
        systemRole.setSchoolId(this.schoolId);
        systemRole.setLastUpdateCreator(lastUpdateCreator);
        systemRole.setLastUpdateCreatetime(lastUpdateCreatetime);
        systemRole.setCreator(this.creator);
        systemRole.setCreatetime(this.createtime);
        systemRole.setDescription(this.description);
        systemRole.setRoleName(this.roleName);
        systemRole.setRoleCode(this.roleCode);
        systemRole.setId(this.id);
        systemRole.setStatus(this.status);
        return systemRole;
    }
}
