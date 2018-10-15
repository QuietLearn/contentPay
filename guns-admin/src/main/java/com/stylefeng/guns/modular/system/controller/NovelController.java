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
import com.stylefeng.guns.modular.system.model.Novel;
import com.stylefeng.guns.modular.system.service.INovelService;

import java.util.List;

/**
 * 小说管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-14 20:00:43
 */
@Controller
@RequestMapping("/novel")
public class NovelController extends BaseController {

    private String PREFIX = "/system/novel/";

    @Autowired
    private INovelService novelService;

    /**
     * 跳转到小说管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "novel.html";
    }

    /**
     * 跳转到添加小说管理
     */
    @RequestMapping("/novel_add")
    public String novelAdd() {
        return PREFIX + "novel_add.html";
    }

    /**
     * 跳转到修改小说管理
     */
    @RequestMapping("/novel_update/{novelId}")
    public String novelUpdate(@PathVariable Integer novelId, Model model) {
        Novel novel = novelService.selectById(novelId);
        model.addAttribute("item",novel);
        LogObjectHolder.me().set(novel);
        return PREFIX + "novel_edit.html";
    }

    /**
     * 获取小说管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        List<Novel> novels = novelService.selectList(null);
        return novels;
    }

    /**
     * 新增小说管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Novel novel) {
        novelService.insert(novel);
        return SUCCESS_TIP;
    }

    /**
     * 删除小说管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer novelId) {
        novelService.deleteById(novelId);
        return SUCCESS_TIP;
    }

    /**
     * 修改小说管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Novel novel) {
        novelService.updateById(novel);
        return SUCCESS_TIP;
    }

    /**
     * 小说管理详情
     */
    @RequestMapping(value = "/detail/{novelId}")
    @ResponseBody
    public Object detail(@PathVariable("novelId") Integer novelId) {
        return novelService.selectById(novelId);
    }
}
