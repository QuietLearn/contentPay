package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.warpper.AppInfoWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Feedback;
import com.stylefeng.guns.modular.system.service.IFeedbackService;

import java.util.Date;
import java.util.List;

/**
 * 用户反馈控制器
 *
 * @author fengshuonan
 * @Date 2018-10-28 22:02:07
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

    private String PREFIX = "/system/feedback/";

    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 跳转到用户反馈首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "feedback.html";
    }

    /**
     * 跳转到添加用户反馈
     */
    @RequestMapping("/feedback_add")
    public String feedbackAdd() {
        return PREFIX + "feedback_add.html";
    }

    /**
     * 跳转到修改用户反馈
     */
    @RequestMapping("/feedback_update/{feedbackId}")
    public String feedbackUpdate(@PathVariable Integer feedbackId, Model model) {
        Feedback feedback = feedbackService.selectById(feedbackId);
        model.addAttribute("item",feedback);
        LogObjectHolder.me().set(feedback);
        return PREFIX + "feedback_edit.html";
    }

    /**
     * 获取用户反馈列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String username,Integer appId,String membertypeCode) {
        //注意改成server
        if (ToolUtil.isEmpty(username)&&ToolUtil.isEmpty(membertypeCode)&&appId==null){
            Page<Feedback> page =new PageFactory<Feedback>().defaultPage();

            page = feedbackService.selectPage(page);

            List<Feedback> FeedbackList = page.getRecords();
            page.setRecords((List<Feedback>)super.warpObject(new AppInfoWarpper(BeanKit.listToMapList(FeedbackList))));

            PageInfoBT<Feedback> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<Feedback> page = new PageFactory<Feedback>().defaultPage();
            EntityWrapper<Feedback> entityWrapper = new EntityWrapper<>();
            if (appId!=null&&appId!=0){
                entityWrapper.eq("appId",appId);
            }
            if (ToolUtil.isNotEmpty(username)){
                entityWrapper.like("username","%"+username+"%");
            }
            if (ToolUtil.isNotEmpty(membertypeCode)){
                entityWrapper.eq("feedback_type",membertypeCode);
            }

            page = feedbackService.selectPage(page,entityWrapper);
            List<Feedback> Feedbacks = page.getRecords();
            page.setRecords((List<Feedback>)super.warpObject(new AppInfoWarpper(BeanKit.listToMapList(Feedbacks))));
            PageInfoBT<Feedback> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }
    }

    /**
     * 新增用户反馈
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Feedback feedback) {
        feedback.setGmtCreated(new Date());
        feedback.setGmtModified(new Date());
        feedbackService.insert(feedback);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户反馈
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer feedbackId) {
        feedbackService.deleteById(feedbackId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户反馈
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Feedback feedback) {
        feedback.setGmtModified(new Date());
        feedbackService.updateById(feedback);
        return SUCCESS_TIP;
    }

    /**
     * 用户反馈详情
     */
    @RequestMapping(value = "/detail/{feedbackId}")
    @ResponseBody
    public Object detail(@PathVariable("feedbackId") Integer feedbackId) {
        return feedbackService.selectById(feedbackId);
    }
}
