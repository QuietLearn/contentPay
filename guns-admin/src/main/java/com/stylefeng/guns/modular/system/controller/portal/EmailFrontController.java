package com.stylefeng.guns.modular.system.controller.portal;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.Email;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.IEmailService;
import com.stylefeng.guns.modular.system.service.IMailService;
import com.stylefeng.guns.modular.system.service.IMemberService;
import com.stylefeng.guns.modular.system.service.INotificationService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hyj on 2018/10/23
 */
@Api(tags = {"用户邮箱接口"})
@RestController
@RequestMapping("/front/email")
public class EmailFrontController extends BaseController {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IMailService mailService;

    @Autowired
    private IMemberService memberService;
    @Autowired
    private MemberMapper memberMapper;

    @ApiOperation(value = "邮箱验证",notes = "邮箱验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "\"code\": 0,\n" +
                    "    \"data\": \"\",\n" +
                    "    \"msg\": \"发送邮件成功\""),
            @ApiResponse(code = 1, message = "\"code\": 1,\n" +
                    "    \"data\": \"\",\n" +
                    "    \"msg\": \"发送邮件失败\""),
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "get_verify", method = RequestMethod.POST)
    public Object getVerify(String email,String uuidToken) {

        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        boolean isSendMail = mailService.sendHtmlMail(email, "主题：这是模板邮件", member);
        if (!isSendMail)
            return Result.createByErrorMessage("发送邮件失败");
        return Result.createBySuccess("发送邮件成功");
    }


    @RequestMapping(value = "activation/{memberId}", method = RequestMethod.GET)
    public Object activation(@PathVariable String memberId) throws IOException {

        Wrapper<Email> wrapper = new EntityWrapper<>();
        wrapper.eq("memberId",memberId);
        Email email = emailService.selectOne(wrapper);
        if(email!=null) {
            email.setActive(1);
            email.setGmtModified(new Date());
        }
        boolean updateResult = emailService.updateById(email);
        if (!updateResult){
            return Result.createByErrorMessage("更新邮件激活状态失败");
        }
        return SUCCESS_TIP;
    }


}
