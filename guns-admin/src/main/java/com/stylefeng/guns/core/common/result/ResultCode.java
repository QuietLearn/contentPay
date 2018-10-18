package com.stylefeng.guns.core.common.result;

public enum ResultCode {
    UNKNOWN_ERROR(-1,"UNKNOWN_ERROR"),
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    ILLEAGAL_ARGUMENT(-2,"ILLEAGAL_ARGUMENT");

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

