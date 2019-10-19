package com.cdyykj.xzhk.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Table(name = "r_test_system_member")
public class RTestSystemMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 测试系统ID
     */
    @ApiModelProperty(value="测试系统ID")
    @Column(name = "test_system_id")
    private Integer testSystemId;

    /**
     * 成员ID
     */
    @ApiModelProperty(value="成员ID")
    @Column(name = "member_id")
    private Integer memberId;
    /**
     * 是否测试0-否，1-是
     */
    @ApiModelProperty(value=" 是否测试0-否，1-是")
    @Column(name="is_test")
    private Byte isTest;

    /**
     * 是否合格0-未通过，1-通过
     */
    @ApiModelProperty(value = "是否合格，0-未通过，1-通过")
    @Column(name = "is_pass")
    private Byte isPass;

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

    public Byte getIsTest() {
        return isTest;
    }

    public void setIsTest(Byte isTest) {
        this.isTest = isTest;
    }

    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }
}