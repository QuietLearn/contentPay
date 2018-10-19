package com.stylefeng.guns.core.util.meassge;

import com.stylefeng.guns.config.MessageConfig;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.util.HttpSessionHolder;
import com.stylefeng.guns.core.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static final Logger logger = LoggerFactory.getLogger(IndustrySMS.class);
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = MessageConfig.ACCOUNT_SID;
	private static String to = "13065708090";
	private static String smsContent = "【尚线网络】您的验证码为{0}，请于{1}分钟内正确输入，如非本人操作，请忽略此短信。";


	/**
	 * 验证码通知短信
	 */
	public static Result execute(String mobile)
	{
		if(StringUtils.isBlank(mobile)){
			logger.info("手机号为空，请重新传值");
			return Result.createByErrorMessage("手机号为空，请重新传值");
		}

		// 设置超时时间-可自行调整
		/*System.setProperty(MessageConfig.defaultConnectTimeout, MessageConfig.Timeout);
		System.setProperty(MessageConfig.defaultReadTimeout, MessageConfig.Timeout);*/

		String tmpSmsContent = null;
		String smsCode =null;
	    try{
	    	//string.format(smsContent,smscode())
			smsCode = smsCode();

			tmpSmsContent = URLEncoder.encode(MessageFormat.format(smsContent,smsCode, AllConst.timeout), "UTF-8");
	    }catch(Exception e){
	      
	    }
	    String url = MessageConfig.BASE_URL + operation;
	    String body = "accountSid=" + accountSid + "&to=" + mobile + "&smsContent=" + tmpSmsContent
	        + HttpUtil.createCommonParam();

	    // 提交请求  注意了这边是短信发送的接口
	   /* String result = HttpUtil.post(url, body);
	    System.out.println("result:" + System.lineSeparator() + result);*/
	    return Result.createBySuccess(smsCode);
	}

	//创建验证码
	public static String smsCode(){
//		String randomNumeric = RandomStringUtils.randomNumeric(6);
		String random=(int)((Math.random()*9+1)*100000)+"";
		System.out.println("验证码："+random);
		return random;
	}

}
