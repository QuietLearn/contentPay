package com.stylefeng.guns.rest.modular.userResouceLib.model;

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
 * @since 2018-11-27
 */
@TableName("usl_video_repository")
public class UslVideoRepository extends Model<UslVideoRepository> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 所属视频分类
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 原封面图片地址id
     */
    @TableField("picture_id")
    private Integer pictureId;
    /**
     * 封面图片
     */
    @TableField("cover_image")
    private String coverImage;
    /**
     * 视频标签
     */
    private String labelIds;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String introduction;
    /**
     * 播放次数
     */
    @TableField("plays_number")
    private Integer playsNumber;
    /**
     * 视频地址
     */
    @TableField("video_address")
    private String videoAddress;
    /**
     * 国家id
     */
    @TableField("country_id")
    private Integer countryId;
    private String times;
    @TableField("is_del")
    private Integer isDel;
    /**
     * 排序字段
     */
    private Integer sort;
    /**
     * 浏览次数
     */
    private Integer browse;
    /**
     * 所属用戶
     */
    @TableField("user_Id")
    private Integer userId;
    /**
     * 收藏次数
     */
    @TableField("collection_count")
    private Integer collectionCount;
    @TableField("gmt_created")
    private Date gmtCreated;
    @TableField("gmt_modified")
    private Date gmtModified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPlaysNumber() {
        return playsNumber;
    }

    public void setPlaysNumber(Integer playsNumber) {
        this.playsNumber = playsNumber;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
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
        return "UslVideoRepository{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", pictureId=" + pictureId +
                ", coverImage=" + coverImage +
                ", labelIds=" + labelIds +
                ", title=" + title +
                ", introduction=" + introduction +
                ", playsNumber=" + playsNumber +
                ", videoAddress=" + videoAddress +
                ", countryId=" + countryId +
                ", times=" + times +
                ", isDel=" + isDel +
                ", sort=" + sort +
                ", browse=" + browse +
                ", userId=" + userId +
                ", collectionCount=" + collectionCount +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
