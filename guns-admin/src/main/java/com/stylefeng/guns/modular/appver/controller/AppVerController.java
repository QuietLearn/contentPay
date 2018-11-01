package com.stylefeng.guns.modular.appver.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.AppVer;
import com.stylefeng.guns.modular.appver.service.IAppVerService;

import java.util.Date;

/**
 * 应用版本控制器
 *
 * @author fengshuonan
 * @Date 2018-11-01 10:08:11
 */
@Controller
@RequestMapping("/appVer")
public class AppVerController extends BaseController {

    private String PREFIX = "/appver/appVer/";

    @Autowired
    private IAppVerService appVerService;

    /**
     * 跳转到应用版本首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "appVer.html";
    }

    /**
     * 跳转到添加应用版本
     */
    @RequestMapping("/appVer_add")
    public String appVerAdd() {
        return PREFIX + "appVer_add.html";
    }

    /**
     * 跳转到修改应用版本
     */
    @RequestMapping("/appVer_update/{appVerId}")
    public String appVerUpdate(@PathVariable Integer appVerId, Model model) {
        AppVer appVer = appVerService.selectById(appVerId);
        model.addAttribute("item",appVer);
        LogObjectHolder.me().set(appVer);
        return PREFIX + "appVer_edit.html";
    }

    /**
     * 获取应用版本列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return appVerService.selectList(null);
    }

    /**
     * 新增应用版本
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AppVer appVer) {
        appVer.setGmtCreated(new Date());
        appVer.setGmtModified(new Date());
        appVerService.insert(appVer);
        return SUCCESS_TIP;
    }

    /**
     * 删除应用版本
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer appVerId) {
        appVerService.deleteById(appVerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改应用版本
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AppVer appVer) {
        appVer.setGmtModified(new Date());
        appVerService.updateById(appVer);
        return SUCCESS_TIP;
    }

    /**
     * 应用版本详情
     */
    @RequestMapping(value = "/detail/{appVerId}")
    @ResponseBody
    public Object detail(@PathVariable("appVerId") Integer appVerId) {
        return appVerService.selectById(appVerId);
    }
}
