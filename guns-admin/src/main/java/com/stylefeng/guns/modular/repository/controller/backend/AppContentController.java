package com.stylefeng.guns.modular.repository.controller.backend;

import com.stylefeng.guns.core.base.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.AppContent;
import com.stylefeng.guns.modular.repository.service.IAppContentService;

import java.util.Arrays;
import com.stylefeng.guns.core.common.result.Result;
import java.util.List;

/**
 * 应用内容版本控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 17:43:07
 */
@Controller
@RequestMapping("/appContent")
public class AppContentController extends BaseController {

    private String PREFIX = "/repository/appContent/";

    @Autowired
    private IAppContentService appContentService;

    /**
     * 跳转到应用内容版本首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "appContent.html";
    }

    /**
     * 跳转到添加应用内容版本
     */
    @RequestMapping("/appContent_add")
    public String appContentAdd() {
        return PREFIX + "appContent_add.html";
    }

    /**
     * 跳转到修改应用内容版本
     */
    @RequestMapping("/appContent_update/{appContentId}")
    public String appContentUpdate(@PathVariable Integer appContentId, Model model) {
        AppContent appContent = appContentService.selectById(appContentId);
        model.addAttribute("item",appContent);
        LogObjectHolder.me().set(appContent);
        return PREFIX + "appContent_edit.html";
    }

    /**
     * 获取应用内容版本列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return appContentService.selectList(null);
    }

    /**
     * 新增应用内容版本
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AppContent appContent) {
        appContent.setGmtCreated(new Date());
        appContent.setGmtModified(new Date());
        appContentService.insert(appContent);
        return SUCCESS_TIP;
    }

    /**
     * 删除应用内容版本
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer appContentId) {
        appContentService.deleteById(appContentId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除应用内容版本
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteAppContentList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String> appContentIdList = Arrays.asList(ss);
        boolean deleteResult = appContentService.deleteBatchIds(appContentIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("应用内容版本批量删除失败，请稍后再试");
    }
    /**
     * 修改应用内容版本
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AppContent appContent) {
        appContent.setGmtModified(new Date());
        appContentService.updateById(appContent);
        return SUCCESS_TIP;
    }

    /**
     * 应用内容版本详情
     */
    @RequestMapping(value = "/detail/{appContentId}")
    @ResponseBody
    public Object detail(@PathVariable("appContentId") Integer appContentId) {
        return appContentService.selectById(appContentId);
    }
}
