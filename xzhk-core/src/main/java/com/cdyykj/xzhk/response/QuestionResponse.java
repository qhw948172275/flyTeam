package com.cdyykj.xzhk.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
@Api(description = "选项")
public class QuestionResponse {
    @ApiModelProperty(value = "选项")
    private String option;
    @ApiModelProperty(value="选择内容")
    private String content;
    @ApiModelProperty(value = "图面地址")
    private String pictureUrl;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
