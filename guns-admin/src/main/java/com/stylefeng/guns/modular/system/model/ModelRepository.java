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
 * 
 * </p>
 *
 * @author hyj
 * @since 2018-11-19
 */
@TableName("repo_model_repository")
public class ModelRepository extends Model<ModelRepository> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 模特姓名
     */
    private String name;
    /**
     * 登录
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    private String password;
    private String pwdMD5;
    /**
     * 角色
     */
    private String role;
    /**
     * 头像
     */
    @TableField("pic_address")
    private String picAddress;
    private String description;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 备注
     */
    private String notes;
    private Integer sort;
    @TableField("is_del")
    private Integer isDel;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 身高
     */
    private String height;
    /**
     * 三围
     */
    private String measurements;
    /**
     * 地区
     */
    private Integer countriesid;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 特征
     */
    private String characteristics;
    /**
     * 出生年月
     */
    private String birth;
    /**
     * 关注数量
     */
    @TableField("focus_count")
    private Integer focusCount;
    /**
     * 是否开启私聊  1  开启 2 不开启
     */
    @TableField("is_chat")
    private String isChat;
    /**
     * 手机号码
     */
    private String mobile;
    @TableField("gmt_created")
    private Date gmtCreated;
    @TableField("gmt_modified")
    private Date gmtModified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwdMD5() {
        return pwdMD5;
    }

    public void setPwdMD5(String pwdMD5) {
        this.pwdMD5 = pwdMD5;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    public Integer getCountriesid() {
        return countriesid;
    }

    public void setCountriesid(Integer countriesid) {
        this.countriesid = countriesid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }

    public String getIsChat() {
        return isChat;
    }

    public void setIsChat(String isChat) {
        this.isChat = isChat;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
        return "ModelRepository{" +
        "id=" + id +
        ", name=" + name +
        ", userName=" + userName +
        ", password=" + password +
        ", pwdMD5=" + pwdMD5 +
        ", role=" + role +
        ", picAddress=" + picAddress +
        ", description=" + description +
        ", signature=" + signature +
        ", notes=" + notes +
        ", sort=" + sort +
        ", isDel=" + isDel +
        ", nickName=" + nickName +
        ", height=" + height +
        ", measurements=" + measurements +
        ", countriesid=" + countriesid +
        ", age=" + age +
        ", characteristics=" + characteristics +
        ", birth=" + birth +
        ", focusCount=" + focusCount +
        ", isChat=" + isChat +
        ", mobile=" + mobile +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
