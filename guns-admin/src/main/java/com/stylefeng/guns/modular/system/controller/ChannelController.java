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
import com.stylefeng.guns.modular.system.model.Channel;
import com.stylefeng.guns.modular.system.service.IChannelService;

import java.util.Date;

/**
 * 应用下载渠道控制器
 *
 * @author fengshuonan
 * @Date 2018-11-04 19:23:36
 */
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController {

    private String PREFIX = "/system/channel/";

    @Autowired
    private IChannelService channelService;

    /**
     * 跳转到应用下载渠道首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "channel.html";
    }

    /**
     * 跳转到添加应用下载渠道
     */
    @RequestMapping("/channel_add")
    public String channelAdd() {
        return PREFIX + "channel_add.html";
    }

    /**
     * 跳转到修改应用下载渠道
     */
    @RequestMapping("/channel_update/{channelId}")
    public String channelUpdate(@PathVariable Integer channelId, Model model) {
        Channel channel = channelService.selectById(channelId);
        model.addAttribute("item",channel);
        LogObjectHolder.me().set(channel);
        return PREFIX + "channel_edit.html";
    }

    /**
     * 获取应用下载渠道列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return channelService.selectList(null);
    }

    /**
     * 新增应用下载渠道
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Channel channel) {
        channel.setGmtCreated(new Date());
        channel.setGmtModified(new Date());
        channelService.insert(channel);
        return SUCCESS_TIP;
    }

    /**
     * 删除应用下载渠道
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer channelId) {
        channelService.deleteById(channelId);
        return SUCCESS_TIP;
    }

    /**
     * 修改应用下载渠道
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Channel channel) {
        channel.setGmtModified(new Date());
        channelService.updateById(channel);
        return SUCCESS_TIP;
    }

    /**
     * 应用下载渠道详情
     */
    @RequestMapping(value = "/detail/{channelId}")
    @ResponseBody
    public Object detail(@PathVariable("channelId") Integer channelId) {
        return channelService.selectById(channelId);
    }
}
