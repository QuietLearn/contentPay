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
 * 数据追踪-应用统计-激活统计表
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
@TableName("cps_active")
public class Active extends Model<Active> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * app id
     */
    private String appId;
    /**
     * app 版本号
     */
    private String appVer;
    /**
     *  渠道号
     */
    private String channelId;
    /**
     * 运营商
     */
    private String operator;
    /**
     * 省份 地区
     */
    private String address;
    /**
     * 移动设备识别码
     */
    @TableField("IMSI")
    private String imsi;
    /**
     * 移动设备身份码
     */
    @TableField("IMEI")
    private String imei;
    /**
     * 短信中心号码
     */
    private String smsc;
    /**
     * 是否删除 0 未删除 1 删除
     */
    @TableField("is_del")
    private Integer isDel;
    /**
     * 0  开启   1  激活  2  手动开启
     */
    @TableField("active_type")
    private Integer activeType;
    /**
     * 手机型号
     */
    @TableField("phone_type")
    private String phoneType;
    /**
     * 手机品牌
     */
    @TableField("phone_brand")
    private String phoneBrand;
    /**
     * 手机系统
     */
    @TableField("phone_system")
    private String phoneSystem;
    /**
     * 分辨率
     */
    @TableField("DPI")
    private String dpi;
    @TableField("UUID")
    private String uuid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 手机识别码
     */
    @TableField("ICCID")
    private String iccid;
    /**
     * 埋点类型 1 安卓 2ios
     */
    @TableField("point_type")
    private Integer pointType;
    /**
     * 网络类型
     */
    @TableField("wifi_type")
    private String wifiType;
    /**
     * 广告标识
     */
    private String idfa;
    @TableField("open_UDID")
    private String openUdid;
    /**
     * 1 代表来自头条
     */
    @TableField("is_toutiao")
    private Integer isToutiao;
    /**
     * 创建时间
     */
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSmsc() {
        return smsc;
    }

    public void setSmsc(String smsc) {
        this.smsc = smsc;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getActiveType() {
        return activeType;
    }

    public void setActiveType(Integer activeType) {
        this.activeType = activeType;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public String getWifiType() {
        return wifiType;
    }

    public void setWifiType(String wifiType) {
        this.wifiType = wifiType;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getOpenUdid() {
        return openUdid;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    public Integer getIsToutiao() {
        return isToutiao;
    }

    public void setIsToutiao(Integer isToutiao) {
        this.isToutiao = isToutiao;
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
        return "Active{" +
        "id=" + id +
        ", appId=" + appId +
        ", appVer=" + appVer +
        ", channelId=" + channelId +
        ", operator=" + operator +
        ", address=" + address +
        ", imsi=" + imsi +
        ", imei=" + imei +
        ", smsc=" + smsc +
        ", isDel=" + isDel +
        ", activeType=" + activeType +
        ", phoneType=" + phoneType +
        ", phoneBrand=" + phoneBrand +
        ", phoneSystem=" + phoneSystem +
        ", dpi=" + dpi +
        ", uuid=" + uuid +
        ", mobile=" + mobile +
        ", iccid=" + iccid +
        ", pointType=" + pointType +
        ", wifiType=" + wifiType +
        ", idfa=" + idfa +
        ", openUdid=" + openUdid +
        ", isToutiao=" + isToutiao +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
