package com.cdyykj.system.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_sys_role")
public class SystemRole implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "角色ID")
    private Integer id;
	@ApiModelProperty(value = "角色名称")
    private String roleName;
	@ApiModelProperty(value = "描述")
    private String description;
	@ApiModelProperty(value = "0-启用，1-禁用")
    private Integer status;

    private String creator;

    private Long createtime;

    private String lastUpdateCreator;

    private Long lastUpdateCreatetime;

    /**
     * 学校ID
     */
    @Column(name="school_id")
    private Integer schoolId;
	@ApiModelProperty(value = "角色代码")
    private String roleCode;
	/**
	 * 角色类型：0 普通 角色；1 系统角色
	 */
	private Integer roleType=0;

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdateCreatetime == null) ? 0 : lastUpdateCreatetime.hashCode());
		result = prime * result + ((lastUpdateCreator == null) ? 0 : lastUpdateCreator.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((schoolId == null) ? 0 : schoolId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemRole other = (SystemRole) obj;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdateCreatetime == null) {
			if (other.lastUpdateCreatetime != null)
				return false;
		} else if (!lastUpdateCreatetime.equals(other.lastUpdateCreatetime))
			return false;
		if (lastUpdateCreator == null) {
			if (other.lastUpdateCreator != null)
				return false;
		} else if (!lastUpdateCreator.equals(other.lastUpdateCreator))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (schoolId == null) {
			if (other.schoolId != null)
				return false;
		} else if (!schoolId.equals(other.schoolId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}