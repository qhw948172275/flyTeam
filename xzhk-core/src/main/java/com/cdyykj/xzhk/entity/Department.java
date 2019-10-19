package com.cdyykj.xzhk.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Table(name = "t_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String name;

    /**
     * 父类ID
     */
    @ApiModelProperty(value = "父类ID")
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @Column(name = "dp_order")
    private Byte DpOrder;

    /**
     * 层级路径
     */
    @ApiModelProperty(value = "层级路径")
    private String levelPath;



    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门名称
     *
     * @return name - 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     *
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父类ID
     *
     * @return parent_id - 父类ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父类ID
     *
     * @param parentId 父类ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public Byte getDpOrder() {
        return DpOrder;
    }

    public void setDpOrder(Byte dpOrder) {
        DpOrder = dpOrder;
    }

    public String getLevelPath() {
        return levelPath;
    }

    public void setLevelPath(String levelPath) {
        this.levelPath = levelPath;
    }
}