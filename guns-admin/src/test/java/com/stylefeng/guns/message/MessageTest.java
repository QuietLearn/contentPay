package com.stylefeng.guns.message;

import com.aliyuncs.exceptions.ClientException;
import com.stylefeng.guns.core.util.meassge.IndustrySMS;
import com.stylefeng.guns.core.util.meassge.SendSMSUtilLZ;


/**
 * Created by hyj on 2018/10/19
 */
public class MessageTest {
    /**
     * @param args
     */
    public static void main(String[] args) throws ClientException, InterruptedException {
        SendSMSUtilLZ sendSMSUtilLZ = new SendSMSUtilLZ("13065708090");
        sendSMSUtilLZ.main("1002");
        // 验证码通知短信接口
//        IndustrySMS.execute("13065708090");

    }


}
