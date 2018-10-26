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
import com.stylefeng.guns.modular.system.model.PayPoints;
import com.stylefeng.guns.modular.system.service.IPayPointsService;

import java.util.List;

/**
 * 支付积分控制器
 *
 * @author fengshuonan
 * @Date 2018-10-14 20:08:58
 */
@Controller
@RequestMapping("/payPoints")
public class PayPointsController extends BaseController {

    private String PREFIX = "/system/payPoints/";

    @Autowired
    private IPayPointsService payPointsService;

    /**
     * 跳转到支付积分首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "payPoints.html";
    }

    /**
     * 跳转到添加支付积分
     */
    @RequestMapping("/payPoints_add")
    public String payPointsAdd() {
        return PREFIX + "payPoints_add.html";
    }

    /**
     * 跳转到修改支付积分
     */
    @RequestMapping("/payPoints_update/{payPointsId}")
    public String payPointsUpdate(@PathVariable Integer payPointsId, Model model) {
        PayPoints payPoints = payPointsService.selectById(payPointsId);
        model.addAttribute("item",payPoints);
        LogObjectHolder.me().set(payPoints);
        return PREFIX + "payPoints_edit.html";
    }

    /**
     * 获取支付积分列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<PayPoints> payPoints = payPointsService.selectList(null);
        return payPoints;
    }

    /**
     * 新增支付积分
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PayPoints payPoints) {
        payPointsService.insert(payPoints);
        return SUCCESS_TIP;
    }

    /**
     * 删除支付积分
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer payPointsId) {
        payPointsService.deleteById(payPointsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改支付积分
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PayPoints payPoints) {
        payPointsService.updateById(payPoints);
        return SUCCESS_TIP;
    }

    /**
     * 支付积分详情
     */
    @RequestMapping(value = "/detail/{payPointsId}")
    @ResponseBody
    public Object detail(@PathVariable("payPointsId") Integer payPointsId) {
        return payPointsService.selectById(payPointsId);
    }
}
