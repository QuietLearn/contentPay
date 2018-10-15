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
import com.stylefeng.guns.modular.system.model.Words;
import com.stylefeng.guns.modular.system.service.IWordsService;

/**
 * 关键字控制器
 *
 * @author fengshuonan
 * @Date 2018-10-14 20:08:17
 */
@Controller
@RequestMapping("/words")
public class WordsController extends BaseController {

    private String PREFIX = "/system/words/";

    @Autowired
    private IWordsService wordsService;

    /**
     * 跳转到关键字首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "words.html";
    }

    /**
     * 跳转到添加关键字
     */
    @RequestMapping("/words_add")
    public String wordsAdd() {
        return PREFIX + "words_add.html";
    }

    /**
     * 跳转到修改关键字
     */
    @RequestMapping("/words_update/{wordsId}")
    public String wordsUpdate(@PathVariable Integer wordsId, Model model) {
        Words words = wordsService.selectById(wordsId);
        model.addAttribute("item",words);
        LogObjectHolder.me().set(words);
        return PREFIX + "words_edit.html";
    }

    /**
     * 获取关键字列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return wordsService.selectList(null);
    }

    /**
     * 新增关键字
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Words words) {
        wordsService.insert(words);
        return SUCCESS_TIP;
    }

    /**
     * 删除关键字
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer wordsId) {
        wordsService.deleteById(wordsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改关键字
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Words words) {
        wordsService.updateById(words);
        return SUCCESS_TIP;
    }

    /**
     * 关键字详情
     */
    @RequestMapping(value = "/detail/{wordsId}")
    @ResponseBody
    public Object detail(@PathVariable("wordsId") Integer wordsId) {
        return wordsService.selectById(wordsId);
    }
}
