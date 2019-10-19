package com.cdyykj.xzhk.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_test_system")
public class TestSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 测试系统名称
     */
    @ApiModelProperty(value="测试系统名称")
    private String name;

    /**
     * 发布日期
     */
    @ApiModelProperty(value="发布日期")
    @Column(name = "push_time")
    private Date pushTime;

    /**
     * 开始测试时间
     */
    @ApiModelProperty(value = "开始测试时间")
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 接收人数
     */
    @ApiModelProperty(value="接收人数")
    @Column(name = "receive_number")
    private Integer receiveNumber;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    @Column(name = "create_time")
    private Date createTime;
    @ApiModelProperty(value = "截至日期")
    @Column(name = "end_date")
    private Date endDate;
    /**
     * 题数量
     */
    @ApiModelProperty(value="题数量")
    @Column(name = "question_count")
    private Byte questionCount;
    /**
     * 总分
     */
    @ApiModelProperty(value = "总分")
    private Integer marks;
    /**
     * 创建人ID
     */
    @ApiModelProperty(value="创建人ID")
    private Integer createId;
    /**
     *0-草稿，1-发布，2-删除
     */
    @ApiModelProperty(value = "0-草稿，1-发布，2-删除,3定时发布")
    private Byte status;

    /**
     * 及格分数
     */
    @ApiModelProperty(value = "及格分数")
    @Column(name = "pass_mark")
    private Integer passMark;


    /**
     * 及格人数
     */
    @ApiModelProperty(value = "及格人数")
    @Column(name = "pass_count")
    private Integer passCount;

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
     * 获取标题名称
     *
     * @return name - 标题名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标题名称
     *
     * @param name 标题名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取发布日期
     *
     * @return push_time - 发布日期
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * 设置发布日期
     *
     * @param pushTime 发布日期
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * 获取接收人数
     *
     * @return receive_number - 接收人数
     */
    public Integer getReceiveNumber() {
        if(this.receiveNumber==null){
            return 0;
        }
        return receiveNumber;
    }

    /**
     * 设置接收人数
     *
     * @param receiveNumber 接收人数
     */
    public void setReceiveNumber(Integer receiveNumber) {
        this.receiveNumber = receiveNumber;
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
     * 获取题数量
     *
     * @return question_count - 题数量
     */
    public Byte getQuestionCount() {
        return questionCount;
    }

    /**
     * 设置题数量
     *
     * @param questionCount 题数量
     */
    public void setQuestionCount(Byte questionCount) {
        this.questionCount = questionCount;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getMarks() {
        if(this.marks==null){
            return 0;
        }
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getPassMark() {
        if(this.passMark==null){
            return 0;
        }
        return passMark;
    }

    public void setPassMark(Integer passMark) {
        this.passMark = passMark;
    }

    public Integer getPassCount() {
        if(this.passCount==null){
            return 0;
        }
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}