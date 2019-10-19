package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.DoRecord;
import com.cdyykj.xzhk.entity.DoRecordDetail;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DoRecordResponse extends DoRecord {
    /**
     * 会员名称
     */
    @ApiModelProperty(value = "memberName")
    private String memberName;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "dptName")
    private String dptName;
    /**
     * 做题详情
     */
    @ApiModelProperty(value="做题详情")
    private List<DoRecordDetail> doRecordDetails;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public List<DoRecordDetail> getDoRecordDetails() {
        return doRecordDetails;
    }

    public void setDoRecordDetails(List<DoRecordDetail> doRecordDetails) {
        this.doRecordDetails = doRecordDetails;
    }
}
