package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 埋点
 * </p>
 *
 * @author hyj
 * @since 2018-11-02
 */
@TableName("cps_buried_point")
@ApiModel(value = "BuriedPoint", description = "埋点信息")
public class BuriedPoint extends Model<BuriedPoint> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true,example = "1")
    private Integer id;
    /**
     * app  id
     */
    @TableField("app_id")
    @ApiModelProperty(value = "应用id", required = true)
    private String appId;
    /**
     * app 版本号
     */
    @TableField("app_ver")
    @ApiModelProperty(value = "uuid唯一识别码", required = true)
    private String appVer;
    /**
     *  渠道号
     */
    @TableField("channel_id")
    @ApiModelProperty(value = "分发渠道", required = true)
    private String channelId;
    /**
     * 运营商 1移动 2联通 3 电信
     */
    @ApiModelProperty(hidden = true)
    private String operator;
    /**
     * 省份 地区
     */
    @ApiModelProperty(hidden = true)
    private String address;
    /**
     * 移动设备识别码
     */
    @TableField("IMSI")
    @ApiModelProperty(value = "移动设备识别码 ", required = true)
    private String imsi;
    /**
     * 移动设备身份码
     */
    @TableField("IMEI")
    @ApiModelProperty(value = "移动设备身份码 ", required = true)
    private String imei;
    /**
     * 100  开启　101 激活　102　弹出计费框　 103 切换到后台 104 切回应用 105 应用退出 106 应用崩溃 107 注册  108 取消注册 109 忘记密码 111 登录  112 登录取消 113 首页  115 频道页   117 列表页 119 详情页 121 播放页
     */
    @TableField("point_id")
    @ApiModelProperty(value = "埋点码(埋点接口需要)", example = "100")
    private Integer pointId;
    /**
     * 埋点信息
     */
    @TableField("point_message")
    @ApiModelProperty(hidden = true)
    private String pointMessage;
    /**
     * 支付类型 1 ZZF  2 DM 3 LFT
     */
    @TableField("pay_type")
    @ApiModelProperty(hidden = true)
    private String payType;
    /**
     * 用户唯一识别号码
     */
    @TableField("UUID")
    @ApiModelProperty(hidden = true)
    private String uuid;

    @TableField("is_del")
    @ApiModelProperty(hidden = true,example = "1")
    private Integer isDel;
    /**
     * 网络类型
     */
    @TableField("wifi_type")
    @ApiModelProperty(value = "网络类型" )
    private String wifiType;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码" )
    private String mobile;
    /**
     * 手机识别码
     */
    @TableField("ICCID")
    @ApiModelProperty(value = "手机识别码" )
    private String iccid;
    /**
     * 手机型号
     */
    @TableField("phone_type")
    @ApiModelProperty(hidden = true)
    private String phoneType;
    /**
     * 错误信息
     */
    @TableField("error_message")
    @ApiModelProperty(hidden = true)
    private String errorMessage;
    /**
     * //机型  1 安卓 2 ios
     */
    @TableField("point_type")
    @ApiModelProperty(value = "机型 1 安卓 2 ios", example = "1")
    private Integer pointType;
    /**
     * 广告标识
     */
    @ApiModelProperty(hidden = true)
    private String idfa;
    @TableField("open_UDID")
    @ApiModelProperty(hidden = true)
    private String openUdid;
    /**
     * 创建时间
     */
    @TableField("gmt_created")
    @ApiModelProperty(hidden = true)
    private Date gmtCreated;
    @TableField("gmt_modified")
    @ApiModelProperty(hidden = true)
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

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public String getPointMessage() {
        return pointMessage;
    }

    public void setPointMessage(String pointMessage) {
        this.pointMessage = pointMessage;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getWifiType() {
        return wifiType;
    }

    public void setWifiType(String wifiType) {
        this.wifiType = wifiType;
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

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
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
        return "BuriedPoint{" +
        "id=" + id +
        ", appId=" + appId +
        ", appVer=" + appVer +
        ", channelId=" + channelId +
        ", operator=" + operator +
        ", address=" + address +
        ", imsi=" + imsi +
        ", imei=" + imei +
        ", pointId=" + pointId +
        ", pointMessage=" + pointMessage +
        ", payType=" + payType +
        ", uuid=" + uuid +
        ", isDel=" + isDel +
        ", wifiType=" + wifiType +
        ", mobile=" + mobile +
        ", iccid=" + iccid +
        ", phoneType=" + phoneType +
        ", errorMessage=" + errorMessage +
        ", pointType=" + pointType +
        ", idfa=" + idfa +
        ", openUdid=" + openUdid +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
