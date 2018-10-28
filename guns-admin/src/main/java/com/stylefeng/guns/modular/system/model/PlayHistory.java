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
 * 播放历史表
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
@TableName("cps_play_history")
public class PlayHistory extends Model<PlayHistory> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 视频类型
     */
    @TableField("type_id")
    private Integer typeId;
    /**
     * 视频id
     */
    @TableField("video_id")
    private Integer videoId;
    /**
     * 视频名
     */
    @TableField("video_name")
    private String videoName;
    /**
     * 说明(BD/高清/更新到6/共8)
     */
    @TableField("video_note")
    private String videoNote;
    /**
     * 封面图片
     */
    @TableField("video_pic")
    private String videoPic;
    /**
     * 演员
     */
    @TableField("video_actor")
    private String videoActor;
    /**
     * 导演
     */
    @TableField("video_director")
    private String videoDirector;
    /**
     * 发行地区
     */
    @TableField("video_publisharea")
    private String videoPublisharea;
    /**
     * 发行年份
     */
    @TableField("video_publishyear")
    private Integer videoPublishyear;
    /**
     * 用户id
     */
    @TableField("member_id")
    private Integer memberId;
    /**
     * 用户昵称
     */
    @TableField("member_username")
    private String memberUsername;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 逻辑删除
     */
    @TableField("is_del")
    private Integer isDel;
    /**
     * 收藏时间
     */
    @TableField("gmt_created")
    private Date gmtCreated;
    @TableField("gmt_modified")
    private Date gmtModified;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 应用版本
     */
    private String appVer;
    /**
     * 渠道号
     */
    private String channel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public String getVideoDirector() {
        return videoDirector;
    }

    public void setVideoDirector(String videoDirector) {
        this.videoDirector = videoDirector;
    }

    public String getVideoPublisharea() {
        return videoPublisharea;
    }

    public void setVideoPublisharea(String videoPublisharea) {
        this.videoPublisharea = videoPublisharea;
    }

    public Integer getVideoPublishyear() {
        return videoPublishyear;
    }

    public void setVideoPublishyear(Integer videoPublishyear) {
        this.videoPublishyear = videoPublishyear;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PlayHistory{" +
        "id=" + id +
        ", typeId=" + typeId +
        ", videoId=" + videoId +
        ", videoName=" + videoName +
        ", videoNote=" + videoNote +
        ", videoPic=" + videoPic +
        ", videoActor=" + videoActor +
        ", videoDirector=" + videoDirector +
        ", videoPublisharea=" + videoPublisharea +
        ", videoPublishyear=" + videoPublishyear +
        ", memberId=" + memberId +
        ", memberUsername=" + memberUsername +
        ", sort=" + sort +
        ", isDel=" + isDel +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        ", appId=" + appId +
        ", appVer=" + appVer +
        ", channel=" + channel +
        "}";
    }
}
