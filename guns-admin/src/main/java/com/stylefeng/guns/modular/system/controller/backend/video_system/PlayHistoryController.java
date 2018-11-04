package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Favorite;
import com.stylefeng.guns.modular.system.warpper.AppInfoWarpper;
import com.stylefeng.guns.modular.system.warpper.TypeWarpper;
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
import java.util.List;

/**
 * 用户播放历史控制器
 *
 * @author fengshuonan
 * @Date 2018-10-24 11:04:24
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
    public Object list(String username,Integer appId,String videoName) {
        //注意改成server
        if (ToolUtil.isEmpty(username)&&ToolUtil.isEmpty(videoName)&&appId==null){
            Page<PlayHistory> page =new PageFactory<PlayHistory>().defaultPage();

            page = playHistoryService.selectPage(page);

            List<PlayHistory> PlayHistoryList = page.getRecords();
            page.setRecords((List<PlayHistory>)super.warpObject(new AppInfoWarpper(BeanKit.listToMapList(PlayHistoryList))));

            PageInfoBT<PlayHistory> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<PlayHistory> page = new PageFactory<PlayHistory>().defaultPage();
            EntityWrapper<PlayHistory> entityWrapper = new EntityWrapper<>();
            if (appId!=null&&appId!=0){
                entityWrapper.eq("appId",appId);
            }
            if (ToolUtil.isNotEmpty(username)){
                entityWrapper.like("member_username","%"+username+"%");
            }
            if (ToolUtil.isNotEmpty(videoName)){
                entityWrapper.like("mobile","%"+videoName+"%");
            }

            page = playHistoryService.selectPage(page,entityWrapper);
            List<PlayHistory> PlayHistorys = page.getRecords();
            page.setRecords((List<PlayHistory>)super.warpObject(new AppInfoWarpper(BeanKit.listToMapList(PlayHistorys))));
            PageInfoBT<PlayHistory> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }
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
