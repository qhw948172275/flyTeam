package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.AnnexManage;
import com.cdyykj.xzhk.entity.Article;
import io.swagger.annotations.ApiModelProperty;


import java.util.List;

public class ArticleMemberResponse extends Article {
    /**
     * 接收成员集合
     */
    @ApiModelProperty(value = "接收成员集合")
    private List<MemberResponse>  memberResponses;
    /**
     * 附件集合
     */
    @ApiModelProperty(value = "附件集合")
    private List<AnnexManage> annexManages;
    public List<MemberResponse> getMemberResponses() {
        return memberResponses;
    }

    public void setMemberResponses(List<MemberResponse> memberResponses) {
        this.memberResponses = memberResponses;
    }

    public List<AnnexManage> getAnnexManages() {
        return annexManages;
    }

    public void setAnnexManages(List<AnnexManage> annexManages) {
        this.annexManages = annexManages;
    }
}
