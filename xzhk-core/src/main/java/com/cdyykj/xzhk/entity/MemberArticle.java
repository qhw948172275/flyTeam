package com.cdyykj.xzhk.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "r_member_article")
public class MemberArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 成员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 文章ID
     */
    private Integer article;

    /**
     * 开始阅读时间
     */
    @Column(name = "start_read_time")
    private Date startReadTime;

    /**
     * 结束阅读时间
     */
    @Column(name = "end_read_time")
    private Date endReadTime;

    /**
     * 是否阅读成功，0-否，1-是
     */
    @Column(name = "is_read_success")
    private Byte isReadSuccess;
    /**
     * 阅读时长
     */
    @Column(name = "read_time")
    private Long readTime;

    /**
     * IP地址
     */
    private String ip;

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
     * 获取文章ID
     *
     * @return article - 文章ID
     */
    public Integer getArticle() {
        return article;
    }

    /**
     * 设置文章ID
     *
     * @param article 文章ID
     */
    public void setArticle(Integer article) {
        this.article = article;
    }

    /**
     * 获取开始阅读时间
     *
     * @return start_read_time - 开始阅读时间
     */
    public Date getStartReadTime() {
        return startReadTime;
    }

    /**
     * 设置开始阅读时间
     *
     * @param startReadTime 开始阅读时间
     */
    public void setStartReadTime(Date startReadTime) {
        this.startReadTime = startReadTime;
    }

    /**
     * 获取结束阅读时间
     *
     * @return end_read_time - 结束阅读时间
     */
    public Date getEndReadTime() {
        return endReadTime;
    }

    /**
     * 设置结束阅读时间
     *
     * @param endReadTime 结束阅读时间
     */
    public void setEndReadTime(Date endReadTime) {
        this.endReadTime = endReadTime;
    }

    /**
     * 获取是否阅读成功，0-否，1-是
     *
     * @return is_read_success - 是否阅读成功，0-否，1-是
     */
    public Byte getIsReadSuccess() {
        return isReadSuccess;
    }

    /**
     * 设置是否阅读成功，0-否，1-是
     *
     * @param isReadSuccess 是否阅读成功，0-否，1-是
     */
    public void setIsReadSuccess(Byte isReadSuccess) {
        this.isReadSuccess = isReadSuccess;
    }

    /**
     * 获取IP地址
     *
     * @return ip - IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Long getReadTime() {
        if(this.readTime==null){
            return 0L;
        }
        return readTime;
    }

    public void setReadTime(Long readTime) {
        this.readTime = readTime;
    }
}