package com.cdyykj.xzhk.entity;

import javax.persistence.*;

@Table(name = "r_department_member")
public class RDepartmentMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 部门ID
     */
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * 成员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 部门内的排序值
     */
    @Column(name = "mb_order")
    private Integer mbOrder;

    /**
     * 表示在所在的部门内是否为上级
     */
    @Column(name = "is_leaderIn_dept")
    private Byte isLeaderInDept;

    /**
     * 不操作数据库
     */
    @Transient
    private String userId;

    /**
     * 获取表示在所在的部门内是否为上级
     *
     * @return is_leader_in_dept - 表示在所在的部门内是否为上级
     */
    public Byte getIsLeaderInDept() {
        return isLeaderInDept;
    }

    /**
     * 设置表示在所在的部门内是否为上级
     *
     * @param isLeaderInDept 表示在所在的部门内是否为上级
     */
    public void setIsLeaderInDept(Byte isLeaderInDept) {
        this.isLeaderInDept = isLeaderInDept;
    }


    public Integer getMbOrder() {
        return mbOrder;
    }

    public void setMbOrder(Integer mbOrder) {
        this.mbOrder = mbOrder;
    }

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
     * 获取部门ID
     *
     * @return department_id - 部门ID
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置部门ID
     *
     * @param departmentId 部门ID
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}