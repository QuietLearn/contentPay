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
 * @since 2018-10-28
 */
@TableName("cps_note")
public class Note extends Model<Note> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 信息
     */
    private String message;
    /**
     * 短信有效时间(分)
     */
    private Integer aging;
    @TableField("is_del")
    private Integer isDel;
    /**
     * 发送手机
     */
    private String mobile;
    @TableField("gmt_created")
    private Date gmtCreated;
    @TableField("gmt_updated")
    private Date gmtUpdated;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 应用版本
     */
    private String appVer;
    /**
     * 渠道号
     */
    private String channel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAging() {
        return aging;
    }

    public void setAging(Integer aging) {
        this.aging = aging;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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

    public Date getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Note{" +
        "id=" + id +
        ", message=" + message +
        ", aging=" + aging +
        ", isDel=" + isDel +
        ", mobile=" + mobile +
        ", gmtCreated=" + gmtCreated +
        ", gmtUpdated=" + gmtUpdated +
        ", appId=" + appId +
        ", appVer=" + appVer +
        ", channel=" + channel +
        "}";
    }
}
