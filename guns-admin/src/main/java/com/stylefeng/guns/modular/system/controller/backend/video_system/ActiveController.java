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
import com.stylefeng.guns.modular.system.model.Active;
import com.stylefeng.guns.modular.system.service.IActiveService;

import java.util.Date;

/**
 * 激活统计控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 18:55:47
 */
@Controller
@RequestMapping("/active")
public class ActiveController extends BaseController {

    private String PREFIX = "/system/active/";

    @Autowired
    private IActiveService activeService;

    /**
     * 跳转到激活统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "active.html";
    }

    /**
     * 跳转到添加激活统计
     */
    @RequestMapping("/active_add")
    public String activeAdd() {
        return PREFIX + "active_add.html";
    }

    /**
     * 跳转到修改激活统计
     */
    @RequestMapping("/active_update/{activeId}")
    public String activeUpdate(@PathVariable Integer activeId, Model model) {
        Active active = activeService.selectById(activeId);
        model.addAttribute("item",active);
        LogObjectHolder.me().set(active);
        return PREFIX + "active_edit.html";
    }

    /**
     * 获取激活统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return activeService.selectList(null);
    }

    /**
     * 新增激活统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Active active) {
        active.setGmtCreated(new Date());
        active.setGmtModified(new Date());
        activeService.insert(active);
        return SUCCESS_TIP;
    }

    /**
     * 删除激活统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer activeId) {
        activeService.deleteById(activeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改激活统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Active active) {
        active.setGmtModified(new Date());
        activeService.updateById(active);
        return SUCCESS_TIP;
    }

    /**
     * 激活统计详情
     */
    @RequestMapping(value = "/detail/{activeId}")
    @ResponseBody
    public Object detail(@PathVariable("activeId") Integer activeId) {
        return activeService.selectById(activeId);
    }
}
