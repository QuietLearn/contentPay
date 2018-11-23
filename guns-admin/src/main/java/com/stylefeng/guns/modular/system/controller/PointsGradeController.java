package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.Arrays;
import com.stylefeng.guns.core.common.result.Result;
import java.util.List;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.PointsGrade;
import com.stylefeng.guns.modular.system.service.IPointsGradeService;
import java.util.Date;

/**
 * 积分价格控制器
 *
 * @author fengshuonan
 * @Date 2018-11-23 16:42:15
 */
@Controller
@RequestMapping("/pointsGrade")
public class PointsGradeController extends BaseController {

    private String PREFIX = "/system/pointsGrade/";

    @Autowired
    private IPointsGradeService pointsGradeService;

    /**
     * 跳转到积分价格首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pointsGrade.html";
    }

    /**
     * 跳转到添加积分价格
     */
    @RequestMapping("/pointsGrade_add")
    public String pointsGradeAdd() {
        return PREFIX + "pointsGrade_add.html";
    }

    /**
     * 跳转到修改积分价格
     */
    @RequestMapping("/pointsGrade_update/{pointsGradeId}")
    public String pointsGradeUpdate(@PathVariable Integer pointsGradeId, Model model) {
        PointsGrade pointsGrade = pointsGradeService.selectById(pointsGradeId);
        model.addAttribute("item",pointsGrade);
        LogObjectHolder.me().set(pointsGrade);
        return PREFIX + "pointsGrade_edit.html";
    }

    /**
     * 获取积分价格列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return pointsGradeService.selectList(null);
    }

    /**
     * 新增积分价格
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PointsGrade pointsGrade) {
        pointsGrade.setGmtCreated(new Date());
        pointsGrade.setGmtModified(new Date());
        pointsGradeService.insert(pointsGrade);
        return SUCCESS_TIP;
    }

    /**
     * 删除积分价格
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer pointsGradeId) {
        pointsGradeService.deleteById(pointsGradeId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除积分价格
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deletePointsGradeList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String>  pointsGradeIdList = Arrays.asList(ss);
        boolean deleteResult = pointsGradeService.deleteBatchIds(pointsGradeIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("积分价格批量删除失败，请稍后再试");
    }
    /**
     * 修改积分价格
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PointsGrade pointsGrade) {
        pointsGrade.setGmtModified(new Date());
        pointsGradeService.updateById(pointsGrade);
        return SUCCESS_TIP;
    }

    /**
     * 积分价格详情
     */
    @RequestMapping(value = "/detail/{pointsGradeId}")
    @ResponseBody
    public Object detail(@PathVariable("pointsGradeId") Integer pointsGradeId) {
        return pointsGradeService.selectById(pointsGradeId);
    }
}
