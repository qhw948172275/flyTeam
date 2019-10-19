package com.cdyykj.xzhk.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(description = "正确答案")
public class ResultResponse {
    @ApiModelProperty(value="正确答案")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
