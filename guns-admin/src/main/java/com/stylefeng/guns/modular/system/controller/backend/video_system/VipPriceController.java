package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.warpper.MemberTypeWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.VipPrice;
import com.stylefeng.guns.modular.system.service.IVipPriceService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 会员价格管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-18 14:20:44
 */
@Controller
@RequestMapping("/vipPrice")
public class VipPriceController extends BaseController {

    private String PREFIX = "/system/vipPrice/";

    @Autowired
    private IVipPriceService vipPriceService;

    /**
     * 跳转到会员价格管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "vipPrice.html";
    }

    /**
     * 跳转到添加会员价格管理
     */
    @RequestMapping("/vipPrice_add")
    public String vipPriceAdd() {
        return PREFIX + "vipPrice_add.html";
    }

    /**
     * 跳转到修改会员价格管理
     */
    @RequestMapping("/vipPrice_update/{vipPriceId}")
    public String vipPriceUpdate(@PathVariable Integer vipPriceId, Model model) {
        VipPrice vipPrice = vipPriceService.selectById(vipPriceId);
        model.addAttribute("item",vipPrice);
        LogObjectHolder.me().set(vipPrice);
        return PREFIX + "vipPrice_edit.html";
    }

    /**
     * 获取会员价格管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<VipPrice> vipPrices = vipPriceService.selectList(null);

        return super.warpObject(new MemberTypeWarpper(BeanKit.listToMapList(vipPrices)));
    }

    /**
     * 新增会员价格管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VipPrice vipPrice) {
        vipPrice.setGmtCreated(new Date());
        vipPrice.setGmtUpdated(new Date());
        vipPrice.setPrice(vipPrice.getPrice()+"¥");
        vipPrice.setAging(vipPrice.getAging()+"d");
        vipPriceService.insert(vipPrice);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员价格管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer vipPriceId) {
        vipPriceService.deleteById(vipPriceId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员价格管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VipPrice vipPrice) {
        vipPrice.setGmtUpdated(new Date());
        vipPriceService.updateById(vipPrice);
        return SUCCESS_TIP;
    }

    /**
     * 会员价格管理详情
     */
    @RequestMapping(value = "/detail/{vipPriceId}")
    @ResponseBody
    public Object detail(@PathVariable("vipPriceId") Integer vipPriceId) {
        return vipPriceService.selectById(vipPriceId);
    }
}
