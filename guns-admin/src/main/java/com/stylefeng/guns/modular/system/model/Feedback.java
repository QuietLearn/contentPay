package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 回馈表
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
@TableName("cps_feedback")
public class Feedback extends Model<Feedback> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true,example = "0")
    private Integer id;
    /**
     * 反馈信息
     */
    @ApiModelProperty(value = "反馈信息 ", required = true)
    private String info;
    /**
     * 用户id
     */
    @ApiModelProperty(hidden = true,example = "0")
    private Integer memberId;
    /**
     * 用户名
     */
    @TableField("member_name")
    @ApiModelProperty(hidden = true)
    private String memberName;

    @TableField("feedback_type")
    @ApiModelProperty(value = "反馈类型(code：如1,2,3) ", required = true)
    private String feedbackType;
    /**
     * 是否删除 0 是 1否
     */
    @TableField("is_del")
    @ApiModelProperty(hidden = true,example = "0")
    private Integer isDel;
    @TableField("gmt_created")
    @ApiModelProperty(hidden = true)
    private Date gmtCreated;
    @TableField("gmt_modified")
    @ApiModelProperty(hidden = true)
    private Date gmtModified;
    /**
     * 应用id
     */
    @ApiModelProperty(value = "app id", required = true,example = "0")
    private Integer appId;
    /**
     * 应用版本
     */
    @ApiModelProperty(value = "app版本", required = true)
    private String appVer;
    /**
     * 渠道号
     */
    @ApiModelProperty(value = "分发渠道", required = true)
    private String channel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
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
        return "Feedback{" +
        "id=" + id +
        ", info=" + info +
        ", memberId=" + memberId +
        ", memberName=" + memberName +
        ", feedbackType=" + feedbackType +
        ", isDel=" + isDel +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        ", appId=" + appId +
        ", appVer=" + appVer +
        ", channel=" + channel +
        "}";
    }
}
