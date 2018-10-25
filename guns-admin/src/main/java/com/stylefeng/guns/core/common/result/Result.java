package com.stylefeng.guns.core.common.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result<T> {
    /** 错误提示码 */
    private int code;
    /** 提示信息 */
    private String msg;
    /** 具体内容 */
    private T data;

    private Result(int code){
        this.code = code;
    }

    private Result(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    private Result(int code,T data){
        this.code = code;
        this.data = data;
    }

    private Result(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @JSONField(serialize=false)
    public boolean isSuccess(){
        return this.code==ResultCode.SUCCESS.getCode();
    }

    public static Result createBySuccess(){
        return new Result(ResultCode.SUCCESS.getCode());
    }

    public static Result createBySuccessMessage(String msg){
        return new Result(ResultCode.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> createBySuccess(String msg,T data){
        return new Result(ResultCode.SUCCESS.getCode(),msg,data);
    }
    public static <T> Result<T> createBySuccess(T data){
        return new Result(ResultCode.SUCCESS.getCode(),data);
    }


    public static Result createByError(String msg){
        return new Result(ResultCode.ERROR.getCode());
    }

    public static Result createByErrorMessage(String errorMsg){
        return new Result(ResultCode.ERROR.getCode(),errorMsg);
    }

    public static Result createByErrorCodeMessage(int errorCode,String errorMsg){
        return new Result(errorCode,errorMsg);
    }

    public static <T> Result<T>  createByError(T data){
        return new Result(ResultCode.ERROR.getCode(),data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
