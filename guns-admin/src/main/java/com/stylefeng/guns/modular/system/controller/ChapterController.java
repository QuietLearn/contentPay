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
import com.stylefeng.guns.modular.system.model.Chapter;
import com.stylefeng.guns.modular.system.service.IChapterService;

import java.util.List;

/**
 * 章节管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-14 20:00:24
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController extends BaseController {

    private String PREFIX = "/system/chapter/";

    @Autowired
    private IChapterService chapterService;

    /**
     * 跳转到章节管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "chapter.html";
    }

    /**
     * 跳转到添加章节管理
     */
    @RequestMapping("/chapter_add")
    public String chapterAdd() {
        return PREFIX + "chapter_add.html";
    }

    /**
     * 跳转到修改章节管理
     */
    @RequestMapping("/chapter_update/{chapterId}")
    public String chapterUpdate(@PathVariable Integer chapterId, Model model) {
        Chapter chapter = chapterService.selectById(chapterId);
        model.addAttribute("item",chapter);
        LogObjectHolder.me().set(chapter);
        return PREFIX + "chapter_edit.html";
    }

    /**
     * 获取章节管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        List<Chapter> chapters = chapterService.selectList(null);
        return chapters;
    }

    /**
     * 新增章节管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Chapter chapter) {
        chapterService.insert(chapter);
        return SUCCESS_TIP;
    }

    /**
     * 删除章节管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer chapterId) {
        chapterService.deleteById(chapterId);
        return SUCCESS_TIP;
    }

    /**
     * 修改章节管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Chapter chapter) {
        chapterService.updateById(chapter);
        return SUCCESS_TIP;
    }

    /**
     * 章节管理详情
     */
    @RequestMapping(value = "/detail/{chapterId}")
    @ResponseBody
    public Object detail(@PathVariable("chapterId") Integer chapterId) {
        return chapterService.selectById(chapterId);
    }
}
