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
import com.stylefeng.guns.modular.system.model.FeedbackType;
import com.stylefeng.guns.modular.system.service.IFeedbackTypeService;

import java.util.Date;

/**
 * 反馈类型字典控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 01:04:02
 */
@Controller
@RequestMapping("/feedbackType")
public class FeedbackTypeController extends BaseController {

    private String PREFIX = "/system/feedbackType/";

    @Autowired
    private IFeedbackTypeService feedbackTypeService;

    /**
     * 跳转到反馈类型字典首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "feedbackType.html";
    }

    /**
     * 跳转到添加反馈类型字典
     */
    @RequestMapping("/feedbackType_add")
    public String feedbackTypeAdd() {
        return PREFIX + "feedbackType_add.html";
    }

    /**
     * 跳转到修改反馈类型字典
     */
    @RequestMapping("/feedbackType_update/{feedbackTypeId}")
    public String feedbackTypeUpdate(@PathVariable Integer feedbackTypeId, Model model) {
        FeedbackType feedbackType = feedbackTypeService.selectById(feedbackTypeId);
        model.addAttribute("item",feedbackType);
        LogObjectHolder.me().set(feedbackType);
        return PREFIX + "feedbackType_edit.html";
    }

    /**
     * 获取反馈类型字典列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return feedbackTypeService.selectList(null);
    }

    /**
     * 新增反馈类型字典
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(FeedbackType feedbackType) {
        feedbackType.setGmtCreated(new Date());
        feedbackType.setGmtModified(new Date());
        feedbackTypeService.insert(feedbackType);
        return SUCCESS_TIP;
    }

    /**
     * 删除反馈类型字典
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer feedbackTypeId) {
        feedbackTypeService.deleteById(feedbackTypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改反馈类型字典
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(FeedbackType feedbackType) {
        feedbackType.setGmtModified(new Date());
        feedbackTypeService.updateById(feedbackType);
        return SUCCESS_TIP;
    }

    /**
     * 反馈类型字典详情
     */
    @RequestMapping(value = "/detail/{feedbackTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("feedbackTypeId") Integer feedbackTypeId) {
        return feedbackTypeService.selectById(feedbackTypeId);
    }
}
