package com.stylefeng.guns.modular.repository.controller.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.model.PlaySource;
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
import com.stylefeng.guns.modular.system.model.VideoSource;
import com.stylefeng.guns.modular.repository.service.IVideoSourceService;
import java.util.Date;

/**
 * 视频来源控制器
 *
 * @author fengshuonan
 * @Date 2018-11-26 10:32:53
 */
@Controller
@RequestMapping("/videoSource")
public class VideoSourceController extends BaseController {

    private String PREFIX = "/repository/videoSource/";

    @Autowired
    private IVideoSourceService videoSourceService;

    /**
     * 跳转到视频来源首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "videoSource.html";
    }

    /**
     * 跳转到添加视频来源
     */
    @RequestMapping("/videoSource_add")
    public String videoSourceAdd() {
        return PREFIX + "videoSource_add.html";
    }

    /**
     * 跳转到修改视频来源
     */
    @RequestMapping("/videoSource_update/{videoSourceId}")
    public String videoSourceUpdate(@PathVariable Integer videoSourceId, Model model) {
        VideoSource videoSource = videoSourceService.selectById(videoSourceId);
        model.addAttribute("item",videoSource);
        LogObjectHolder.me().set(videoSource);
        return PREFIX + "videoSource_edit.html";
    }

    /**
     * 获取视频来源列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {

        return videoSourceService.selectList(null);
    }

    /**
     * 新增视频来源
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VideoSource videoSource) {
        videoSource.setGmtCreated(new Date());
        videoSource.setGmtModified(new Date());
        videoSourceService.insert(videoSource);
        return SUCCESS_TIP;
    }

    /**
     * 删除视频来源
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer videoSourceId) {
        videoSourceService.deleteById(videoSourceId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除视频来源
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteVideoSourceList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String>  videoSourceIdList = Arrays.asList(ss);
        boolean deleteResult = videoSourceService.deleteBatchIds(videoSourceIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("视频来源批量删除失败，请稍后再试");
    }
    /**
     * 修改视频来源
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VideoSource videoSource) {
        videoSource.setGmtModified(new Date());
        videoSourceService.updateById(videoSource);
        return SUCCESS_TIP;
    }

    /**
     * 视频来源详情
     */
    @RequestMapping(value = "/detail/{videoSourceId}")
    @ResponseBody
    public Object detail(@PathVariable("videoSourceId") Integer videoSourceId) {
        return videoSourceService.selectById(videoSourceId);
    }
}
