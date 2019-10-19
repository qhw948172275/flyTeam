package com.cdyykj.system.commons.enums;

/**
 * 通用状态枚举
 */
public enum StateEnum {

    DELETE(0,"删除"),NORMAL(1,"正常"),LOCK(2,"锁定");

    private Integer state;

    private String remark;

    StateEnum(int state,String remark) {
        this.state = state;
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public String getRemark() {
        return remark;
    }
}
