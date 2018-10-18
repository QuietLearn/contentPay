package com.stylefeng.guns.core.common.constant.state;

/**
 * Created by hyj on 2018/10/15
 */
public enum ServerType {
    REGULAR(1000,"普通会员"),
    VIP(1001,"vip会员"),
    ANNO(1002,"匿名用户");


    int code;
    String desc;

    ServerType(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ServerType codeOf(int code){
        for(ServerType serverType:values()){
            if (serverType.getCode()==code){
                return serverType;
            }
        }
        throw new RuntimeException("找不到该code对应支付类型枚举");
    }
}
