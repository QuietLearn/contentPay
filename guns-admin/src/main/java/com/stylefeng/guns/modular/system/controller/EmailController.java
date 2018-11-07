package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Email;
import com.stylefeng.guns.modular.system.service.IEmailService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 邮箱激活控制器
 *
 * @author fengshuonan
 * @Date 2018-11-07 16:03:11
 */
@Controller
@RequestMapping("/email")
public class EmailController extends BaseController {

    private String PREFIX = "/system/email/";

    @Autowired
    private IEmailService emailService;

    /**
     * 跳转到邮箱激活首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "email.html";
    }

    /**
     * 跳转到添加邮箱激活
     */
    @RequestMapping("/email_add")
    public String emailAdd() {
        return PREFIX + "email_add.html";
    }

    /**
     * 跳转到修改邮箱激活
     */
    @RequestMapping("/email_update/{emailId}")
    public String emailUpdate(@PathVariable Integer emailId, Model model) {
        Email email = emailService.selectById(emailId);
        model.addAttribute("item",email);
        LogObjectHolder.me().set(email);
        return PREFIX + "email_edit.html";
    }

    /**
     * 获取邮箱激活列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return emailService.selectList(null);
    }

    /**
     * 新增邮箱激活
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Email email) {
        email.setGmtCreated(new Date());
        email.setGmtModified(new Date());
        emailService.insert(email);
        return SUCCESS_TIP;
    }

    /**
     * 删除邮箱激活
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer emailId) {
        emailService.deleteById(emailId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除邮箱激活
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteEmailList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String> emailIdList = Arrays.asList(ss);
        boolean deleteResult = emailService.deleteBatchIds(emailIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("邮箱激活批量删除失败，请稍后再试");
    }
    /**
     * 修改邮箱激活
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Email email) {
        email.setGmtModified(new Date());
        emailService.updateById(email);
        return SUCCESS_TIP;
    }

    /**
     * 邮箱激活详情
     */
    @RequestMapping(value = "/detail/{emailId}")
    @ResponseBody
    public Object detail(@PathVariable("emailId") Integer emailId) {
        return emailService.selectById(emailId);
    }
}
