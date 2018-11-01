package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.MemberLoginLog;
import com.stylefeng.guns.modular.system.service.IMemberLoginLogService;

import java.util.Date;

/**
 * 会员登录日志控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 14:36:31
 */
@Controller
@RequestMapping("/memberLoginLog")
public class MemberLoginLogController extends BaseController {

    private String PREFIX = "/system/memberLoginLog/";

    @Autowired
    private IMemberLoginLogService memberLoginLogService;

    /**
     * 跳转到会员登录日志首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberLoginLog.html";
    }

    /**
     * 跳转到添加会员登录日志
     */
    @RequestMapping("/memberLoginLog_add")
    public String memberLoginLogAdd() {
        return PREFIX + "memberLoginLog_add.html";
    }

    /**
     * 跳转到修改会员登录日志
     */
    @RequestMapping("/memberLoginLog_update/{memberLoginLogId}")
    public String memberLoginLogUpdate(@PathVariable Integer memberLoginLogId, Model model) {
        MemberLoginLog memberLoginLog = memberLoginLogService.selectById(memberLoginLogId);
        model.addAttribute("item",memberLoginLog);
        LogObjectHolder.me().set(memberLoginLog);
        return PREFIX + "memberLoginLog_edit.html";
    }

    /**
     * 获取会员登录日志列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return memberLoginLogService.selectList(null);
    }

    /**
     * 新增会员登录日志
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberLoginLog memberLoginLog) {
        memberLoginLog.setCreatetime(new Date());
        memberLoginLogService.insert(memberLoginLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员登录日志
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberLoginLogId) {
        memberLoginLogService.deleteById(memberLoginLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员登录日志
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberLoginLog memberLoginLog) {
        memberLoginLogService.updateById(memberLoginLog);
        return SUCCESS_TIP;
    }

    /**
     * 会员登录日志详情
     */
    @RequestMapping(value = "/detail/{memberLoginLogId}")
    @ResponseBody
    public Object detail(@PathVariable("memberLoginLogId") Integer memberLoginLogId) {
        return memberLoginLogService.selectById(memberLoginLogId);
    }
}
