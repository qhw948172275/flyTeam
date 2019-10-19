package com.cdyykj.xzhk.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "r_annex_apply")
public class RAnnexApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 附件ID
     */
    private Integer annexId;
    /**
     * 应用ID
     */
    private Integer applyId;
    /**
     * 应用类型0-文章，1-飞行报告
     */
    private Byte type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnnexId() {
        return annexId;
    }

    public void setAnnexId(Integer annexId) {
        this.annexId = annexId;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
