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
import com.stylefeng.guns.modular.system.model.VideoRepository;
import com.stylefeng.guns.modular.repository.service.IVideoRepositoryService;
import java.util.Date;
import java.util.List;

/**
 * 视频资源库控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 15:51:58
 */
@Controller
@RequestMapping("/videoRepository")
public class VideoRepositoryController extends BaseController {

    private String PREFIX = "/repository/videoRepository/";

    @Autowired
    private IVideoRepositoryService videoRepositoryService;

    /**
     * 跳转到视频资源库首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "videoRepository.html";
    }

    /**
     * 跳转到添加视频资源库
     */
    @RequestMapping("/videoRepository_add")
    public String videoRepositoryAdd() {
        return PREFIX + "videoRepository_add.html";
    }

    /**
     * 跳转到修改视频资源库
     */
    @RequestMapping("/videoRepository_update/{videoRepositoryId}")
    public String videoRepositoryUpdate(@PathVariable Integer videoRepositoryId, Model model) {
        VideoRepository videoRepository = videoRepositoryService.selectById(videoRepositoryId);
        model.addAttribute("item",videoRepository);
        LogObjectHolder.me().set(videoRepository);
        return PREFIX + "videoRepository_edit.html";
    }

    /**
     * 获取视频资源库列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return videoRepositoryService.selectList(null);
    }

    /**
     * 新增视频资源库
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VideoRepository videoRepository) {
        videoRepository.setGmtCreated(new Date());
        videoRepository.setGmtModified(new Date());
        videoRepositoryService.insert(videoRepository);
        return SUCCESS_TIP;
    }

    /**
     * 删除视频资源库
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer videoRepositoryId) {
        videoRepositoryService.deleteById(videoRepositoryId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除视频资源库
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteVideoRepositoryList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String> videoRepositoryIdList = Arrays.asList(ss);
        boolean deleteResult = videoRepositoryService.deleteBatchIds(videoRepositoryIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("视频资源库批量删除失败，请稍后再试");
    }
    /**
     * 修改视频资源库
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VideoRepository videoRepository) {
        videoRepository.setGmtModified(new Date());
        videoRepositoryService.updateById(videoRepository);
        return SUCCESS_TIP;
    }

    /**
     * 视频资源库详情
     */
    @RequestMapping(value = "/detail/{videoRepositoryId}")
    @ResponseBody
    public Object detail(@PathVariable("videoRepositoryId") Integer videoRepositoryId) {
        return videoRepositoryService.selectById(videoRepositoryId);
    }
}
