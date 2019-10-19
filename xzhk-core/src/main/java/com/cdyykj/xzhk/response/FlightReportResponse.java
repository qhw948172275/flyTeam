package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.AnnexManage;
import com.cdyykj.xzhk.entity.FlightReport;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class FlightReportResponse extends FlightReport {

    /**
     * 成员姓名
     */
    @ApiModelProperty(value = "成员姓名")
    private String memberName;
    /**
     * 职务
     */
    @ApiModelProperty(value = "职务")
    private String position;
    /**
     * 附件集合
     */
    @ApiModelProperty(value = "附件集合")
    private List<AnnexManage> annexManageList;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<AnnexManage> getAnnexManageList() {
        return annexManageList;
    }

    public void setAnnexManageList(List<AnnexManage> annexManageList) {
        this.annexManageList = annexManageList;
    }
}
