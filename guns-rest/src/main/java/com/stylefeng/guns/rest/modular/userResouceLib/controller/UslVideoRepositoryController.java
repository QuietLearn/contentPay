package com.stylefeng.guns.rest.modular.userResouceLib.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.rest.core.common.result.Result;
import com.stylefeng.guns.rest.modular.userResouceLib.model.UslVideoRepository;
import com.stylefeng.guns.rest.modular.userResouceLib.service.IUslVideoRepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 发布视频控制器
 *
 * @author fengshuonan
 * @Date 2018-11-27 15:20:46
 */
@Controller
@RequestMapping("/uslVideoRepository")
public class UslVideoRepositoryController extends BaseController {

    private String PREFIX = "/userResouceLib/uslVideoRepository/";

    @Autowired
    private IUslVideoRepositoryService uslVideoRepositoryService;

    /**
     * 跳转到发布视频首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "uslVideoRepository.html";
    }

    /**
     * 跳转到添加发布视频
     */
    @RequestMapping("/uslVideoRepository_add")
    public String uslVideoRepositoryAdd() {
        return PREFIX + "uslVideoRepository_add.html";
    }

    /**
     * 跳转到修改发布视频
     */
    @RequestMapping("/uslVideoRepository_update/{uslVideoRepositoryId}")
    public String uslVideoRepositoryUpdate(@PathVariable Integer uslVideoRepositoryId, Model model) {
        UslVideoRepository uslVideoRepository = uslVideoRepositoryService.selectById(uslVideoRepositoryId);
        model.addAttribute("item",uslVideoRepository);
        //日志
        //LogObjectHolder.me().set(uslVideoRepository);
        return PREFIX + "uslVideoRepository_edit.html";
    }

    /**
     * 获取发布视频列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return uslVideoRepositoryService.selectList(null);
    }

    /**
     * 新增发布视频
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UslVideoRepository uslVideoRepository) {
        uslVideoRepository.setGmtCreated(new Date());
        uslVideoRepository.setGmtModified(new Date());
        uslVideoRepositoryService.insert(uslVideoRepository);
        return SUCCESS_TIP;
    }

    /**
     * 删除发布视频
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer uslVideoRepositoryId) {
        uslVideoRepositoryService.deleteById(uslVideoRepositoryId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除发布视频
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteUslVideoRepositoryList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String>  uslVideoRepositoryIdList = Arrays.asList(ss);
        boolean deleteResult = uslVideoRepositoryService.deleteBatchIds(uslVideoRepositoryIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("发布视频批量删除失败，请稍后再试");
    }
    /**
     * 修改发布视频
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UslVideoRepository uslVideoRepository) {
        uslVideoRepository.setGmtModified(new Date());
        uslVideoRepositoryService.updateById(uslVideoRepository);
        return SUCCESS_TIP;
    }

    /**
     * 发布视频详情
     */
    @RequestMapping(value = "/detail/{uslVideoRepositoryId}")
    @ResponseBody
    public Object detail(@PathVariable("uslVideoRepositoryId") Integer uslVideoRepositoryId) {
        return uslVideoRepositoryService.selectById(uslVideoRepositoryId);
    }
}
