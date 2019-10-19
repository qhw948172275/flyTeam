package com.cdyykj.xzhk.entity;

import javax.persistence.*;

@Table(name = "t_do_record")
public class DoRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 成员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 总分数
     */
    private Integer marks;

    /**
     * 正确答案数量
     */
    @Column(name = "right_count")
    private Byte rightCount;

    /**
     * 错误答案数量
     */
    @Column(name = "error_count")
    private Byte errorCount;

    /**
     * 测试系统ID
     */
    @Column(name = "test_system_id")
    private Integer testSystemId;

    @Column(name = "t_test_system_id")
    private Integer tTestSystemId;

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
     * 获取总分数
     *
     * @return marks - 总分数
     */
    public Integer getMarks() {
        return marks;
    }

    /**
     * 设置总分数
     *
     * @param marks 总分数
     */
    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    /**
     * 获取正确答案数量
     *
     * @return right_count - 正确答案数量
     */
    public Byte getRightCount() {
        return rightCount;
    }

    /**
     * 设置正确答案数量
     *
     * @param rightCount 正确答案数量
     */
    public void setRightCount(Byte rightCount) {
        this.rightCount = rightCount;
    }

    /**
     * 获取错误答案数量
     *
     * @return error_count - 错误答案数量
     */
    public Byte getErrorCount() {
        return errorCount;
    }

    /**
     * 设置错误答案数量
     *
     * @param errorCount 错误答案数量
     */
    public void setErrorCount(Byte errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * 获取测试系统ID
     *
     * @return test_system_id - 测试系统ID
     */
    public Integer getTestSystemId() {
        return testSystemId;
    }

    /**
     * 设置测试系统ID
     *
     * @param testSystemId 测试系统ID
     */
    public void setTestSystemId(Integer testSystemId) {
        this.testSystemId = testSystemId;
    }

    /**
     * @return t_test_system_id
     */
    public Integer gettTestSystemId() {
        return tTestSystemId;
    }

    /**
     * @param tTestSystemId
     */
    public void settTestSystemId(Integer tTestSystemId) {
        this.tTestSystemId = tTestSystemId;
    }
}