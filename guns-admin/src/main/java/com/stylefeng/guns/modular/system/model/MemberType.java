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
 * 会员购买详情表_待定（一种商品/付费类型）
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-15
 */
@TableName("cps_member_type")
public class MemberType extends Model<MemberType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     *  1001 普通用户 1001 包月  1002 包年
     */
    @TableField("server_code")
    private Integer serverCode;
    /**
     * 服务名称
     */
    @TableField("server_name")
    private String serverName;
    /**
     * 购买单价
     */
    private BigDecimal price;
    /**
     * 有效持续时间
     */
    private Long aging;
    /**
     * 逻辑删除
     */
    @TableField("is_del")
    private Integer isDel;
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

    public Integer getServerCode() {
        return serverCode;
    }

    public void setServerCode(Integer serverCode) {
        this.serverCode = serverCode;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAging() {
        return aging;
    }

    public void setAging(Long aging) {
        this.aging = aging;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MemberType{" +
        "id=" + id +
        ", serverCode=" + serverCode +
        ", serverName=" + serverName +
        ", price=" + price +
        ", aging=" + aging +
        ", isDel=" + isDel +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
