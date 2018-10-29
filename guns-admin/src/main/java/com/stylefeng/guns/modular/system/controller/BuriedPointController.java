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
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;

import java.util.Date;

/**
 * 埋点统计控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 17:24:16
 */
@Controller
@RequestMapping("/buriedPoint")
public class BuriedPointController extends BaseController {

    private String PREFIX = "/system/buriedPoint/";

    @Autowired
    private IBuriedPointService buriedPointService;

    /**
     * 跳转到埋点统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "buriedPoint.html";
    }

    /**
     * 跳转到添加埋点统计
     */
    @RequestMapping("/buriedPoint_add")
    public String buriedPointAdd() {
        return PREFIX + "buriedPoint_add.html";
    }

    /**
     * 跳转到修改埋点统计
     */
    @RequestMapping("/buriedPoint_update/{buriedPointId}")
    public String buriedPointUpdate(@PathVariable Integer buriedPointId, Model model) {
        BuriedPoint buriedPoint = buriedPointService.selectById(buriedPointId);
        model.addAttribute("item",buriedPoint);
        LogObjectHolder.me().set(buriedPoint);
        return PREFIX + "buriedPoint_edit.html";
    }

    /**
     * 获取埋点统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return buriedPointService.selectList(null);
    }

    /**
     * 新增埋点统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BuriedPoint buriedPoint) {
        buriedPoint.setGmtCreated(new Date());
        buriedPoint.setGmtModified(new Date());
        buriedPointService.insert(buriedPoint);
        return SUCCESS_TIP;
    }

    /**
     * 删除埋点统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer buriedPointId) {
        buriedPointService.deleteById(buriedPointId);
        return SUCCESS_TIP;
    }

    /**
     * 修改埋点统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BuriedPoint buriedPoint) {
        buriedPoint.setGmtModified(new Date());
        buriedPointService.updateById(buriedPoint);
        return SUCCESS_TIP;
    }

    /**
     * 埋点统计详情
     */
    @RequestMapping(value = "/detail/{buriedPointId}")
    @ResponseBody
    public Object detail(@PathVariable("buriedPointId") Integer buriedPointId) {
        return buriedPointService.selectById(buriedPointId);
    }
}
