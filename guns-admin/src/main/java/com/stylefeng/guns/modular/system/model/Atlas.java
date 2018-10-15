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
@TableName("cps_atlas")
public class Atlas extends Model<Atlas> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("account_id")
    private Integer accountId;
    /**
     * 图片编号
     */
    private Integer picId;
    /**
     * 图片名称
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 支付积分
     */
    @TableField("pay_points")
    private Integer payPoints;
    /**
     * 标签类型
     */
    @TableField("tags_ids")
    private String tagsIds;
    /**
     * 封面
     */
    @TableField("cover_pic")
    private String coverPic;
    /**
     * 图集类型
     */
    @TableField("type_id")
    private Integer typeId;
    /**
     * 图集编号
     */
    private Integer number;
    @TableField("is_del")
    private Integer isDel;
    /**
     * 图片地址
     */
    private String picaddress;
    private Date gmtcreated;
    private Date gmtmodified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPayPoints() {
        return payPoints;
    }

    public void setPayPoints(Integer payPoints) {
        this.payPoints = payPoints;
    }

    public String getTagsIds() {
        return tagsIds;
    }

    public void setTagsIds(String tagsIds) {
        this.tagsIds = tagsIds;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getPicaddress() {
        return picaddress;
    }

    public void setPicaddress(String picaddress) {
        this.picaddress = picaddress;
    }

    public Date getGmtcreated() {
        return gmtcreated;
    }

    public void setGmtcreated(Date gmtcreated) {
        this.gmtcreated = gmtcreated;
    }

    public Date getGmtmodified() {
        return gmtmodified;
    }

    public void setGmtmodified(Date gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Atlas{" +
        "id=" + id +
        ", accountId=" + accountId +
        ", picId=" + picId +
        ", title=" + title +
        ", description=" + description +
        ", payPoints=" + payPoints +
        ", tagsIds=" + tagsIds +
        ", coverPic=" + coverPic +
        ", typeId=" + typeId +
        ", number=" + number +
        ", isDel=" + isDel +
        ", picaddress=" + picaddress +
        ", gmtcreated=" + gmtcreated +
        ", gmtmodified=" + gmtmodified +
        "}";
    }
}
