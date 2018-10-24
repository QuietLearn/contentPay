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
 * @since 2018-10-24
 */
@TableName("cps_video")
public class Video extends Model<Video> {

    private static final long serialVersionUID = 1L;

    /**
     * 视频id
     */
    @TableId(value = "v_id", type = IdType.AUTO)
    private Integer vId;
    /**
     * 视频类型id
     */
    @TableField("t_id")
    private Integer tId;
    /**
     * 视频名
     */
    @TableField("v_name")
    private String vName;
    /**
     * 视频图片
     */
    @TableField("v_pic")
    private String vPic;
    /**
     * 发行日期
     */
    @TableField("v_publishyear")
    private Integer vPublishyear;
    /**
     * 添加时间
     */
    @TableField("v_addtime")
    private Integer vAddtime;
    /**
     * 消耗金币
     */
    @TableField("v_money")
    private Integer vMoney;
    /**
     * 是否需要会员 0是 1否
     */
    @TableField("v_req_vip")
    private Integer vReqVip;
    @TableField("gmt_created")
    private Date gmtCreated;
    @TableField("gmt_modified")
    private Date gmtModified;


    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getvPic() {
        return vPic;
    }

    public void setvPic(String vPic) {
        this.vPic = vPic;
    }

    public Integer getvPublishyear() {
        return vPublishyear;
    }

    public void setvPublishyear(Integer vPublishyear) {
        this.vPublishyear = vPublishyear;
    }

    public Integer getvAddtime() {
        return vAddtime;
    }

    public void setvAddtime(Integer vAddtime) {
        this.vAddtime = vAddtime;
    }

    public Integer getvMoney() {
        return vMoney;
    }

    public void setvMoney(Integer vMoney) {
        this.vMoney = vMoney;
    }

    public Integer getvReqVip() {
        return vReqVip;
    }

    public void setvReqVip(Integer vReqVip) {
        this.vReqVip = vReqVip;
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
        return this.vId;
    }

    @Override
    public String toString() {
        return "Video{" +
        "vId=" + vId +
        ", tId=" + tId +
        ", vName=" + vName +
        ", vPic=" + vPic +
        ", vPublishyear=" + vPublishyear +
        ", vAddtime=" + vAddtime +
        ", vMoney=" + vMoney +
        ", vReqVip=" + vReqVip +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
