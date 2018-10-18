package com.stylefeng.guns.core.common.constant.state;

/**
 * Created by hyj on 2018/10/18
 */
public class AllConst {


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
}
