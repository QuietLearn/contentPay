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
@TableName("repo_photos_app_source")
public class PhotosAppSource extends Model<PhotosAppSource> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 应用内容id
     */
    @TableField("app_content_id")
    private Integer appContentId;
    /**
     * 图集资源库id
     */
    @TableField("pictures_category_id")
    private Long picturesCategoryId;
    /**
     * 权限
     */
    private Integer authority;
    private Integer isdel;
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

    public Long getPicturesCategoryId() {
        return picturesCategoryId;
    }

    public void setPicturesCategoryId(Long picturesCategoryId) {
        this.picturesCategoryId = picturesCategoryId;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
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
        return "PhotosAppSource{" +
        "id=" + id +
        ", appContentId=" + appContentId +
        ", picturesCategoryId=" + picturesCategoryId +
        ", authority=" + authority +
        ", isdel=" + isdel +
        ", gmtCreated=" + gmtCreated +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
