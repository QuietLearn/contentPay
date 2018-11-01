package com.stylefeng.guns.core.common.constant.state;

/**
 * Created by hyj on 2018/10/18
 */
public class AllConst {

    public static final int timeout = 10;

    public static final int REPEAT_TIME = 1;

    public static final String MESSAGE_ERROR_MSG = "验证码错误，重新输入";

    public enum OrderStatusEnum {
        CANCELED(0, "已取消"),
        NO_PAY(10, "未支付"),
        PAID(20, "付款成功"),
        SHIPPED(30, "付款失败");

        private int code;
        private String status;

        private OrderStatusEnum(int code, String status) {
            this.code = code;
            this.status = status;
        }

        public int getCode() {
            return code;
        }

        public String getStatus() {
            return status;
        }

        public static OrderStatusEnum getOrderStatusEnum(int code) {
            for (OrderStatusEnum orderStatusEnum : values()) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("找不到该code对应枚举");
        }
    }

    public interface VideoReqVip {
        int NEED_VIP = 0;
        int NO_NEED_VIP = 1;
    }


    public interface SMS_Message {

        String signin = "SMS_144455793";
        //        String signin  = "SMS_148590553";
        //密码找回 = "SMS_144455795";
        String findPass = "SMS_144455795";
        //解绑手机= "SMS_144455799";
        String replacephone = "SMS_144455799";
        //绑定手机= "SMS_144450770";
        String unbundlingphone = "SMS_144450770";
        //守护到期提醒= "SMS_144455809";
        String guardNotice = "SMS_144455809";

        //= "LTAIlB7K8erciXPg";LTAINyZShGhQRGet
        String accessKeyId = "LTAIlB7K8erciXPg";
        //="4RDLyTeo3ka8aKZyaAJPtC3h661o0d"
        String accessKeySecret = "USzLesKykp7JvzGnBHJPSCjGVdjA3J";
        String SignName = "坚果娱乐";
    }


    public enum NotifyTypeEnum {
        /*SYSTEM(1000,"系统消息"),
        ACTIVITY(1001,"热门活动"),
        BUG(1002,"异常bug消息");
        valueof(){

        }*/
    }

    public enum PointMessageEnum {
        ENABLE(100, "开启"),
        ACTIVE(101, "激活"),
        //CTRL
        GOBACK(103, "切换到后台"),
        CUTAPP(104, "切回应用"),
        EXITAPP(105, "应用退出"),
        APP_CRASH(106, "应用崩溃"),
        REGISTER(107, "注册"),
        RESET_PASS(108, "忘记密码"),
        LOGIN(111, "登录"),
        HOME(112, "首页"),
        CHANNEL(114, "频道页"),
        LIST(116, "列表页"),
        DETAIL(118, "详情页"),
        PLAY(120, "播放页");


        private Integer code;
        private String message;


        PointMessageEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String valueOf(Integer value) {
            if (value == null) {
                return null;
            } else {
                for (PointMessageEnum pointMessageEnum : PointMessageEnum.values()) {

                    if (value.equals(pointMessageEnum.getCode())) {
                        return pointMessageEnum.getMessage();
                    }
                }
                return null;
            }
        }


        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }



}
