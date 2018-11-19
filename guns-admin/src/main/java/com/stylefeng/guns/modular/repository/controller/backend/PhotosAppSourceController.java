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
import com.stylefeng.guns.modular.system.model.PhotosAppSource;
import com.stylefeng.guns.modular.repository.service.IPhotosAppSourceService;
import java.util.Date;
import java.util.List;

/**
 * 图集应用内容控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 17:38:32
 */
@Controller
@RequestMapping("/photosAppSource")
public class PhotosAppSourceController extends BaseController {

    private String PREFIX = "/repository/photosAppSource/";

    @Autowired
    private IPhotosAppSourceService photosAppSourceService;

    /**
     * 跳转到图集应用内容首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "photosAppSource.html";
    }

    /**
     * 跳转到添加图集应用内容
     */
    @RequestMapping("/photosAppSource_add")
    public String photosAppSourceAdd() {
        return PREFIX + "photosAppSource_add.html";
    }

    /**
     * 跳转到修改图集应用内容
     */
    @RequestMapping("/photosAppSource_update/{photosAppSourceId}")
    public String photosAppSourceUpdate(@PathVariable Integer photosAppSourceId, Model model) {
        PhotosAppSource photosAppSource = photosAppSourceService.selectById(photosAppSourceId);
        model.addAttribute("item",photosAppSource);
        LogObjectHolder.me().set(photosAppSource);
        return PREFIX + "photosAppSource_edit.html";
    }

    /**
     * 获取图集应用内容列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return photosAppSourceService.selectList(null);
    }

    /**
     * 新增图集应用内容
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PhotosAppSource photosAppSource) {
        photosAppSource.setGmtCreated(new Date());
        photosAppSource.setGmtModified(new Date());
        photosAppSourceService.insert(photosAppSource);
        return SUCCESS_TIP;
    }

    /**
     * 删除图集应用内容
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer photosAppSourceId) {
        photosAppSourceService.deleteById(photosAppSourceId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除图集应用内容
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deletePhotosAppSourceList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String> photosAppSourceIdList = Arrays.asList(ss);
        boolean deleteResult = photosAppSourceService.deleteBatchIds(photosAppSourceIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("图集应用内容批量删除失败，请稍后再试");
    }
    /**
     * 修改图集应用内容
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PhotosAppSource photosAppSource) {
        photosAppSource.setGmtModified(new Date());
        photosAppSourceService.updateById(photosAppSource);
        return SUCCESS_TIP;
    }

    /**
     * 图集应用内容详情
     */
    @RequestMapping(value = "/detail/{photosAppSourceId}")
    @ResponseBody
    public Object detail(@PathVariable("photosAppSourceId") Integer photosAppSourceId) {
        return photosAppSourceService.selectById(photosAppSourceId);
    }
}
