package com.cdyykj.xzhk.response;

import com.cdyykj.xzhk.entity.Article;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.util.Date;

public class ArticleResponse extends Article {
    /**
     * 开始阅读时间
     */
    @Column(name = "start_read_time")
    @ApiModelProperty(value = "开始阅读时间")
    private Date startReadTime;

    /**
     * 结束阅读时间
     */
    @Column(name = "end_read_time")
    @ApiModelProperty(value = "结束阅读时间")
    private Date endReadTime;

    /**
     * 是否阅读成功，0-否，1-是
     */
    @Column(name = "is_read_success")
    @ApiModelProperty(value = "是否阅读成功，0-否，1-是")
    private Byte isReadSuccess;


    public Date getStartReadTime() {
        return startReadTime;
    }

    public void setStartReadTime(Date startReadTime) {
        this.startReadTime = startReadTime;
    }

    public Date getEndReadTime() {
        return endReadTime;
    }

    public void setEndReadTime(Date endReadTime) {
        this.endReadTime = endReadTime;
    }

    public Byte getIsReadSuccess() {
        if(this.isReadSuccess==null){
            return (byte)0;
        }
        return isReadSuccess;
    }

    public void setIsReadSuccess(Byte isReadSuccess) {
        this.isReadSuccess = isReadSuccess;
    }
}
