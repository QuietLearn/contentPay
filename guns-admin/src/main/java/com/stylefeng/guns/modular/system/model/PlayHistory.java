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
 * @since 2018-10-23
 */
@TableName("cps_play_history")
public class PlayHistory extends Model<PlayHistory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @TableField("member_id")
    private Integer memberId;
    @TableField("member_username")
    private String memberUsername;
    /**
     * 播放视频id
     */
    private Integer videoId;
    @TableField("video_name")
    private String videoName;
    @TableField("video_note")
    private String videoNote;
    @TableField("video_pic")
    private String videoPic;
    @TableField("video_actor")
    private String videoActor;
    @TableField("is_del")
    private Integer isDel;
    /**
     * 播放时间
     */
    @TableField("play_time")
    private Date playTime;
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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoNote() {
        return videoNote;
    }

    public void setVideoNote(String videoNote) {
        this.videoNote = videoNote;
    }

    public String getVideoPic() {
        return videoPic;
    }

    public void setVideoPic(String videoPic) {
        this.videoPic = videoPic;
    }

    public String getVideoActor() {
        return videoActor;
    }

    public void setVideoActor(String videoActor) {
        this.videoActor = videoActor;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
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
        return "PlayHistory{" +
        "id=" + id +
        ", memberId=" + memberId +
        ", memberUsername=" + memberUsername +
        ", videoId=" + videoId +
        ", videoName=" + videoName +
        ", videoNote=" + videoNote +
        ", videoPic=" + videoPic +
        ", videoActor=" + videoActor +
        ", isDel=" + isDel +
        ", playTime=" + playTime +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
