package com.cdyykj.xzhk.entity;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Table(name = "t_question_bank")
public class QuestionBank {
    /**
     * 题库ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 题目
     */
    @ApiModelProperty(value = "题目")
    private String title;
    /**
     * 题类型 0-选择题
     */
    @ApiModelProperty(value = "题类型 0-选择题")
    private Byte type;
    /**
     * 题内容
     */
    @ApiModelProperty(value = "题内容")
    private String content;
    /**
     * 正确答案
     */
    @ApiModelProperty(value = "正确答案")
    @Column(name="choose_result")
    private String chooseResult;

    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer mark;
    /**
     * 测试系统ID
     */
    @ApiModelProperty(value = "测试系统ID")
    @Column(name = "test_system_id")
    private Integer testSystemId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChooseResult() {
        return chooseResult;
    }

    public void setChooseResult(String chooseResult) {
        this.chooseResult = chooseResult;
    }

    public Integer getMark() {
        if(this.mark==null){
            return 0;
        }
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getTestSystemId() {
        return testSystemId;
    }

    public void setTestSystemId(Integer testSystemId) {
        this.testSystemId = testSystemId;
    }
}