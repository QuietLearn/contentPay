package com.stylefeng.guns.core.util.meassge;

import java.text.SimpleDateFormat;
import java.util.*;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.service.impl.MemberServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class SendSMSUtilLZ {
    private static final Logger logger = LoggerFactory.getLogger(SendSMSUtilLZ.class);
//    @Resource
//    protected SmsSender               smsSender;

    //产品名称:云通信短信API产品,开发者无需替换
    static final String               product         = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String               domain          = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static  String               accessKeyId     ="";//= "LTAIlB7K8erciXPg";
    static  String               accessKeySecret ="";//= "USzLesKykp7JvzGnBHJPSCjGVdjA3J";

    //签名
        static  String               SignName        ="";//= "坚果娱乐";
    //模板
    static String                     TemplateCode    = "";
    private static String             signin       ;// 注册 = "SMS_144455793";
    private static String             findPass      ;//密码找回 = "SMS_144455795";
    private static String             replacephone     ;//解绑手机= "SMS_144455799";
    private static String              unbundlingphone     ;//绑定手机= "SMS_144450770";
    private static String              guardNotice       ;//守护到期提醒= "SMS_144455809";
    private static String             cell;
    private static String             code;
    public static Map<String, String> map             = new HashMap<String, String>();
    public  Map<String,Object> params = new HashMap<>();

    public SendSMSUtilLZ(String cell,String code,String SignName,
                         String accessKeyId,String accessKeySecret,String signin,String findPass,
                         String replacephone,String unbundlingphone,String guardNotice) {
        this.cell = cell;
        this.code = code;
        this.signin = signin;
        this.findPass = findPass;
        this.replacephone=replacephone;
        this.unbundlingphone = unbundlingphone;
        this.guardNotice=guardNotice;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.SignName = SignName;
    }

    public SendSMSUtilLZ(String cell){
        this.cell = cell;
        this.signin = AllConst.SMS_Message.signin;
        this.findPass =AllConst.SMS_Message. findPass;
        this.replacephone=AllConst.SMS_Message.replacephone;
        this.unbundlingphone = AllConst.SMS_Message.unbundlingphone;
        this.guardNotice=AllConst.SMS_Message.guardNotice;
        this.accessKeyId =AllConst.SMS_Message.accessKeyId;
        this.accessKeySecret = AllConst.SMS_Message.accessKeySecret;
        this.SignName = AllConst.SMS_Message.SignName;
    }

    public  SendSmsResponse sendSms() throws ClientException {



        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,
                accessKeySecret);
       try{
           DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e1) {
           e1.printStackTrace();
        }
        //初始化ascClient,暂时不支持多region（请勿修改）
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
//        request.setMethod(MethodType.POST);
        //必填:待发送手机号
        request.setPhoneNumbers(cell);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SignName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(TemplateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        this.code = vcode();
        map.put(cell, code);
        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"" + code + "\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendSmsResponse;
    }

    public  QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "100000");
        System.setProperty("sun.net.client.defaultReadTimeout", "100000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(cell);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }


    /**
     * 生成6位随机数验证码
     * @return
     */
    public static String vcode(){
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }

    public Result main(String type) throws ClientException, InterruptedException {
    	logger.info("type:"+type);
        if (type.equals("1000")) {
            TemplateCode = signin;//注册
        } else if (type.equals("1001")) {
            TemplateCode = findPass;//找回密码
        }else if(type.equals("1002")){
        	TemplateCode = replacephone;//换绑手机
        }else if(type.equals("1003")){
        	TemplateCode = unbundlingphone;//解绑手机
        }else if(type.equals("1004")){
        	TemplateCode = guardNotice;//守护提醒
        }
        //发短信
        SendSmsResponse response = sendSms();
        logger.info("------------短信接口返回的数据--------------");
        logger.info("Code=" + response.getCode());
        logger.info("Message=" + response.getMessage());
        logger.info("RequestId=" + response.getRequestId());
        logger.info("BizId=" + response.getBizId());

        Thread.sleep(3000L);

        //查明细
        if (response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response
                .getBizId());
            logger.info("-----------短信明细查询接口返回数据--------------");
            logger.info("Code=" + querySendDetailsResponse.getCode());
            logger.info("Message=" + querySendDetailsResponse.getMessage());
            map.put(cell + "Message", querySendDetailsResponse.getMessage());
            int i = 0;
            for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse
                .getSmsSendDetailDTOs()) {
                logger.info("SmsSendDetailDTO[" + i + "]:");
                logger.info("Content=" + smsSendDetailDTO.getContent());
                logger.info("ErrCode=" + smsSendDetailDTO.getErrCode());
                logger.info("OutId=" + smsSendDetailDTO.getOutId());
                logger.info("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                logger.info("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                logger.info("SendDate=" + smsSendDetailDTO.getSendDate());
                logger.info("SendStatus=" + smsSendDetailDTO.getSendStatus());
                logger.info("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            logger.info("TotalCount=" + querySendDetailsResponse.getTotalCount());
            logger.info("RequestId=" + querySendDetailsResponse.getRequestId());

        } else {
            map.put(cell + "Message", response.getMessage());
        }
        return Result.createBySuccess(code);
    }

//    public static void main(String[] args) throws ClientException, InterruptedException {
//
//        //发短信
//        SendSmsResponse response = sendSms();
//        logger.info("短信接口返回的数据----------------");
//        logger.info("Code=" + response.getCode());
//        logger.info("Message=" + response.getMessage());
//        logger.info("RequestId=" + response.getRequestId());
//        logger.info("BizId=" + response.getBizId());
//
//        Thread.sleep(3000L);
//
//        //查明细
//        if (response.getCode() != null && response.getCode().equals("OK")) {
//            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response
//                .getBizId());
//            logger.info("短信明细查询接口返回数据----------------");
//            logger.info("Code=" + querySendDetailsResponse.getCode());
//            logger.info("Message=" + querySendDetailsResponse.getMessage());
//            int i = 0;
//            for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse
//                .getSmsSendDetailDTOs()) {
//                logger.info("SmsSendDetailDTO[" + i + "]:");
//                logger.info("Content=" + smsSendDetailDTO.getContent());
//                logger.info("ErrCode=" + smsSendDetailDTO.getErrCode());
//                logger.info("OutId=" + smsSendDetailDTO.getOutId());
//                logger.info("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
//                logger.info("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
//                logger.info("SendDate=" + smsSendDetailDTO.getSendDate());
//                logger.info("SendStatus=" + smsSendDetailDTO.getSendStatus());
//                logger.info("Template=" + smsSendDetailDTO.getTemplateCode());
//            }
//            logger.info("TotalCount=" + querySendDetailsResponse.getTotalCount());
//            logger.info("RequestId=" + querySendDetailsResponse.getRequestId());
//        }
//
//    }
}