package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
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
    public Object list(String condition) {
        return feedbackService.selectList(null);
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
