package com.stylefeng.guns.modular.repository.controller.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.model.VideoAppSource;
import com.stylefeng.guns.modular.system.warpper.MemberWarpper;
import com.stylefeng.guns.modular.system.warpper.repo.ModelAppSourceWarpper;
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
import com.stylefeng.guns.modular.system.model.ModelAppSource;
import com.stylefeng.guns.modular.repository.service.IModelAppSourceService;
import java.util.Date;
import java.util.List;

/**
 * 模特应用内容控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 17:38:57
 */
@Controller
@RequestMapping("/modelAppSource")
public class ModelAppSourceController extends BaseController {

    private String PREFIX = "/repository/modelAppSource/";

    @Autowired
    private IModelAppSourceService modelAppSourceService;

    /**
     * 跳转到模特应用内容首页
     */
    @RequestMapping("")
    public String index(Integer appContentId, Model model) {
        if (appContentId!=null){
            model.addAttribute("apdSearch",appContentId);
        }else{
            model.addAttribute("apdSearch",null);
        }
        return PREFIX + "modelAppSource.html";
    }

    /**
     * 跳转到添加模特应用内容
     */
    @RequestMapping("/modelAppSource_add")
    public String modelAppSourceAdd() {
        return PREFIX + "modelAppSource_add.html";
    }

    /**
     * 跳转到修改模特应用内容
     */
    @RequestMapping("/modelAppSource_update/{modelAppSourceId}")
    public String modelAppSourceUpdate(@PathVariable Integer modelAppSourceId, Model model) {
        ModelAppSource modelAppSource = modelAppSourceService.selectById(modelAppSourceId);
        model.addAttribute("item",modelAppSource);
        LogObjectHolder.me().set(modelAppSource);
        return PREFIX + "modelAppSource_edit.html";
    }

    /**
     * 获取模特应用内容列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer appContentId) {
        //注意改成server

        if (appContentId == null) {
            Page<ModelAppSource> page = new PageFactory<ModelAppSource>().defaultPage();
            EntityWrapper<ModelAppSource> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("is_del",1);
            page = modelAppSourceService.selectPage(page,entityWrapper);

            List<ModelAppSource> ModelAppSourceList = page.getRecords();
            page.setRecords((List<ModelAppSource>) super.warpObject(new ModelAppSourceWarpper(BeanKit.listToMapList(ModelAppSourceList))));

            PageInfoBT<ModelAppSource> pageInfoBT = this.packForBT(page);

            return pageInfoBT;
        } else {
            Page<ModelAppSource> page = new PageFactory<ModelAppSource>().defaultPage();
            EntityWrapper<ModelAppSource> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("is_del",1);
            if (appContentId != null && appContentId != 0) {
                entityWrapper.eq("app_content_id", appContentId);
            }

            page = modelAppSourceService.selectPage(page, entityWrapper);
            List<ModelAppSource> ModelAppSources = page.getRecords();
            page.setRecords((List<ModelAppSource>) super.warpObject(new ModelAppSourceWarpper(BeanKit.listToMapList(ModelAppSources))));
            PageInfoBT<ModelAppSource> pageInfoBT = this.packForBT(page);

            return pageInfoBT;
        }
    }

    /**
     * 新增模特应用内容
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ModelAppSource modelAppSource) {
        modelAppSource.setGmtCreated(new Date());
        modelAppSource.setGmtModified(new Date());
        modelAppSourceService.insert(modelAppSource);
        return SUCCESS_TIP;
    }

    /**
     * 删除模特应用内容
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer modelAppSourceId) {
        modelAppSourceService.deleteById(modelAppSourceId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除模特应用内容
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteModelAppSourceList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String> modelAppSourceIdList = Arrays.asList(ss);
        boolean deleteResult = modelAppSourceService.deleteBatchIds(modelAppSourceIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("模特应用内容批量删除失败，请稍后再试");
    }
    /**
     * 修改模特应用内容
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ModelAppSource modelAppSource) {
        modelAppSource.setGmtModified(new Date());
        modelAppSourceService.updateById(modelAppSource);
        return SUCCESS_TIP;
    }

    /**
     * 模特应用内容详情
     */
    @RequestMapping(value = "/detail/{modelAppSourceId}")
    @ResponseBody
    public Object detail(@PathVariable("modelAppSourceId") Integer modelAppSourceId) {
        return modelAppSourceService.selectById(modelAppSourceId);
    }
}
