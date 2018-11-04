package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.PlayHistory;
import com.stylefeng.guns.modular.system.warpper.AppInfoWarpper;
import com.stylefeng.guns.modular.system.warpper.DictWarpper;
import com.stylefeng.guns.modular.system.warpper.TypeWarpper;
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
import java.util.List;
import java.util.Map;

/**
 * 用户看单控制器
 *
 * @author fengshuonan
 * @Date 2018-10-24 11:03:17
 */
@Controller
@RequestMapping("/favorite")
public class FavoriteController extends BaseController {

    private String PREFIX = "/system/favorite/";

    @Autowired
    private IFavoriteService favoriteService;

    /**
     * 跳转到用户看单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "favorite.html";
    }

    /**
     * 跳转到添加用户看单
     */
    @RequestMapping("/favorite_add")
    public String favoriteAdd() {
        return PREFIX + "favorite_add.html";
    }

    /**
     * 跳转到修改用户看单
     */
    @RequestMapping("/favorite_update/{favoriteId}")
    public String favoriteUpdate(@PathVariable Integer favoriteId, Model model) {
        Favorite favorite = favoriteService.selectById(favoriteId);
        model.addAttribute("item",favorite);
        LogObjectHolder.me().set(favorite);
        return PREFIX + "favorite_edit.html";
    }

    /**
     * 获取用户看单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String username,Integer appId,String mobile) {
        //注意改成server
        if (ToolUtil.isEmpty(username)&&ToolUtil.isEmpty(mobile)&&appId==null){
            Page<Favorite> page =new PageFactory<Favorite>().defaultPage();

            page = favoriteService.selectPage(page);

            List<Favorite> favoriteList = page.getRecords();
            page.setRecords((List<Favorite>)super.warpObject(new AppInfoWarpper(BeanKit.listToMapList(favoriteList))));

            PageInfoBT<Favorite> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<Favorite> page = new PageFactory<Favorite>().defaultPage();
            EntityWrapper<Favorite> entityWrapper = new EntityWrapper<>();
            if (appId!=null&&appId!=0){
                entityWrapper.eq("appId",appId);
            }
            if (ToolUtil.isNotEmpty(username)){
                entityWrapper.like("member_username","%"+username+"%");
            }
            if (ToolUtil.isNotEmpty(mobile)){
                entityWrapper.like("mobile","%"+mobile+"%");
            }

            page = favoriteService.selectPage(page,entityWrapper);
            List<Favorite> Favorites = page.getRecords();
            page.setRecords((List<Favorite>)super.warpObject(new AppInfoWarpper(BeanKit.listToMapList(Favorites))));
            PageInfoBT<Favorite> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }

    }

    /**
     * 新增用户看单
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
     * 删除用户看单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer favoriteId) {
        favoriteService.deleteById(favoriteId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户看单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Favorite favorite) {
        favorite.setGmtModified(new Date());
        favoriteService.updateById(favorite);
        return SUCCESS_TIP;
    }

    /**
     * 用户看单详情
     */
    @RequestMapping(value = "/detail/{favoriteId}")
    @ResponseBody
    public Object detail(@PathVariable("favoriteId") Integer favoriteId) {
        return favoriteService.selectById(favoriteId);
    }
}
