package com.stylefeng.guns.modular.repository.controller.backend;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
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
import com.stylefeng.guns.modular.system.model.ModelRepository;
import com.stylefeng.guns.modular.repository.service.IModelRepositoryService;
import java.util.Date;
import java.util.List;

/**
 * 模特资源库控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 16:01:57
 */
@Controller
@RequestMapping("/modelRepository")
public class ModelRepositoryController extends BaseController {

    private String PREFIX = "/repository/modelRepository/";

    @Autowired
    private IModelRepositoryService modelRepositoryService;

    /**
     * 跳转到模特资源库首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "modelRepository.html";
    }

    /**
     * 跳转到添加模特资源库
     */
    @RequestMapping("/modelRepository_add")
    public String modelRepositoryAdd() {
        return PREFIX + "modelRepository_add.html";
    }

    /**
     * 跳转到修改模特资源库
     */
    @RequestMapping("/modelRepository_update/{modelRepositoryId}")
    public String modelRepositoryUpdate(@PathVariable Integer modelRepositoryId, Model model) {
        ModelRepository modelRepository = modelRepositoryService.selectById(modelRepositoryId);
        model.addAttribute("item",modelRepository);
        LogObjectHolder.me().set(modelRepository);
        return PREFIX + "modelRepository_edit.html";
    }

    /**
     * 获取模特资源库列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return modelRepositoryService.selectList(null);
    }

    /**
     * 新增模特资源库
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ModelRepository modelRepository) {
        modelRepository.setGmtCreated(new Date());
        modelRepository.setGmtModified(new Date());
        modelRepositoryService.insert(modelRepository);
        return SUCCESS_TIP;
    }

    /**
     * 删除模特资源库
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer modelRepositoryId) {
        modelRepositoryService.deleteById(modelRepositoryId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除模特资源库
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteModelRepositoryList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String> modelRepositoryIdList = Arrays.asList(ss);
        boolean deleteResult = modelRepositoryService.deleteBatchIds(modelRepositoryIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("模特资源库批量删除失败，请稍后再试");
    }
    /**
     * 修改模特资源库
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ModelRepository modelRepository) {
        modelRepository.setGmtModified(new Date());
        modelRepositoryService.updateById(modelRepository);
        return SUCCESS_TIP;
    }

    /**
     * 模特资源库详情
     */
    @RequestMapping(value = "/detail/{modelRepositoryId}")
    @ResponseBody
    public Object detail(@PathVariable("modelRepositoryId") Integer modelRepositoryId) {
        return modelRepositoryService.selectById(modelRepositoryId);
    }
}
