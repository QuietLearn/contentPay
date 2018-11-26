package com.stylefeng.guns.modular.repository.controller.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.model.VideoAppSource;
import com.stylefeng.guns.modular.system.warpper.repo.PlaysourceWarpper;
import com.stylefeng.guns.modular.system.warpper.repo.VideoAppSourceWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.Arrays;
import com.stylefeng.guns.core.common.result.Result;
import java.util.List;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.PlaySource;
import com.stylefeng.guns.modular.repository.service.IPlaySourceService;
import java.util.Date;

/**
 * 播放源控制器
 *
 * @author fengshuonan
 * @Date 2018-11-26 10:31:56
 */
@Controller
@RequestMapping("/playSource")
public class PlaySourceController extends BaseController {

    private String PREFIX = "/repository/playSource/";

    @Autowired
    private IPlaySourceService playSourceService;

    /**
     * 跳转到播放源首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "playSource.html";
    }

    /**
     * 跳转到添加播放源
     */
    @RequestMapping("/playSource_add")
    public String playSourceAdd() {
        return PREFIX + "playSource_add.html";
    }

    /**
     * 跳转到修改播放源
     */
    @RequestMapping("/playSource_update/{playSourceId}")
    public String playSourceUpdate(@PathVariable Integer playSourceId, Model model) {
        PlaySource playSource = playSourceService.selectById(playSourceId);
        model.addAttribute("item",playSource);
        LogObjectHolder.me().set(playSource);
        return PREFIX + "playSource_edit.html";
    }

    /**
     * 获取播放源列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer videoSourceId) {
        Page<PlaySource> page = new PageFactory<PlaySource>().defaultPage();
        EntityWrapper<PlaySource> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_del",1);

        if (videoSourceId!=null){
            entityWrapper.eq("video_sourceId", videoSourceId);
        }
        page = playSourceService.selectPage(page,entityWrapper);

        List<PlaySource> playSourceList = page.getRecords();
        page.setRecords((List<PlaySource>) super.warpObject(new PlaysourceWarpper(BeanKit.listToMapList(playSourceList))));

        PageInfoBT<PlaySource> pageInfoBT = this.packForBT(page);

        return pageInfoBT;
    }

    /**
     * 新增播放源
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PlaySource playSource) {
        playSource.setGmtCreated(new Date());
        playSource.setGmtModified(new Date());
        playSourceService.insert(playSource);
        return SUCCESS_TIP;
    }

    /**
     * 删除播放源
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer playSourceId) {
        playSourceService.deleteById(playSourceId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除播放源
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deletePlaySourceList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String>  playSourceIdList = Arrays.asList(ss);
        boolean deleteResult = playSourceService.deleteBatchIds(playSourceIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("播放源批量删除失败，请稍后再试");
    }
    /**
     * 修改播放源
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PlaySource playSource) {
        playSource.setGmtModified(new Date());
        playSourceService.updateById(playSource);
        return SUCCESS_TIP;
    }

    /**
     * 播放源详情
     */
    @RequestMapping(value = "/detail/{playSourceId}")
    @ResponseBody
    public Object detail(@PathVariable("playSourceId") Integer playSourceId) {
        return playSourceService.selectById(playSourceId);
    }
}
