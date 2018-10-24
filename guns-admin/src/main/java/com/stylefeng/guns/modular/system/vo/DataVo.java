package com.stylefeng.guns.modular.system.vo;

/**
 * Created by hyj on 2018/10/24
 */
public class DataVo {
    private Integer vId;
    private Integer tid;
    private String vName;

    private String vPic;

    private Integer vPublishyear;

    private Integer vAddtime;

    private Integer vMoney;

    private Integer vReqVip;

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

    public String getvPic() {
        return vPic;
    }

    public void setvPic(String vPic) {
        this.vPic = vPic;
    }

    public Integer getvMoney() {
        return vMoney;
    }

    public void setvMoney(Integer vMoney) {
        this.vMoney = vMoney;
    }

    public Integer getvReqVip() {
        return vReqVip;
    }

    public void setvReqVip(Integer vReqVip) {
        this.vReqVip = vReqVip;
    }

    public Integer getvPublishyear() {
        return vPublishyear;
    }

    public void setvPublishyear(Integer vPublishyear) {
        this.vPublishyear = vPublishyear;
    }

    public Integer getvAddtime() {
        return vAddtime;
    }

    public void setvAddtime(Integer vAddtime) {
        this.vAddtime = vAddtime;
    }
}
