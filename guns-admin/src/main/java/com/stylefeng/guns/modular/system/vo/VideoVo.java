package com.stylefeng.guns.modular.system.vo;

import com.baomidou.mybatisplus.annotations.TableField;

import java.util.Date;

/**
 * Created by hyj on 2018/10/23
 */
public class VideoVo {

    /**
     * 视频id
     */
    private Integer videoId;
    /**
     * 视频名
     */
    private String videoName;
    /**
     * 说明(BD/高清/更新到6/共8)
     */
    private String videoNote;
    /**
     * 封面图片
     */
    @TableField("video_pic")
    private String videoPic;

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
}
