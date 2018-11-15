package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Note;
import com.stylefeng.guns.modular.system.service.IMailService;
import com.stylefeng.guns.modular.system.warpper.EmailWarpper;
import com.stylefeng.guns.modular.system.warpper.TypeWarpper;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private IMailService mailService;
    /**
     * 跳转到邮箱激活首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "email.html";
    }



    /**
     * 获取邮箱激活列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer appId,String memberId) {
        //注意改成server
        if (ToolUtil.isEmpty(memberId)&&appId==null){
            Page<Email> page =new PageFactory<Email>().defaultPage();

            page = emailService.selectPage(page);

            List<Email> EmailList = page.getRecords();
            page.setRecords((List<Email>)super.warpObject(new EmailWarpper(BeanKit.listToMapList(EmailList))));

            PageInfoBT<Email> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<Email> page = new PageFactory<Email>().defaultPage();
            EntityWrapper<Email> entityWrapper = new EntityWrapper<>();
            if (appId!=null&&appId!=0){
                entityWrapper.eq("appId",appId);
            }
            if (ToolUtil.isNotEmpty(memberId)){
                String[] memberIdArray = StringUtils.split(memberId, ",");
                entityWrapper.in("memberId",memberIdArray);

            }

            page = emailService.selectPage(page,entityWrapper);
            List<Email> Emails = page.getRecords();
            page.setRecords((List<Email>)super.warpObject(new EmailWarpper(BeanKit.listToMapList(Emails))));
            PageInfoBT<Email> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }
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
