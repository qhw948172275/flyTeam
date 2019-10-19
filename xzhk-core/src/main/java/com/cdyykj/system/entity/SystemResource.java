package com.cdyykj.system.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="t_sys_resource")
public class SystemResource implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String resourceName;

    private String resourceUrl;

    private Integer isBasic;

    private Integer parentId;

    private String parentPath;

    private Integer level;

    private String remark;

    private Integer status;

    private Integer resourceKind; // 1商城 2平台 3直播 5erp 6系统 

    private Integer resourceType;

    private Integer seq;

    private Integer openMode;

    private Integer opened;
    
    private String icon;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Integer getIsBasic() {
        return isBasic;
    }

    public void setIsBasic(Integer isBasic) {
        this.isBasic = isBasic;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getResourceKind() {
		return resourceKind;
	}

	public void setResourceKind(Integer resourceKind) {
		this.resourceKind = resourceKind;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getOpenMode() {
		return openMode;
	}

	public void setOpenMode(Integer openMode) {
		this.openMode = openMode;
	}

	public Integer getOpened() {
		return opened;
	}

	public void setOpened(Integer opened) {
		this.opened = opened;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}