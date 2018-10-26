package com.stylefeng.guns.modular.system.controller.backend.sea_cms;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.vo.DataVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.service.IDataService;

import java.util.List;

/**
 * 视频管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-18 17:20:07
 */
@Controller
@RequestMapping("/data")

public class DataController extends BaseController {

    private String PREFIX = "/system/data/";

    @Autowired
    private IDataService dataService;

    /**
     * 跳转到视频管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "data.html";
    }

    /**
     * 跳转到添加视频管理
     */
    @RequestMapping("/data_add")
    public String dataAdd() {
        return PREFIX + "data_add.html";
    }

    /**
     * 跳转到修改视频管理
     */
    @RequestMapping("/data_update/{dataId}")
    public String dataUpdate(@PathVariable Integer dataId, Model model) {
        Data data = dataService.getDataByVid(dataId);
        model.addAttribute("item",data);
        LogObjectHolder.me().set(data);
        return PREFIX + "data_edit.html";
    }

    /**
     * 获取视频管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isEmpty(condition)){
            Page page =new PageFactory().defaultPage();

            page = dataService.selectPageL(page);

            PageInfoBT pageInfoBT =this.packForBT(page);

            List<DataVo> dataVoList = dataService.assemDataVoList(page.getRecords());
            pageInfoBT.setRows(dataVoList);

            return pageInfoBT;
        }else {
            Page page =new PageFactory().defaultPage();
            EntityWrapper<Data> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("title","%"+condition+"%");
            //entityWrapper.like("title","%"+condition+"%");
            page = dataService.selectPage(page,entityWrapper);
            PageInfoBT pageInfoBT =this.packForBT(page);

            List<DataVo> dataVoList = dataService.assemDataVoList(page.getRecords());
            pageInfoBT.setRows(dataVoList);
            return pageInfoBT;
        }

        /*return dataService.selectList();*/
    }

    /**
     * 新增视频管理
     */
    @RequestMapping(value = "/add")
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @ResponseBody
    public Object add(Data data) {

        dataService.insert(data);
        return SUCCESS_TIP;
    }

    /**
     * 删除视频管理
     */
    @RequestMapping(value = "/delete")
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @ResponseBody
    public Object delete(@RequestParam Integer dataId) {
        dataService.deleteById(dataId);
        return SUCCESS_TIP;
    }

    /**
     * 修改视频管理
     */
    @RequestMapping(value = "/update")
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @ResponseBody
    public Object update(Data data) {
        dataService.updateById(data);
        return SUCCESS_TIP;
    }

    /**
     * 视频管理详情
     */
    @RequestMapping(value = "/detail/{dataId}")
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @ResponseBody
    public Object detail(@PathVariable("dataId") Integer dataId) {
        return dataService.selectById(dataId);
    }
}