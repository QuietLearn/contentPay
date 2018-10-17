package com.stylefeng.guns.core.common.result;

public enum ResultCode {
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    ERROR(1,"ERROR"),
    PRIMARY_SCHOOL(100,"这个女孩还在上小学"),
    MIDDLE_SCHOOL(101,"这个女孩在上初中"),
    ADULT(202,"这个女孩已成年");
    private int code;
    private String desc;

    ResultCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

