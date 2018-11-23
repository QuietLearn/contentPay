package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.warpper.MemberWarpper;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Active;
import com.stylefeng.guns.modular.system.service.IActiveService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 激活统计控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 18:55:47
 */
@Controller
@RequestMapping("/active")
public class ActiveController extends BaseController {

    private String PREFIX = "/system/active/";

    @Autowired
    private IActiveService activeService;

    /**
     * 跳转到激活统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "active.html";
    }

    /**
     * 跳转到添加激活统计
     */
    @RequestMapping("/active_add")
    public String activeAdd() {
        return PREFIX + "active_add.html";
    }

    /**
     * 跳转到修改激活统计
     */
    @RequestMapping("/active_update/{activeId}")
    public String activeUpdate(@PathVariable Integer activeId, Model model) {
        Active active = activeService.selectById(activeId);
        model.addAttribute("item",active);
        return PREFIX + "active_edit.html";
    }

    /**
     * 获取激活统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime,String province,Integer appId) {
        //注意改成server
        if (ToolUtil.isEmpty(province)&&ToolUtil.isEmpty(beginTime)&&ToolUtil.isEmpty(endTime)&&appId==null){
            Page<Active> page =new PageFactory<Active>().defaultPage();

            page = activeService.selectPage(page);

            List<Active> ActiveList = page.getRecords();
            page.setRecords((List<Active>)super.warpObject(new MemberWarpper(BeanKit.listToMapList(ActiveList))));

            PageInfoBT<Active> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<Active> page = new PageFactory<Active>().defaultPage();
            EntityWrapper<Active> entityWrapper = new EntityWrapper<>();
            if (appId!=null&&appId!=0){
                entityWrapper.eq("appId",appId);
            }
            if (ToolUtil.isNotEmpty(beginTime)&&ToolUtil.isNotEmpty(endTime)){
                entityWrapper.between("gmt_created",beginTime,endTime);
            }
            if (ToolUtil.isNotEmpty(province)){
                entityWrapper.like("address","%"+province+"%");
            }

            page = activeService.selectPage(page,entityWrapper);
            List<Active> Actives = page.getRecords();
            page.setRecords((List<Active>)super.warpObject(new MemberWarpper(BeanKit.listToMapList(Actives))));
            PageInfoBT<Active> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }
    }

    /**
     * 新增激活统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Active active) {
        active.setGmtCreated(new Date());
        active.setGmtModified(new Date());
        activeService.insert(active);
        return SUCCESS_TIP;
    }

    /**
     * 删除激活统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer activeId) {
        activeService.deleteById(activeId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除激活统计
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deletePointList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String>  activeIdList = Arrays.asList(ss);
        boolean deleteResult = activeService.deleteBatchIds(activeIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("激活记录批量删除失败，请稍后再试");
    }


    /**
     * 修改激活统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Active active) {
        active.setGmtModified(new Date());
        activeService.updateById(active);
        return SUCCESS_TIP;
    }

    /**
     * 激活统计详情
     */
    @RequestMapping(value = "/detail/{activeId}")
    @ResponseBody
    public Object detail(@PathVariable("activeId") Integer activeId) {
        return activeService.selectById(activeId);
    }
}
