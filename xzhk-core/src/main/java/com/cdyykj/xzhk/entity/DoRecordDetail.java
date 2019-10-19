package com.cdyykj.xzhk.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Table(name = "t_do_record_detail")
public class DoRecordDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 成员做题统计ID
     */
    @Column(name = "do_record_id")
    @ApiModelProperty(value = "成员做题统计ID")
    private Integer doRecordId;

    /**
     * 题ID
     */
    @Column(name = "question_brank_id")
    @ApiModelProperty(value = "题ID")
    private Integer questionBrankId;

    /**
     * 成员提交的答案
     */
    @ApiModelProperty(value = "成员提交的答案")
    private String answer;

    /**
     * 成员ID
     */
    @Column(name = "member_id")
    @ApiModelProperty(value = "成员ID")
    private Integer memberId;

    /**
     * 得分
     */
    @ApiModelProperty(value = "得分")
    private Byte mark;

    /**
     * 是否正确：0-否，1-是
     */
    @Column(name = "is_right")
    @ApiModelProperty(value = "是否正确：0-否，1-是")
    private Byte isRight;

    @Column(name = "t_do_record_id")
    private Integer tDoRecordId;

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
     * 获取成员做题统计ID
     *
     * @return do_record_id - 成员做题统计ID
     */
    public Integer getDoRecordId() {
        return doRecordId;
    }

    /**
     * 设置成员做题统计ID
     *
     * @param doRecordId 成员做题统计ID
     */
    public void setDoRecordId(Integer doRecordId) {
        this.doRecordId = doRecordId;
    }

    /**
     * 获取题ID
     *
     * @return question_brank_id - 题ID
     */
    public Integer getQuestionBrankId() {
        return questionBrankId;
    }

    /**
     * 设置题ID
     *
     * @param questionBrankId 题ID
     */
    public void setQuestionBrankId(Integer questionBrankId) {
        this.questionBrankId = questionBrankId;
    }

    /**
     * 获取成员提交的答案
     *
     * @return answer - 成员提交的答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置成员提交的答案
     *
     * @param answer 成员提交的答案
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    /**
     * 获取成员ID
     *
     * @return member_id - 成员ID
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 设置成员ID
     *
     * @param memberId 成员ID
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取得分
     *
     * @return mark - 得分
     */
    public Byte getMark() {
        return mark;
    }

    /**
     * 设置得分
     *
     * @param mark 得分
     */
    public void setMark(Byte mark) {
        this.mark = mark;
    }

    /**
     * 获取是否正确：0-否，1-是
     *
     * @return is_right - 是否正确：0-否，1-是
     */
    public Byte getIsRight() {
        return isRight;
    }

    /**
     * 设置是否正确：0-否，1-是
     *
     * @param isRight 是否正确：0-否，1-是
     */
    public void setIsRight(Byte isRight) {
        this.isRight = isRight;
    }

    /**
     * @return t_do_record_id
     */
    public Integer gettDoRecordId() {
        return tDoRecordId;
    }

    /**
     * @param tDoRecordId
     */
    public void settDoRecordId(Integer tDoRecordId) {
        this.tDoRecordId = tDoRecordId;
    }
}