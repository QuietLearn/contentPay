package com.stylefeng.guns.core.common.constant.state;

/**
 * Created by hyj on 2018/10/18
 */
public class AllConst {

    public static final int timeout = 2;

    public static final int REPEAT_TIME = 1;

    public enum OrderStatusEnum{
        CANCELED(0,"已取消"),
        NO_PAY(10,"未支付"),
        PAID(20,"付款成功"),
        SHIPPED(30,"付款失败");

        private int code;
        private String status;

        private OrderStatusEnum(int code,String status){
            this.code = code;
            this.status = status;
        }

        public int getCode() {
            return code;
        }

        public String getStatus() {
            return status;
        }

        public static OrderStatusEnum getOrderStatusEnum(int code){
            for(OrderStatusEnum orderStatusEnum:values()){
                if (orderStatusEnum.getCode()==code){
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


    public interface SMS_Message{
        String signin  = "SMS_144455793";
        String findPass  = "SMS_144455795"        ;//密码找回 = "SMS_144455795";
        String replacephone = "SMS_144455799"      ;//解绑手机= "SMS_144455799";
        String unbundlingphone = "SMS_144450770"     ;//绑定手机= "SMS_144450770";
        String guardNotice  = "SMS_144455809" ;//守护到期提醒= "SMS_144455809";

        String accessKeyId     ="LTAINyZShGhQRGet";//= "LTAIlB7K8erciXPg";
        String accessKeySecret ="4RDLyTeo3ka8aKZyaAJPtC3h661o0d";
        String SignName        ="坚果娱乐";
    }
}
