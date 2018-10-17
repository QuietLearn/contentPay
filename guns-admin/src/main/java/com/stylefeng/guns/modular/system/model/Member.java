package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author hyj
 * @since 2018-10-16
 */
@TableName("cps_member")
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 明文密码
     */
    private String password;
    /**
     * md5加密密码
     */
    @TableField("Md5_password")
    private String md5Password;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 头像图片地址
     */
    @TableField("pic_address")
    private String picAddress;
    /**
     * 手机
     */
    private String mobile;
    /**
     * qq
     */
    private String qq;
    /**
     * 微信
     */
    private String weixin;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 会员类型 表id
     */
    @TableField("member_type_id")
    private Integer memberTypeId;
    /**
     * 会员类型名称
     */
    @TableField("member_type_name")
    private String memberTypeName;
    /**
     * 会员等级
     */
    @TableField("vip_level")
    private Integer vipLevel;
    /**
     * 购买时间
     */
    @TableField("buy_time")
    private Date buyTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 有效时间
     */
    private Integer aging;
    /**
     * 积分
     */
    private Integer points;
    /**
     * 收藏夹ids ，分隔
     */
    @TableField("favorites_ids")
    private String favoritesIds;
    /**
     * 最后登录时间
     */
    @TableField("last_login")
    private Date lastLogin;
    /**
     * 最后登录ip
     */
    @TableField("last_loginIp")
    private String lastLoginip;
    /**
     * 注册时间
     */
    @TableField("register_time")
    private Date registerTime;
    /**
     * 注册ip
     */
    @TableField("register_ip")
    private String registerIp;
    /**
     * uuid唯一值
     */
    @TableField("UUID_token")
    private String uuidToken;
    /**
     * 总消费额
     */
    @TableField("total_money")
    private Integer totalMoney;
    /**
     * 是否锁定 0 是 １否
     */
    @TableField("is_lock")
    private Integer isLock;
    /**
     * 逻辑删除 0 是 １否
     */
    @TableField("is_del")
    private Integer isDel;
    /**
     * 创建时间
     */
    @TableField("gmt_created")
    private Date gmtCreated;
    /**
     * 更改时间
     */
    @TableField("gmt_modified")
    private Date gmtModified;


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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMd5Password() {
        return md5Password;
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMemberTypeId() {
        return memberTypeId;
    }

    public void setMemberTypeId(Integer memberTypeId) {
        this.memberTypeId = memberTypeId;
    }

    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getAging() {
        return aging;
    }

    public void setAging(Integer aging) {
        this.aging = aging;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getFavoritesIds() {
        return favoritesIds;
    }

    public void setFavoritesIds(String favoritesIds) {
        this.favoritesIds = favoritesIds;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLoginip() {
        return lastLoginip;
    }

    public void setLastLoginip(String lastLoginip) {
        this.lastLoginip = lastLoginip;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getUuidToken() {
        return uuidToken;
    }

    public void setUuidToken(String uuidToken) {
        this.uuidToken = uuidToken;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Member{" +
        "id=" + id +
        ", username=" + username +
        ", nickname=" + nickname +
        ", password=" + password +
        ", md5Password=" + md5Password +
        ", gender=" + gender +
        ", picAddress=" + picAddress +
        ", mobile=" + mobile +
        ", qq=" + qq +
        ", weixin=" + weixin +
        ", email=" + email +
        ", memberTypeId=" + memberTypeId +
        ", memberTypeName=" + memberTypeName +
        ", vipLevel=" + vipLevel +
        ", buyTime=" + buyTime +
        ", endTime=" + endTime +
        ", aging=" + aging +
        ", points=" + points +
        ", favoritesIds=" + favoritesIds +
        ", lastLogin=" + lastLogin +
        ", lastLoginip=" + lastLoginip +
        ", registerTime=" + registerTime +
        ", registerIp=" + registerIp +
        ", uuidToken=" + uuidToken +
        ", totalMoney=" + totalMoney +
        ", isLock=" + isLock +
        ", isDel=" + isDel +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
