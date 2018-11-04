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
 * 会员登录记录
 * </p>
 *
 * @author hyj
 * @since 2018-11-04
 */
@TableName("cps_member_login_log")
public class MemberLoginLog extends Model<MemberLoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 日志名称
     */
    private String logname;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 应用版本
     */
    private String appVer;
    /**
     * 用户更新app后的版本
     */
    @TableField("update_appver")
    private String updateAppver;
    /**
     * 渠道号
     */
    private String channel;
    /**
     * 会员id
     */
    private Integer memberid;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 是否执行成功
     */
    private String succeed;
    /**
     * 具体消息
     */
    private String message;
    /**
     * 登录ip
     */
    private String ip;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
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

    public String getUpdateAppver() {
        return updateAppver;
    }

    public void setUpdateAppver(String updateAppver) {
        this.updateAppver = updateAppver;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MemberLoginLog{" +
        "id=" + id +
        ", logname=" + logname +
        ", appId=" + appId +
        ", appVer=" + appVer +
        ", updateAppver=" + updateAppver +
        ", channel=" + channel +
        ", memberid=" + memberid +
        ", createtime=" + createtime +
        ", succeed=" + succeed +
        ", message=" + message +
        ", ip=" + ip +
        "}";
    }
}
