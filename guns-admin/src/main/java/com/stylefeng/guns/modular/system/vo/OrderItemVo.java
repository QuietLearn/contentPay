package com.stylefeng.guns.modular.system.vo;

import com.baomidou.mybatisplus.annotations.TableField;

import java.util.Date;

/**
 * Created by hyj on 2018/10/18
 */
public class OrderItemVo {
    private Integer memberId;

    private Long orderNo;
    /**
     * '购买商品类型 1 会员　2　自定义商品 3积分商品  4 模豆提取码商品'
     */
    private Integer buyType;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 单价
     */
    private Integer price;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 商品总价,单位是元,保留两位小数
     */
    private Integer totalPrice;

    private Date createTime;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
