package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.vo.DataVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Video;
import com.stylefeng.guns.modular.system.service.IVideoService;

import java.util.Date;
import java.util.List;

/**
 * 视频价格控制器
 *
 * @author fengshuonan
 * @Date 2018-10-24 18:01:26
 */
@Controller
@RequestMapping("/video")
public class VideoController extends BaseController {

    private String PREFIX = "/system/video/";

    @Autowired
    private IVideoService videoService;

    /**
     * 跳转到视频价格首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "video.html";
    }

    /**
     * 跳转到添加视频价格
     */
    @RequestMapping("/video_add")
    public String videoAdd() {
        return PREFIX + "video_add.html";
    }

    /**
     * 跳转到修改视频价格
     */
    @RequestMapping("/video_update/{videoId}")
    public String videoUpdate(@PathVariable Integer videoId, Model model) {
        Video video = videoService.selectById(videoId);
        model.addAttribute("item",video);
        LogObjectHolder.me().set(video);
        return PREFIX + "video_edit.html";
    }

    /**
     * 获取视频价格列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isEmpty(condition)){
            Page<Video> page =new PageFactory<Video>().defaultPage();

            page = videoService.selectPage(page);

            PageInfoBT<Video> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<Video> page =new PageFactory<Video>().defaultPage();
            EntityWrapper<Video> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("title","%"+condition+"%");
            page = videoService.selectPage(page,entityWrapper);
            PageInfoBT<Video> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }
    }

    /**
     * 新增视频价格
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Video video) {
        video.setGmtCreated(new Date());
        video.setGmtModified(new Date());
        videoService.insert(video);
        return SUCCESS_TIP;
    }

    /**
     * 删除视频价格
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer videoId) {
        videoService.deleteById(videoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改视频价格
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Video video) {
        video.setGmtModified(new Date());
        videoService.updateById(video);
        return SUCCESS_TIP;
    }

    /**
     * 视频价格详情
     */
    @RequestMapping(value = "/detail/{videoId}")
    @ResponseBody
    public Object detail(@PathVariable("videoId") Integer videoId) {
        return videoService.selectById(videoId);
    }

    @RequestMapping(value = "sync_video")
    @ResponseBody
    public String syncVideo(){
        return videoService.syncVideo();
    }
}
