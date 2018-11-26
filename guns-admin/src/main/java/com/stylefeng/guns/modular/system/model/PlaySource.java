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
 * @since 2018-11-26
 */
@TableName("repo_play_source")
public class PlaySource extends Model<PlaySource> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 视频id
     */
    @TableField("app_videoId")
    private Integer appVideoid;
    /**
     * 视频来源id
     */
    @TableField("video_sourceId")
    private Integer videoSourceid;
    /**
     * 视频地址
     */
    @TableField("video_address")
    private String videoAddress;
    /**
     * 是否选用 0  不选用 1 选中
     */
    @TableField("is_check")
    private Integer isCheck;
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

    public Integer getAppVideoid() {
        return appVideoid;
    }

    public void setAppVideoid(Integer appVideoid) {
        this.appVideoid = appVideoid;
    }

    public Integer getVideoSourceid() {
        return videoSourceid;
    }

    public void setVideoSourceid(Integer videoSourceid) {
        this.videoSourceid = videoSourceid;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
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
        return "PlaySource{" +
        "id=" + id +
        ", appVideoid=" + appVideoid +
        ", videoSourceid=" + videoSourceid +
        ", videoAddress=" + videoAddress +
        ", isCheck=" + isCheck +
        ", isDel=" + isDel +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
