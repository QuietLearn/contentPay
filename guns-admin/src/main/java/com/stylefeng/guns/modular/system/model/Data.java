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
 * @since 2018-10-18
 */
@TableName("sea_data")
public class Data extends Model<Data> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "v_id", type = IdType.AUTO)
    private Integer vId;
    private Integer tid;
    @TableField("v_name")
    private String vName;
    @TableField("v_state")
    private Integer vState;
    @TableField("v_pic")
    private String vPic;
    @TableField("v_spic")
    private String vSpic;
    @TableField("v_gpic")
    private String vGpic;
    @TableField("v_hit")
    private Integer vHit;
    @TableField("v_money")
    private Integer vMoney;
    @TableField("v_rank")
    private Integer vRank;
    @TableField("v_digg")
    private Integer vDigg;
    @TableField("v_tread")
    private Integer vTread;
    @TableField("v_commend")
    private Integer vCommend;
    @TableField("v_wrong")
    private Integer vWrong;
    @TableField("v_ismake")
    private Integer vIsmake;
    @TableField("v_actor")
    private String vActor;
    @TableField("v_color")
    private String vColor;
    @TableField("v_publishyear")
    private Integer vPublishyear;
    @TableField("v_publisharea")
    private String vPublisharea;
    @TableField("v_addtime")
    private Integer vAddtime;
    @TableField("v_topic")
    private Integer vTopic;
    @TableField("v_note")
    private String vNote;
    @TableField("v_tags")
    private String vTags;
    @TableField("v_letter")
    private String vLetter;
    @TableField("v_isunion")
    private Integer vIsunion;
    @TableField("v_recycled")
    private Integer vRecycled;
    @TableField("v_director")
    private String vDirector;
    @TableField("v_enname")
    private String vEnname;
    @TableField("v_lang")
    private String vLang;
    @TableField("v_score")
    private Integer vScore;
    @TableField("v_scorenum")
    private Integer vScorenum;
    @TableField("v_extratype")
    private String vExtratype;
    @TableField("v_jq")
    private String vJq;
    @TableField("v_nickname")
    private String vNickname;
    @TableField("v_reweek")
    private String vReweek;
    @TableField("v_douban")
    private Float vDouban;
    @TableField("v_mtime")
    private Float vMtime;
    @TableField("v_imdb")
    private Float vImdb;
    @TableField("v_tvs")
    private String vTvs;
    @TableField("v_company")
    private String vCompany;
    @TableField("v_dayhit")
    private Integer vDayhit;
    @TableField("v_weekhit")
    private Integer vWeekhit;
    @TableField("v_monthhit")
    private Integer vMonthhit;
    @TableField("v_daytime")
    private Integer vDaytime;
    @TableField("v_weektime")
    private Integer vWeektime;
    @TableField("v_monthtime")
    private Integer vMonthtime;
    /**
     * 是否需要vip 0是 1否
     */
    @TableField("v_req_vip")
    private Integer vReqVip;
    @TableField("v_len")
    private String vLen;
    @TableField("v_total")
    private String vTotal;
    @TableField("v_ver")
    private String vVer;
    @TableField("v_psd")
    private String vPsd;
    @TableField("v_longtxt")
    private String vLongtxt;


    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public Integer getvState() {
        return vState;
    }

    public void setvState(Integer vState) {
        this.vState = vState;
    }

    public String getvPic() {
        return vPic;
    }

    public void setvPic(String vPic) {
        this.vPic = vPic;
    }

    public String getvSpic() {
        return vSpic;
    }

    public void setvSpic(String vSpic) {
        this.vSpic = vSpic;
    }

    public String getvGpic() {
        return vGpic;
    }

    public void setvGpic(String vGpic) {
        this.vGpic = vGpic;
    }

    public Integer getvHit() {
        return vHit;
    }

    public void setvHit(Integer vHit) {
        this.vHit = vHit;
    }

    public Integer getvMoney() {
        return vMoney;
    }

    public void setvMoney(Integer vMoney) {
        this.vMoney = vMoney;
    }

    public Integer getvRank() {
        return vRank;
    }

    public void setvRank(Integer vRank) {
        this.vRank = vRank;
    }

    public Integer getvDigg() {
        return vDigg;
    }

    public void setvDigg(Integer vDigg) {
        this.vDigg = vDigg;
    }

    public Integer getvTread() {
        return vTread;
    }

    public void setvTread(Integer vTread) {
        this.vTread = vTread;
    }

    public Integer getvCommend() {
        return vCommend;
    }

    public void setvCommend(Integer vCommend) {
        this.vCommend = vCommend;
    }

    public Integer getvWrong() {
        return vWrong;
    }

    public void setvWrong(Integer vWrong) {
        this.vWrong = vWrong;
    }

    public Integer getvIsmake() {
        return vIsmake;
    }

    public void setvIsmake(Integer vIsmake) {
        this.vIsmake = vIsmake;
    }

    public String getvActor() {
        return vActor;
    }

    public void setvActor(String vActor) {
        this.vActor = vActor;
    }

    public String getvColor() {
        return vColor;
    }

    public void setvColor(String vColor) {
        this.vColor = vColor;
    }

    public Integer getvPublishyear() {
        return vPublishyear;
    }

    public void setvPublishyear(Integer vPublishyear) {
        this.vPublishyear = vPublishyear;
    }

    public String getvPublisharea() {
        return vPublisharea;
    }

    public void setvPublisharea(String vPublisharea) {
        this.vPublisharea = vPublisharea;
    }

    public Integer getvAddtime() {
        return vAddtime;
    }

    public void setvAddtime(Integer vAddtime) {
        this.vAddtime = vAddtime;
    }

    public Integer getvTopic() {
        return vTopic;
    }

    public void setvTopic(Integer vTopic) {
        this.vTopic = vTopic;
    }

    public String getvNote() {
        return vNote;
    }

    public void setvNote(String vNote) {
        this.vNote = vNote;
    }

    public String getvTags() {
        return vTags;
    }

    public void setvTags(String vTags) {
        this.vTags = vTags;
    }

    public String getvLetter() {
        return vLetter;
    }

    public void setvLetter(String vLetter) {
        this.vLetter = vLetter;
    }

    public Integer getvIsunion() {
        return vIsunion;
    }

    public void setvIsunion(Integer vIsunion) {
        this.vIsunion = vIsunion;
    }

    public Integer getvRecycled() {
        return vRecycled;
    }

    public void setvRecycled(Integer vRecycled) {
        this.vRecycled = vRecycled;
    }

    public String getvDirector() {
        return vDirector;
    }

    public void setvDirector(String vDirector) {
        this.vDirector = vDirector;
    }

    public String getvEnname() {
        return vEnname;
    }

    public void setvEnname(String vEnname) {
        this.vEnname = vEnname;
    }

    public String getvLang() {
        return vLang;
    }

    public void setvLang(String vLang) {
        this.vLang = vLang;
    }

    public Integer getvScore() {
        return vScore;
    }

    public void setvScore(Integer vScore) {
        this.vScore = vScore;
    }

    public Integer getvScorenum() {
        return vScorenum;
    }

    public void setvScorenum(Integer vScorenum) {
        this.vScorenum = vScorenum;
    }

    public String getvExtratype() {
        return vExtratype;
    }

    public void setvExtratype(String vExtratype) {
        this.vExtratype = vExtratype;
    }

    public String getvJq() {
        return vJq;
    }

    public void setvJq(String vJq) {
        this.vJq = vJq;
    }

    public String getvNickname() {
        return vNickname;
    }

    public void setvNickname(String vNickname) {
        this.vNickname = vNickname;
    }

    public String getvReweek() {
        return vReweek;
    }

    public void setvReweek(String vReweek) {
        this.vReweek = vReweek;
    }

    public Float getvDouban() {
        return vDouban;
    }

    public void setvDouban(Float vDouban) {
        this.vDouban = vDouban;
    }

    public Float getvMtime() {
        return vMtime;
    }

    public void setvMtime(Float vMtime) {
        this.vMtime = vMtime;
    }

    public Float getvImdb() {
        return vImdb;
    }

    public void setvImdb(Float vImdb) {
        this.vImdb = vImdb;
    }

    public String getvTvs() {
        return vTvs;
    }

    public void setvTvs(String vTvs) {
        this.vTvs = vTvs;
    }

    public String getvCompany() {
        return vCompany;
    }

    public void setvCompany(String vCompany) {
        this.vCompany = vCompany;
    }

    public Integer getvDayhit() {
        return vDayhit;
    }

    public void setvDayhit(Integer vDayhit) {
        this.vDayhit = vDayhit;
    }

    public Integer getvWeekhit() {
        return vWeekhit;
    }

    public void setvWeekhit(Integer vWeekhit) {
        this.vWeekhit = vWeekhit;
    }

    public Integer getvMonthhit() {
        return vMonthhit;
    }

    public void setvMonthhit(Integer vMonthhit) {
        this.vMonthhit = vMonthhit;
    }

    public Integer getvDaytime() {
        return vDaytime;
    }

    public void setvDaytime(Integer vDaytime) {
        this.vDaytime = vDaytime;
    }

    public Integer getvWeektime() {
        return vWeektime;
    }

    public void setvWeektime(Integer vWeektime) {
        this.vWeektime = vWeektime;
    }

    public Integer getvMonthtime() {
        return vMonthtime;
    }

    public void setvMonthtime(Integer vMonthtime) {
        this.vMonthtime = vMonthtime;
    }

    public Integer getvReqVip() {
        return vReqVip;
    }

    public void setvReqVip(Integer vReqVip) {
        this.vReqVip = vReqVip;
    }

    public String getvLen() {
        return vLen;
    }

    public void setvLen(String vLen) {
        this.vLen = vLen;
    }

    public String getvTotal() {
        return vTotal;
    }

    public void setvTotal(String vTotal) {
        this.vTotal = vTotal;
    }

    public String getvVer() {
        return vVer;
    }

    public void setvVer(String vVer) {
        this.vVer = vVer;
    }

    public String getvPsd() {
        return vPsd;
    }

    public void setvPsd(String vPsd) {
        this.vPsd = vPsd;
    }

    public String getvLongtxt() {
        return vLongtxt;
    }

    public void setvLongtxt(String vLongtxt) {
        this.vLongtxt = vLongtxt;
    }

    @Override
    protected Serializable pkVal() {
        return this.vId;
    }

    @Override
    public String toString() {
        return "Data{" +
        "vId=" + vId +
        ", tid=" + tid +
        ", vName=" + vName +
        ", vState=" + vState +
        ", vPic=" + vPic +
        ", vSpic=" + vSpic +
        ", vGpic=" + vGpic +
        ", vHit=" + vHit +
        ", vMoney=" + vMoney +
        ", vRank=" + vRank +
        ", vDigg=" + vDigg +
        ", vTread=" + vTread +
        ", vCommend=" + vCommend +
        ", vWrong=" + vWrong +
        ", vIsmake=" + vIsmake +
        ", vActor=" + vActor +
        ", vColor=" + vColor +
        ", vPublishyear=" + vPublishyear +
        ", vPublisharea=" + vPublisharea +
        ", vAddtime=" + vAddtime +
        ", vTopic=" + vTopic +
        ", vNote=" + vNote +
        ", vTags=" + vTags +
        ", vLetter=" + vLetter +
        ", vIsunion=" + vIsunion +
        ", vRecycled=" + vRecycled +
        ", vDirector=" + vDirector +
        ", vEnname=" + vEnname +
        ", vLang=" + vLang +
        ", vScore=" + vScore +
        ", vScorenum=" + vScorenum +
        ", vExtratype=" + vExtratype +
        ", vJq=" + vJq +
        ", vNickname=" + vNickname +
        ", vReweek=" + vReweek +
        ", vDouban=" + vDouban +
        ", vMtime=" + vMtime +
        ", vImdb=" + vImdb +
        ", vTvs=" + vTvs +
        ", vCompany=" + vCompany +
        ", vDayhit=" + vDayhit +
        ", vWeekhit=" + vWeekhit +
        ", vMonthhit=" + vMonthhit +
        ", vDaytime=" + vDaytime +
        ", vWeektime=" + vWeektime +
        ", vMonthtime=" + vMonthtime +
        ", vReqVip=" + vReqVip +
        ", vLen=" + vLen +
        ", vTotal=" + vTotal +
        ", vVer=" + vVer +
        ", vPsd=" + vPsd +
        ", vLongtxt=" + vLongtxt +
        "}";
    }
}
