package com.stylefeng.guns.modular.system.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author hyj
 * @since 2018-10-16
 */
@TableName("cps_member")
@ApiModel(value = "MemberVo", description = "用户更新信息")
public class MemberVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(hidden = true,example = "1")
    private Integer id;

    /**
     * uuid 缓存
     */
    @ApiModelProperty(value = "uuid唯一识别码", required = true)
    private String uuidToken;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "昵称", required = false)
    private String username;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", example = "1")
    private Integer gender;
    /**
     * 头像图片地址
     */
    @ApiModelProperty(value = "头像图片地址", required = false)
    private String picAddress;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机", required = false)
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = false)
    private String email;

    /**
     * 会员类型名称
     */
    @ApiModelProperty(hidden = true)
    private String memberTypeName;

    /**
     * 积分
     */
    @ApiModelProperty(hidden = true,example = "0")
    private Integer points;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private Date birthday;

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUuidToken() {
        return uuidToken;
    }

    public void setUuidToken(String uuidToken) {
        this.uuidToken = uuidToken;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Member{" +
        "id=" + id +
        ", username=" + username +
        ", gender=" + gender +
        ", picAddress=" + picAddress +
        ", mobile=" + mobile +
        ", email=" + email +
        ", memberTypeName=" + memberTypeName +
        ", points=" + points +
        "}";
    }
}
