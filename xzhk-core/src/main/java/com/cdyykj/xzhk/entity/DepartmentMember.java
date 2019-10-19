package com.cdyykj.xzhk.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Table(name = "t_department_member")
public class DepartmentMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 成员UserID
     */
    @ApiModelProperty(value = "成员UserID")
    @Column(name = "user_id")
    private String userId;

    /**
     * 	成员名称
     */
    @ApiModelProperty(value = "成员名称")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;


    /**
     * 职务信息
     */
    @ApiModelProperty(value = "职务信息")
    private String position;

    /**
     * 0表示未定义，1表示男性，2表示女性
     */
    @ApiModelProperty(value = "0表示未定义，1表示男性，2表示女性")
    private Byte gender=0;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;



    /**
     * 头像url
     */
    @ApiModelProperty(value = "头像url")
    private String avatar;

    /**
     * 座机
     */
    @ApiModelProperty(value = "座机")
    private String telephone;

    /**
     * 激活状态: 1=已激活，2=已禁用，4=未激活 
     */
    @ApiModelProperty(value = "激活状态: 1=已激活，2=已禁用，4=未激活 ")
    private Byte status;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

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
     * 获取成员UserID
     *
     * @return user_id - 成员UserID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置成员UserID
     *
     * @param userId 成员UserID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取	成员名称
     *
     * @return name - 	成员名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置	成员名称
     *
     * @param name 	成员名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }



    /**
     * 获取职务信息
     *
     * @return position - 职务信息
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职务信息
     *
     * @param position 职务信息
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 获取0表示未定义，1表示男性，2表示女性
     *
     * @return gender - 0表示未定义，1表示男性，2表示女性
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 设置0表示未定义，1表示男性，2表示女性
     *
     * @param gender 0表示未定义，1表示男性，2表示女性
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    /**
     * 获取头像url
     *
     * @return avatar - 头像url
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像url
     *
     * @param avatar 头像url
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 获取座机
     *
     * @return telephone - 座机
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置座机
     *
     * @param telephone 座机
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 获取激活状态: 1=已激活，2=已禁用，4=未激活 
     *
     * @return status - 激活状态: 1=已激活，2=已禁用，4=未激活 
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置激活状态: 1=已激活，2=已禁用，4=未激活 
     *
     * @param status 激活状态: 1=已激活，2=已禁用，4=未激活 
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}