package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-09
 */
@TableName("cps_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单号
     */
    @TableField("order_no")
    private Long orderNo;
    /**
     * 支付渠道
     */
    private String channelId;
    /**
     * 用户id
     */
    @TableField("member_id")
    private Integer memberId;
    /**
     * 实际付款金额,单位是元,保留两位小数
     */
    private BigDecimal payment;
    /**
     * 支付类型,1-在线支付
     */
    @TableField("payment_type")
    private Integer paymentType;
    /**
     * 订单状态:0-取消支付-10-待支付，20-支付成功,30-支付失败
     */
    private Integer status;
    /**
     * 购买商品类型  1 会员　２　自定义商品 3积分商品
     */
    @TableField("buy_type")
    private Integer buyType;
    private Integer appId;
    /**
     * 机型 1 IOS 2 安卓
     */
    @TableField("pay_client")
    private Integer payClient;
    /**
     * 支付时间
     */
    @TableField("payment_time")
    private Date paymentTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getPayClient() {
        return payClient;
    }

    public void setPayClient(Integer payClient) {
        this.payClient = payClient;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", channelId=" + channelId +
        ", memberId=" + memberId +
        ", payment=" + payment +
        ", paymentType=" + paymentType +
        ", status=" + status +
        ", buyType=" + buyType +
        ", appId=" + appId +
        ", payClient=" + payClient +
        ", paymentTime=" + paymentTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
