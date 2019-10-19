package com.cdyykj.xzhk.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Table(name = "t_annex_manage")
public class AnnexManage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 状态：0-启用，1-禁用
     */
    @ApiModelProperty(value = "状态：0-启用，1-禁用")
    private Byte status;

    /**
     * 附件地址
     */
    @ApiModelProperty(value = "附件地址")
    private String url;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String name;
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private String size;




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
     * 获取状态：0-启用，1-禁用
     *
     * @return status - 状态：0-启用，1-禁用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0-启用，1-禁用
     *
     * @param status 状态：0-启用，1-禁用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取附件地址
     *
     * @return url - 附件地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置附件地址
     *
     * @param url 附件地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取附件名称
     *
     * @return name - 附件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置附件名称
     *
     * @param name 附件名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}