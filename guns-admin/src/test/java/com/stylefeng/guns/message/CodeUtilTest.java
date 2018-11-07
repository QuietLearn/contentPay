package com.stylefeng.guns.message;

import com.aliyuncs.exceptions.ClientException;
import com.stylefeng.guns.core.util.meassge.SendSMSUtilLZ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * Created by hyj on 2018/10/19
 */
public class CodeUtilTest {
    /**
     * @param args
     */
    public static void main(String[] args) throws ClientException, InterruptedException {
       /* String code = CodeUtil.generateCode(user.getUserEmail());
        String codepw = CodeUtil.generateCode(user.getPassword())*/;
    }

}

class MailUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${spring.mail.fromMail.addr}")
    private String from;
    public boolean sendRegisterMail(String email, String code) {
        MimeMessage message = mailSender.createMimeMessage();
        String register_link = "http://localhost:8899/api/email=" + email + "/code=" +code;
//创建邮件正文
        Context context = new Context();
        context.setVariable("register_link", register_link);
        String emailContent = templateEngine.process("UserRegisterTemplate", context);
        try {
//true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("低电查询验证邮件");
            helper.setText(emailContent, true);
            mailSender.send(message);
            logger.info("html邮件发送成功");
            return true;
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
            return false;
        }
    }
}
