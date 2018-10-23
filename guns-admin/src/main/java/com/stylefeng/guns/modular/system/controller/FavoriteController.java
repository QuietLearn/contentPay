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
import com.stylefeng.guns.modular.system.model.Favorite;
import com.stylefeng.guns.modular.system.service.IFavoriteService;

import java.util.Date;

/**
 * 用户看单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-23 14:54:45
 */
@Controller
@RequestMapping("/favorite")
public class FavoriteController extends BaseController {

    private String PREFIX = "/system/favorite/";

    @Autowired
    private IFavoriteService favoriteService;

    /**
     * 跳转到用户看单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "favorite.html";
    }

    /**
     * 跳转到添加用户看单管理
     */
    @RequestMapping("/favorite_add")
    public String favoriteAdd() {
        return PREFIX + "favorite_add.html";
    }

    /**
     * 跳转到修改用户看单管理
     */
    @RequestMapping("/favorite_update/{favoriteId}")
    public String favoriteUpdate(@PathVariable Integer favoriteId, Model model) {
        Favorite favorite = favoriteService.selectById(favoriteId);
        model.addAttribute("item",favorite);
        LogObjectHolder.me().set(favorite);
        return PREFIX + "favorite_edit.html";
    }

    /**
     * 获取用户看单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return favoriteService.selectList(null);
    }

    /**
     * 新增用户看单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Favorite favorite) {
        favorite.setGmtCreated(new Date());
        favorite.setGmtModified(new Date());
        favoriteService.insert(favorite);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户看单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer favoriteId) {
        favoriteService.deleteById(favoriteId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户看单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Favorite favorite) {
        favorite.setGmtModified(new Date());
        favoriteService.updateById(favorite);
        return SUCCESS_TIP;
    }

    /**
     * 用户看单管理详情
     */
    @RequestMapping(value = "/detail/{favoriteId}")
    @ResponseBody
    public Object detail(@PathVariable("favoriteId") Integer favoriteId) {
        return favoriteService.selectById(favoriteId);
    }
}