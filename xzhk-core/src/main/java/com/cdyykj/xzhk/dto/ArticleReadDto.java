package com.cdyykj.xzhk.dto;

import io.swagger.annotations.ApiModelProperty;

public class ArticleReadDto {

    @ApiModelProperty(value = "开始阅读时间")
    private String startReadTime;
    @ApiModelProperty(value = "结束阅读时间")
    private String endReadTime;
    @ApiModelProperty(value = "文章ID")
    private Integer articleId;

    public String getStartReadTime() {
        return startReadTime;
    }

    public void setStartReadTime(String startReadTime) {
        this.startReadTime = startReadTime;
    }

    public String getEndReadTime() {
        return endReadTime;
    }

    public void setEndReadTime(String endReadTime) {
        this.endReadTime = endReadTime;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
