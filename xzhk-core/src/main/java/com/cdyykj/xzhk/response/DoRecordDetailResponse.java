package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.DoRecordDetail;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DoRecordDetailResponse extends DoRecordDetail {
    /**
     * 成员答案
     */
    @ApiModelProperty(value = "成员答案")
    List<String>  results;

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
