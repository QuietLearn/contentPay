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
import com.stylefeng.guns.modular.system.model.PicturesCategory;
import com.stylefeng.guns.modular.repository.service.IPicturesCategoryService;
import java.util.Date;
import java.util.List;

/**
 * 图集资源库控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 15:50:02
 */
@Controller
@RequestMapping("/picturesCategory")
public class PicturesCategoryController extends BaseController {

    private String PREFIX = "/repository/picturesCategory/";

    @Autowired
    private IPicturesCategoryService picturesCategoryService;

    /**
     * 跳转到图集资源库首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "picturesCategory.html";
    }

    /**
     * 跳转到添加图集资源库
     */
    @RequestMapping("/picturesCategory_add")
    public String picturesCategoryAdd() {
        return PREFIX + "picturesCategory_add.html";
    }

    /**
     * 跳转到修改图集资源库
     */
    @RequestMapping("/picturesCategory_update/{picturesCategoryId}")
    public String picturesCategoryUpdate(@PathVariable Integer picturesCategoryId, Model model) {
        PicturesCategory picturesCategory = picturesCategoryService.selectById(picturesCategoryId);
        model.addAttribute("item",picturesCategory);
        LogObjectHolder.me().set(picturesCategory);
        return PREFIX + "picturesCategory_edit.html";
    }

    /**
     * 获取图集资源库列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return picturesCategoryService.selectList(null);
    }

    /**
     * 新增图集资源库
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PicturesCategory picturesCategory) {
        picturesCategory.setGmtCreated(new Date());
        picturesCategory.setGmtModified(new Date());
        picturesCategoryService.insert(picturesCategory);
        return SUCCESS_TIP;
    }

    /**
     * 删除图集资源库
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer picturesCategoryId) {
        picturesCategoryService.deleteById(picturesCategoryId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除图集资源库
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deletePicturesCategoryList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String> picturesCategoryIdList = Arrays.asList(ss);
        boolean deleteResult = picturesCategoryService.deleteBatchIds(picturesCategoryIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("图集资源库批量删除失败，请稍后再试");
    }
    /**
     * 修改图集资源库
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PicturesCategory picturesCategory) {
        picturesCategory.setGmtModified(new Date());
        picturesCategoryService.updateById(picturesCategory);
        return SUCCESS_TIP;
    }

    /**
     * 图集资源库详情
     */
    @RequestMapping(value = "/detail/{picturesCategoryId}")
    @ResponseBody
    public Object detail(@PathVariable("picturesCategoryId") Integer picturesCategoryId) {
        return picturesCategoryService.selectById(picturesCategoryId);
    }
}
