package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Note;
import com.stylefeng.guns.modular.system.warpper.AppInfoWarpper;
import com.stylefeng.guns.modular.system.warpper.NotificationTypeWarpper;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Notification;
import com.stylefeng.guns.modular.system.service.INotificationService;

import java.util.Date;
import java.util.List;

/**
 * 消息推送控制器
 *
 * @author fengshuonan
 * @Date 2018-10-26 10:08:21
 */
@Controller
@RequestMapping("/notification")
public class NotificationController extends BaseController {

    private String PREFIX = "/system/notification/";

    @Autowired
    private INotificationService notificationService;

    /**
     * 跳转到消息推送首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "notification.html";
    }

    /**
     * 跳转到添加消息推送
     */
    @RequestMapping("/notification_add")
    public String notificationAdd() {
        return PREFIX + "notification_add.html";
    }

    /**
     * 跳转到修改消息推送
     */
    @RequestMapping("/notification_update/{notificationId}")
    public String notificationUpdate(@PathVariable Integer notificationId, Model model) {
        Notification notification = notificationService.selectById(notificationId);
        model.addAttribute("item",notification);
        LogObjectHolder.me().set(notification);
        return PREFIX + "notification_edit.html";
    }

    /**
     * 获取消息推送列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer notiType,String mobile) {
        //注意改成server
        if (ToolUtil.isEmpty(mobile)&&notiType==null){
            Page<Notification> page =new PageFactory<Notification>().defaultPage();

            page = notificationService.selectPage(page);

            List<Notification> NotificationList = page.getRecords();
            page.setRecords((List<Notification>)super.warpObject(new NotificationTypeWarpper(BeanKit.listToMapList(NotificationList))));

            PageInfoBT<Notification> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<Notification> page = new PageFactory<Notification>().defaultPage();
            EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
            if (notiType!=null&&notiType!=0){
                entityWrapper.eq("type",notiType);
            }
            if (ToolUtil.isNotEmpty(mobile)){
                entityWrapper.like("mobile","%"+mobile+"%");
            }

            page = notificationService.selectPage(page,entityWrapper);
            List<Notification> Notifications = page.getRecords();
            page.setRecords((List<Notification>)super.warpObject(new NotificationTypeWarpper(BeanKit.listToMapList(Notifications))));
            PageInfoBT<Notification> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }
    }

    /**
     * 新增消息推送
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Notification notification) {
        notification.setGmtCreated(new Date());
        notification.setGmtModified(new Date());
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        notification.setCreater(shiroUser.getAccount());
        notificationService.insert(notification);
        return SUCCESS_TIP;
    }

    /**
     * 删除消息推送
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer notificationId) {
        notificationService.deleteById(notificationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改消息推送
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Notification notification) {
        notification.setGmtModified(new Date());
        notificationService.updateById(notification);
        return SUCCESS_TIP;
    }

    /**
     * 消息推送详情
     */
    @RequestMapping(value = "/detail/{notificationId}")
    @ResponseBody
    public Object detail(@PathVariable("notificationId") Integer notificationId) {
        return notificationService.selectById(notificationId);
    }
}
