package com.stylefeng.guns.modular.system.controller.portal;

import com.aliyuncs.exceptions.ClientException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.TokenCache;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.service.IMemberService;
import com.stylefeng.guns.modular.system.vo.MemberVo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by hyj on 2018/10/15
 */
@RestController
@RequestMapping("/front/member")
public class MemberFrontController extends BaseController {

    @Autowired
    private IMemberService memberService;

    /**
     * 注册会员 除了前端表单的信息也需要设置默认的信息
     */
    @RequestMapping(value = "/register")
    public Result<MemberVo> register(String mobile, String password, String message) {

        return memberService.register(mobile,password,message);
    }

    /**
     * 检测用户名或者邮箱是否已用
     * @param str  具体内容
     * @param type username还是email
     * @return
     */
    @RequestMapping(value = "/checkValid")
    public Result<String> checkValid(String str,String type){
        return memberService.checkValid(str,type);
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(String username ,String password){
        return memberService.login(username,password);
    }

    @RequestMapping(value = "/login_use_mobile",method = RequestMethod.POST)
    public Result loginByMobile(String mobile,String password){
        return memberService.loginByMobile(mobile,password);
    }

    @RequestMapping(value = "/checkVip")
    public Result checkVip(String uuid,int vid){
        return memberService.checkVip(uuid,vid);
    }

    @RequestMapping(value="/getMessage")
    public Result getMessage(String mobile) throws ClientException, InterruptedException {
        return memberService.getMessage2(mobile);
    }

    @RequestMapping(value="/reset_password",method = RequestMethod.POST)
    public Result resetPassword(String mobile,String message,String password){
        return memberService.resetPassword(mobile,message,password);
    }

    @RequestMapping(value = "get_uuid_validity",method = RequestMethod.GET)
    public Result getUuidValidity(String uuidToken){
        return memberService.getUuidValidity(uuidToken);
    }

    @RequestMapping(value = "update_user_info",method = RequestMethod.POST)
    public Result updateUserInfo(MemberVo alterMember,String message){
        return memberService.updateUserInfo(alterMember,message);
    }

    @RequestMapping(value = "verify_message")
    Result verifyMessageResult(String mobile,String message){
        return memberService.verifyMessageResult(mobile,message);
    }
}
