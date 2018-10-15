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
import com.stylefeng.guns.modular.system.model.PayChannel;
import com.stylefeng.guns.modular.system.service.IPayChannelService;

import java.util.List;

/**
 * 支付渠道控制器
 *
 * @author fengshuonan
 * @Date 2018-10-14 20:08:42
 */
@Controller
@RequestMapping("/payChannel")
public class PayChannelController extends BaseController {

    private String PREFIX = "/system/payChannel/";

    @Autowired
    private IPayChannelService payChannelService;

    /**
     * 跳转到支付渠道首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "payChannel.html";
    }

    /**
     * 跳转到添加支付渠道
     */
    @RequestMapping("/payChannel_add")
    public String payChannelAdd() {
        return PREFIX + "payChannel_add.html";
    }

    /**
     * 跳转到修改支付渠道
     */
    @RequestMapping("/payChannel_update/{payChannelId}")
    public String payChannelUpdate(@PathVariable Integer payChannelId, Model model) {
        PayChannel payChannel = payChannelService.selectById(payChannelId);
        model.addAttribute("item",payChannel);
        LogObjectHolder.me().set(payChannel);
        return PREFIX + "payChannel_edit.html";
    }

    /**
     * 获取支付渠道列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        List<PayChannel> payChannels = payChannelService.selectList(null);
        return payChannels;
    }

    /**
     * 新增支付渠道
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PayChannel payChannel) {
        payChannelService.insert(payChannel);
        return SUCCESS_TIP;
    }

    /**
     * 删除支付渠道
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer payChannelId) {
        payChannelService.deleteById(payChannelId);
        return SUCCESS_TIP;
    }

    /**
     * 修改支付渠道
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PayChannel payChannel) {
        payChannelService.updateById(payChannel);
        return SUCCESS_TIP;
    }

    /**
     * 支付渠道详情
     */
    @RequestMapping(value = "/detail/{payChannelId}")
    @ResponseBody
    public Object detail(@PathVariable("payChannelId") Integer payChannelId) {
        return payChannelService.selectById(payChannelId);
    }
}
