package com.stylefeng.guns.config;

/**
 * Created by hyj on 2018/10/19
 */
public class MessageConfig {
    // 设置超时时间-可自行调整
    public static final  String defaultConnectTimeout  = "sun.net.client.defaultConnectTimeout";
    public static final  String defaultReadTimeout = "sun.net.client.defaultReadTimeout";
    public static final  String Timeout = "10000";

    /**
     * url前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822";

    /**
     * 开发者注册后系统自动生成的账号，可在官网登录后查看
     */
    public static final String ACCOUNT_SID = "33a5f1e314e24f349dd79d98aa038859";

    /**
     * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
     */
    public static final String AUTH_TOKEN = "9fc8705fef1847eebe7e9d872a863496";

    /**
     * 响应数据类型, JSON或XML
     */
    public static final String RESP_DATA_TYPE = "json";


}
