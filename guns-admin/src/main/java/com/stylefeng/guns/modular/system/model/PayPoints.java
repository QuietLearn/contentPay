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
 * @author wangyang
 * @since 2018-10-14
 */
@TableName("pay_points")
public class PayPoints extends Model<PayPoints> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String appid;
    /**
     * 用户id
     */
    private Integer accoutid;
    /**
     * 模豆购买类型  5  8  30  50  88  168  388  448  998  1648  2998  6498  
     */
    private Integer type;
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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Integer getAccoutid() {
        return accoutid;
    }

    public void setAccoutid(Integer accoutid) {
        this.accoutid = accoutid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "PayPoints{" +
        "id=" + id +
        ", appid=" + appid +
        ", accoutid=" + accoutid +
        ", type=" + type +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
