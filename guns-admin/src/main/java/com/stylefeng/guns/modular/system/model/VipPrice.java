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
 * @since 2018-11-23
 */
@TableName("cps_vip_price")
public class VipPrice extends Model<VipPrice> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户类型
     */
    @TableField("member_type_code")
    private Integer memberTypeCode;
    /**
     * 购买天数
     */
    private String name;
    /**
     * 价格
     */
    private String price;
    /**
     * 持续时间
     */
    private String aging;
    /**
     * 拥有权限
     */
    private Integer authority;
    @TableField("gmt_created")
    private Date gmtCreated;
    @TableField("gmt_updated")
    private Date gmtUpdated;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberTypeCode() {
        return memberTypeCode;
    }

    public void setMemberTypeCode(Integer memberTypeCode) {
        this.memberTypeCode = memberTypeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAging() {
        return aging;
    }

    public void setAging(String aging) {
        this.aging = aging;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "VipPrice{" +
        "id=" + id +
        ", memberTypeCode=" + memberTypeCode +
        ", name=" + name +
        ", price=" + price +
        ", aging=" + aging +
        ", authority=" + authority +
        ", gmtCreated=" + gmtCreated +
        ", gmtUpdated=" + gmtUpdated +
        "}";
    }
}
