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
@TableName("repo_video_app_source")
public class VideoAppSource extends Model<VideoAppSource> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 应用内容id
     */
    @TableField("app_content_id")
    private Integer appContentId;
    /**
     * 视频id
     */
    @TableField("video_id")
    private Long videoId;
    /**
     * 权限
     */
    private Integer authority;
    @TableField("is_del")
    private Integer isDel;
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

    public Integer getAppContentId() {
        return appContentId;
    }

    public void setAppContentId(Integer appContentId) {
        this.appContentId = appContentId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
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
        return "VideoAppSource{" +
        "id=" + id +
        ", appContentId=" + appContentId +
        ", videoId=" + videoId +
        ", authority=" + authority +
        ", isDel=" + isDel +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
