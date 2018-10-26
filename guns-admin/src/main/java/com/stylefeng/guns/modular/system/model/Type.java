package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-10-26
 */
@TableName("sea_type")
public class Type extends Model<Type> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;
    private Integer upid;
    private String tname;
    private String tenname;
    private Integer torder;
    private String templist;
    @TableField("templist_1")
    private String templist1;
    @TableField("templist_2")
    private String templist2;
    private String title;
    private String keyword;
    private String description;
    private Integer ishidden;
    private String unionid;
    private Integer tptype;


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUpid() {
        return upid;
    }

    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTenname() {
        return tenname;
    }

    public void setTenname(String tenname) {
        this.tenname = tenname;
    }

    public Integer getTorder() {
        return torder;
    }

    public void setTorder(Integer torder) {
        this.torder = torder;
    }

    public String getTemplist() {
        return templist;
    }

    public void setTemplist(String templist) {
        this.templist = templist;
    }

    public String getTemplist1() {
        return templist1;
    }

    public void setTemplist1(String templist1) {
        this.templist1 = templist1;
    }

    public String getTemplist2() {
        return templist2;
    }

    public void setTemplist2(String templist2) {
        this.templist2 = templist2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIshidden() {
        return ishidden;
    }

    public void setIshidden(Integer ishidden) {
        this.ishidden = ishidden;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getTptype() {
        return tptype;
    }

    public void setTptype(Integer tptype) {
        this.tptype = tptype;
    }

    @Override
    protected Serializable pkVal() {
        return this.tid;
    }

    @Override
    public String toString() {
        return "Type{" +
        "tid=" + tid +
        ", upid=" + upid +
        ", tname=" + tname +
        ", tenname=" + tenname +
        ", torder=" + torder +
        ", templist=" + templist +
        ", templist1=" + templist1 +
        ", templist2=" + templist2 +
        ", title=" + title +
        ", keyword=" + keyword +
        ", description=" + description +
        ", ishidden=" + ishidden +
        ", unionid=" + unionid +
        ", tptype=" + tptype +
        "}";
    }
}
