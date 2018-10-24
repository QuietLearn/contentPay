package com.stylefeng.guns.modular.system.vo;


import com.baomidou.mybatisplus.annotations.TableField;

/**
 * Created by hyj on 2018/10/23
 */
public class VideoVo {

    /**
     * 视频id
     */
    private Integer videoId;

    /**
     * 视频类型
     */
    private Integer typeId;
    /**
     * 视频名
     */
    private String videoName;
    /**
     * 说明(BD/高清/更新到6/共8)
     */
    private String videoNote;

    /**
     * 演员
     */
    private String videoActor;
    /**
     * 封面图片
     */
    private String videoPic;



    private String videoDirector;
    /**
     * 发行地区
     */
    private String videoPublisharea;
    /**
     * 发行年份
     */
    private Integer videoPublishyear;


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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
}
