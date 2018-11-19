package com.stylefeng.guns.modular.repository.controller.backend;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.VideoAppSource;
import com.stylefeng.guns.modular.repository.service.IVideoAppSourceService;
import java.util.Date;
import java.util.List;

/**
 * 视频应用内容控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 17:39:53
 */
@Controller
@RequestMapping("/videoAppSource")
public class VideoAppSourceController extends BaseController {

    private String PREFIX = "/repository/videoAppSource/";

    @Autowired
    private IVideoAppSourceService videoAppSourceService;

    /**
     * 跳转到视频应用内容首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "videoAppSource.html";
    }

    /**
     * 跳转到添加视频应用内容
     */
    @RequestMapping("/videoAppSource_add")
    public String videoAppSourceAdd() {
        return PREFIX + "videoAppSource_add.html";
    }

    /**
     * 跳转到修改视频应用内容
     */
    @RequestMapping("/videoAppSource_update/{videoAppSourceId}")
    public String videoAppSourceUpdate(@PathVariable Integer videoAppSourceId, Model model) {
        VideoAppSource videoAppSource = videoAppSourceService.selectById(videoAppSourceId);
        model.addAttribute("item",videoAppSource);
        LogObjectHolder.me().set(videoAppSource);
        return PREFIX + "videoAppSource_edit.html";
    }

    /**
     * 获取视频应用内容列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return videoAppSourceService.selectList(null);
    }

    /**
     * 新增视频应用内容
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VideoAppSource videoAppSource) {
        videoAppSource.setGmtCreated(new Date());
        videoAppSource.setGmtModified(new Date());
        videoAppSourceService.insert(videoAppSource);
        return SUCCESS_TIP;
    }

    /**
     * 删除视频应用内容
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer videoAppSourceId) {
        videoAppSourceService.deleteById(videoAppSourceId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除视频应用内容
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteVideoAppSourceList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String> videoAppSourceIdList = Arrays.asList(ss);
        boolean deleteResult = videoAppSourceService.deleteBatchIds(videoAppSourceIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("视频应用内容批量删除失败，请稍后再试");
    }
    /**
     * 修改视频应用内容
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VideoAppSource videoAppSource) {
        videoAppSource.setGmtModified(new Date());
        videoAppSourceService.updateById(videoAppSource);
        return SUCCESS_TIP;
    }

    /**
     * 视频应用内容详情
     */
    @RequestMapping(value = "/detail/{videoAppSourceId}")
    @ResponseBody
    public Object detail(@PathVariable("videoAppSourceId") Integer videoAppSourceId) {
        return videoAppSourceService.selectById(videoAppSourceId);
    }
}
