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
 * @since 2018-11-19
 */
@TableName("repo_video_repository")
public class VideoRepository extends Model<VideoRepository> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
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
     * 视频地址Id
     */
    @TableField("video_addressId")
    private Integer videoAddressid;
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
     * 1:2图片

     */
    private Integer onePastTwo;
    /**
     * 4:5图片
     */
    private Integer fivePastFour;
    /**
     * x：x图片
     */
    private Integer xPastx;
    /**
     * 浏览次数
     */
    private Integer browse;
    /**
     * 所属模特
     */
    private Integer modelId;
    /**
     * 收藏次数
     */
    @TableField("collection_count")
    private Integer collectionCount;
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

    public Integer getVideoAddressid() {
        return videoAddressid;
    }

    public void setVideoAddressid(Integer videoAddressid) {
        this.videoAddressid = videoAddressid;
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

    public Integer getOnePastTwo() {
        return onePastTwo;
    }

    public void setOnePastTwo(Integer onePastTwo) {
        this.onePastTwo = onePastTwo;
    }

    public Integer getFivePastFour() {
        return fivePastFour;
    }

    public void setFivePastFour(Integer fivePastFour) {
        this.fivePastFour = fivePastFour;
    }

    public Integer getxPastx() {
        return xPastx;
    }

    public void setxPastx(Integer xPastx) {
        this.xPastx = xPastx;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
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
        return "VideoRepository{" +
        "id=" + id +
        ", categoryId=" + categoryId +
        ", pictureId=" + pictureId +
        ", coverImage=" + coverImage +
        ", labelIds=" + labelIds +
        ", title=" + title +
        ", introduction=" + introduction +
        ", playsNumber=" + playsNumber +
        ", videoAddressid=" + videoAddressid +
        ", countryId=" + countryId +
        ", times=" + times +
        ", isDel=" + isDel +
        ", sort=" + sort +
        ", onePastTwo=" + onePastTwo +
        ", fivePastFour=" + fivePastFour +
        ", xPastx=" + xPastx +
        ", browse=" + browse +
        ", modelId=" + modelId +
        ", collectionCount=" + collectionCount +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
