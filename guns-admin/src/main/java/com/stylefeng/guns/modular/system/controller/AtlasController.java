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
import com.stylefeng.guns.modular.system.model.Atlas;
import com.stylefeng.guns.modular.system.service.IAtlasService;

import java.util.List;

/**
 * 图集管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-14 20:00:03
 */
@Controller
@RequestMapping("/atlas")
public class AtlasController extends BaseController {

    private String PREFIX = "/system/atlas/";

    @Autowired
    private IAtlasService atlasService;

    /**
     * 跳转到图集管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "atlas.html";
    }

    /**
     * 跳转到添加图集管理
     */
    @RequestMapping("/atlas_add")
    public String atlasAdd() {
        return PREFIX + "atlas_add.html";
    }

    /**
     * 跳转到修改图集管理
     */
    @RequestMapping("/atlas_update/{atlasId}")
    public String atlasUpdate(@PathVariable Integer atlasId, Model model) {
        Atlas atlas = atlasService.selectById(atlasId);
        model.addAttribute("item",atlas);
        LogObjectHolder.me().set(atlas);
        return PREFIX + "atlas_edit.html";
    }

    /**
     * 获取图集管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Atlas> atlases= atlasService.selectList(null);
        return atlases;
    }

    /**
     * 新增图集管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Atlas atlas) {
        atlasService.insert(atlas);
        return SUCCESS_TIP;
    }

    /**
     * 删除图集管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer atlasId) {
        atlasService.deleteById(atlasId);
        return SUCCESS_TIP;
    }

    /**
     * 修改图集管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Atlas atlas) {
        atlasService.updateById(atlas);
        return SUCCESS_TIP;
    }

    /**
     * 图集管理详情
     */
    @RequestMapping(value = "/detail/{atlasId}")
    @ResponseBody
    public Object detail(@PathVariable("atlasId") Integer atlasId) {
        return atlasService.selectById(atlasId);
    }
}
