package com.cdyykj.xzhk.entity;

import com.cdyykj.system.commons.NumberUtils;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章类型
     */
    @ApiModelProperty(value = "文章类型,0-安全质量周报，1-安全提示，2-风险分析，3-学习空间")
    private Byte type;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String title;

    /**
     * 发布状态：0-草稿，1-发布，2-未发布
     */
    @ApiModelProperty(value = "发布状态：0-草稿，1-发布，2-未发布")
    @Column(name = "send_status")
    private Byte sendStatus;

    /**
     * 封面
     */
    @ApiModelProperty(value = "封面")
    private String cover;

    /**
     * 点击量
     */
    @ApiModelProperty(value = "点击量")
    @Column(name = "click_count")
    private Long clickCount;

    /**
     * 阅读成功率
     */
    @ApiModelProperty(value = "阅读成功率")
    @Column(name = "read_success_rate")
    private Double readSuccessRate;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 状态0-启用，1-禁用
     */
    @ApiModelProperty(value = "状态0-启用，1-禁用")
    private Byte status;

    /**
     * 0-仅在企业内分享，1-可对外分享，2-不能分享
     */
    @ApiModelProperty(value = "0-仅在企业内分享，1-可对外分享，2-不能分享")
    @Column(name = "shzre_set")
    private Byte shzreSet;

    /**
     * 摘要
     */
    @ApiModelProperty(value = "摘要")
    private String summary;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String author;

    /**
     * 应接收人数
     */
    @ApiModelProperty(value = "应接收人数")
    @Column(name = "receive_number")
    private Integer receiveNumber;

    /**
     * 成功阅读人数
     */
    @ApiModelProperty(value = "成功阅读人数")
    @Column(name="success_read_number")
    private Integer successReadNumber;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @Column(name = "create_id")
    private Integer createId;

    /**
     * 发布类型，0-直接发布，1-定时发布
     */
    @ApiModelProperty(value = "发布类型，0-直接发布，1-定时发布")
    @Column(name = "send_type")
    private Byte sendType;

    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    private String content;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private Date sendTime;
    /**
     * 阅读时长
     */
    @ApiModelProperty(value="阅读时长")
    private Integer readTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文章类型
     *
     * @return type - 文章类型
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置文章类型
     *
     * @param type 文章类型
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取发布状态：0-草稿，1-发布，2-未发布
     *
     * @return send_status - 发布状态：0-草稿，1-发布，2-未发布
     */
    public Byte getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置发布状态：0-草稿，1-发布，2-未发布
     *
     * @param sendStatus 发布状态：0-草稿，1-发布，2-未发布
     */
    public void setSendStatus(Byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取封面
     *
     * @return cover - 封面
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置封面
     *
     * @param cover 封面
     */
    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    /**
     * 获取点击量
     *
     * @return click_count - 点击量
     */
    public Long getClickCount() {
        if(this.clickCount==null){
            return 0L;
        }
        return clickCount;
    }

    /**
     * 设置点击量
     *
     * @param clickCount 点击量
     */
    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * 获取阅读成功率
     *
     * @return read_success_rate - 阅读成功率
     */
    public Double getReadSuccessRate() {
        if(this.readSuccessRate==null){
            return 0D;
        }

        return  NumberUtils.formatNubmer(readSuccessRate,2);
    }

    /**
     * 设置阅读成功率
     *
     * @param readSuccessRate 阅读成功率
     */
    public void setReadSuccessRate(Double readSuccessRate) {
        this.readSuccessRate = readSuccessRate;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取0-仅在企业内分享，1-可对外分享，2-不能分享
     *
     * @return shzre_set - 0-仅在企业内分享，1-可对外分享，2-不能分享
     */
    public Byte getShzreSet() {
        return shzreSet;
    }

    /**
     * 设置0-仅在企业内分享，1-可对外分享，2-不能分享
     *
     * @param shzreSet 0-仅在企业内分享，1-可对外分享，2-不能分享
     */
    public void setShzreSet(Byte shzreSet) {
        this.shzreSet = shzreSet;
    }

    /**
     * 获取摘要
     *
     * @return summary - 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 获取应接收人数
     *
     * @return receive_number - 应接收人数
     */
    public Integer getReceiveNumber() {
        if(this.receiveNumber==null){
            return 0;
        }
        return receiveNumber;
    }

    /**
     * 设置应接收人数
     *
     * @param receiveNumber 应接收人数
     */
    public void setReceiveNumber(Integer receiveNumber) {
        this.receiveNumber = receiveNumber;
    }

    /**
     * 获取创建人ID
     *
     * @return create_id - 创建人ID
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 设置创建人ID
     *
     * @param createId 创建人ID
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取发布类型，0-直接发布，1-定时发布
     *
     * @return send_type - 发布类型，0-直接发布，1-定时发布
     */
    public Byte getSendType() {
        return sendType;
    }

    /**
     * 设置发布类型，0-直接发布，1-定时发布
     *
     * @param sendType 发布类型，0-直接发布，1-定时发布
     */
    public void setSendType(Byte sendType) {
        this.sendType = sendType;
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getReadTime() {
        if(this.readTime==null){
            return 0;
        }
        return readTime;
    }

    public void setReadTime(Integer readTime) {
        this.readTime = readTime;
    }

    public Integer getSuccessReadNumber() {
        if(this.successReadNumber==null){
            return 0;
        }
        return successReadNumber;
    }

    public void setSuccessReadNumber(Integer successReadNumber) {
        this.successReadNumber = successReadNumber;
    }
}