package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.state.ServerType;
import com.stylefeng.guns.core.support.DateTime;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.modular.system.dao.MemberTypeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.IMemberService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 会员管理控制器
 *
 * @author hyj
 * @Date 2018-10-15 10:18:35
 */
/*@RestController*/
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

    private String PREFIX = "/system/member/";

    @Autowired
    private IMemberService memberService;



    /**
     * 跳转到会员管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "member.html";
    }

    /**
     * 跳转到添加会员管理
     */
    @RequestMapping("/member_add")
    public String memberAdd() {
        return PREFIX + "member_add.html";
    }

    /**
     * 跳转到修改会员管理
     */
    @RequestMapping("/member_update/{memberId}")
    public String memberUpdate(@PathVariable Integer memberId, Model model) {
        Member member = memberService.selectById(memberId);
        model.addAttribute("item",member);
        LogObjectHolder.me().set(member);
        return PREFIX + "member_edit.html";
    }

    /**
     * 获取会员管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Member> list(String condition) {

        return memberService.list(condition);
    }

    /**
     * 新增会员管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Member member) {

        memberService.insert(member);
        return SUCCESS_TIP;
    }


    /**
     * 删除会员管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberId) {
        memberService.deleteById(memberId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Member member) {
        memberService.updateById(member);
        return SUCCESS_TIP;
    }

    /**
     * 会员管理详情
     */
    @RequestMapping(value = "/detail/{memberId}")
    @ResponseBody
    public Object detail(@PathVariable("memberId") Integer memberId) {
        return memberService.selectById(memberId);
    }
}
