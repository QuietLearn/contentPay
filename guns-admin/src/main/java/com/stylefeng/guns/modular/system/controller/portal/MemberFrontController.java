package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ServerType;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.modular.system.dao.MemberTypeMapper;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    public Result<String> register(Member member) {
        return memberService.register(member);
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

    @RequestMapping(value = "/forget_pass_get_question",method = RequestMethod.POST)
    public Result<String> forgetPassGetQuestion(String username){
        return memberService.forgetPassGetQuestion(username);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(String username ,String password){
        return memberService.login(username,password);
    }

    @RequestMapping(value = "/checkVip")
    public Result checkVip(String uuid,int vid){
        return memberService.checkVip(uuid,vid);
    }
}
