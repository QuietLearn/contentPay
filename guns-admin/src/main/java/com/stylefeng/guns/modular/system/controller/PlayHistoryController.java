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
import com.stylefeng.guns.modular.system.model.PlayHistory;
import com.stylefeng.guns.modular.system.service.IPlayHistoryService;

import java.util.Date;

/**
 * 用户播放历史控制器
 *
 * @author fengshuonan
 * @Date 2018-10-23 16:55:53
 */
@Controller
@RequestMapping("/playHistory")
public class PlayHistoryController extends BaseController {

    private String PREFIX = "/system/playHistory/";

    @Autowired
    private IPlayHistoryService playHistoryService;

    /**
     * 跳转到用户播放历史首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "playHistory.html";
    }

    /**
     * 跳转到添加用户播放历史
     */
    @RequestMapping("/playHistory_add")
    public String playHistoryAdd() {
        return PREFIX + "playHistory_add.html";
    }

    /**
     * 跳转到修改用户播放历史
     */
    @RequestMapping("/playHistory_update/{playHistoryId}")
    public String playHistoryUpdate(@PathVariable Integer playHistoryId, Model model) {
        PlayHistory playHistory = playHistoryService.selectById(playHistoryId);
        model.addAttribute("item",playHistory);
        LogObjectHolder.me().set(playHistory);
        return PREFIX + "playHistory_edit.html";
    }

    /**
     * 获取用户播放历史列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return playHistoryService.selectList(null);
    }

    /**
     * 新增用户播放历史
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PlayHistory playHistory) {
        playHistory.setGmtCreated(new Date());
        playHistory.setGmtModified(new Date());
        playHistoryService.insert(playHistory);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户播放历史
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer playHistoryId) {
        playHistoryService.deleteById(playHistoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户播放历史
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PlayHistory playHistory) {
        playHistory.setGmtModified(new Date());
        playHistoryService.updateById(playHistory);
        return SUCCESS_TIP;
    }

    /**
     * 用户播放历史详情
     */
    @RequestMapping(value = "/detail/{playHistoryId}")
    @ResponseBody
    public Object detail(@PathVariable("playHistoryId") Integer playHistoryId) {
        return playHistoryService.selectById(playHistoryId);
    }
}