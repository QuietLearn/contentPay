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
 * 图集资源库表
 * </p>
 *
 * @author hyj
 * @since 2018-11-19
 */
@TableName("repo_pictures_category")
public class PicturesCategory extends Model<PicturesCategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 图片分类集id(图集分类)
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 图集标签
     */
    private String labelIds;
    /**
     * 图集名称
     */
    private String name;
    /**
     * 图片数量
     */
    @TableField("pictures_num")
    private Integer picturesNum;
    /**
     * 浏览次数
     */
    private Integer views;
    /**
     * 所属模特
     */
    private Integer modelId;
    /**
     * 点赞次数
     */
    @TableField("thumbUp_count")
    private Integer thumbupCount;
    /**
     * 分享次数
     */
    @TableField("share_count")
    private Integer shareCount;
    /**
     * 收藏次数
     */
    @TableField("collection_count")
    private Integer collectionCount;
    /**
     * 图片地址
     */
    @TableField("pic_address")
    private String picAddress;
    /**
     * 缩略图地址
     */
    @TableField("scale_picAddress")
    private String scalePicaddress;
    @TableField("is_del")
    private Integer isDel;
    /**
     * 封面图片地址
     */
    private String cover;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPicturesNum() {
        return picturesNum;
    }

    public void setPicturesNum(Integer picturesNum) {
        this.picturesNum = picturesNum;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getThumbupCount() {
        return thumbupCount;
    }

    public void setThumbupCount(Integer thumbupCount) {
        this.thumbupCount = thumbupCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public String getScalePicaddress() {
        return scalePicaddress;
    }

    public void setScalePicaddress(String scalePicaddress) {
        this.scalePicaddress = scalePicaddress;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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
        return "PicturesCategory{" +
        "id=" + id +
        ", categoryId=" + categoryId +
        ", labelIds=" + labelIds +
        ", name=" + name +
        ", picturesNum=" + picturesNum +
        ", views=" + views +
        ", modelId=" + modelId +
        ", thumbupCount=" + thumbupCount +
        ", shareCount=" + shareCount +
        ", collectionCount=" + collectionCount +
        ", picAddress=" + picAddress +
        ", scalePicaddress=" + scalePicaddress +
        ", isDel=" + isDel +
        ", cover=" + cover +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
