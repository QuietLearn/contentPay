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
 * 收藏夹表 分别查询三个表(先分别查出每个表资源的ids，在用in查询)
 * </p>
 *
 * @author hyj
 * @since 2018-10-23
 */
@TableName("cps_favorite")
public class Favorite extends Model<Favorite> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Favorite{" +
        "id=" + id +
        ", videoId=" + videoId +
        ", videoName=" + videoName +
        ", videoNote=" + videoNote +
        ", videoPic=" + videoPic +
        ", memberId=" + memberId +
        ", memberUsername=" + memberUsername +
        ", isDel=" + isDel +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
