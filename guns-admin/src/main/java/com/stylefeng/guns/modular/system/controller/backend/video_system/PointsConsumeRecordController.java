package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.google.common.collect.Lists;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.model.PlayHistory;
import com.stylefeng.guns.modular.system.warpper.AppWarpper;
import com.stylefeng.guns.modular.system.warpper.MemberWarpper;
import com.stylefeng.guns.modular.system.warpper.TypeWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.PointsConsumeRecord;
import com.stylefeng.guns.modular.system.service.IPointsConsumeRecordService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 积分流水记录控制器
 *
 * @author fengshuonan
 * @Date 2018-10-25 14:49:23
 */
@Controller
@RequestMapping("/pointsConsumeRecord")
public class PointsConsumeRecordController extends BaseController {

    private String PREFIX = "/system/pointsConsumeRecord/";

    @Autowired
    private IPointsConsumeRecordService pointsConsumeRecordService;

    /**
     * 跳转到积分流水记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pointsConsumeRecord.html";
    }

    /**
     * 跳转到添加积分流水记录
     */
    @RequestMapping("/pointsConsumeRecord_add")
    public String pointsConsumeRecordAdd() {
        return PREFIX + "pointsConsumeRecord_add.html";
    }

    /**
     * 跳转到修改积分流水记录
     */
    @RequestMapping("/pointsConsumeRecord_update/{pointsConsumeRecordId}")
    public String pointsConsumeRecordUpdate(@PathVariable Integer pointsConsumeRecordId, Model model) {
        PointsConsumeRecord pointsConsumeRecord = pointsConsumeRecordService.selectById(pointsConsumeRecordId);
        model.addAttribute("item",pointsConsumeRecord);
        LogObjectHolder.me().set(pointsConsumeRecord);
        return PREFIX + "pointsConsumeRecord_edit.html";
    }

    /**
     * 获取积分流水记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<PointsConsumeRecord> pointsConsumeRecords = pointsConsumeRecordService.selectList(null);

        return super.warpObject(new MemberWarpper(BeanKit.listToMapList(pointsConsumeRecords)));
    }

    /**
     * 新增积分流水记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PointsConsumeRecord pointsConsumeRecord) {
        pointsConsumeRecord.setGmtCreated(new Date());
        pointsConsumeRecord.setGmtModified(new Date());
        pointsConsumeRecordService.insert(pointsConsumeRecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除积分流水记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer pointsConsumeRecordId) {
        pointsConsumeRecordService.deleteById(pointsConsumeRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改积分流水记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PointsConsumeRecord pointsConsumeRecord) {
        pointsConsumeRecord.setGmtModified(new Date());
        pointsConsumeRecordService.updateById(pointsConsumeRecord);
        return SUCCESS_TIP;
    }

    /**
     * 积分流水记录详情
     */
    @RequestMapping(value = "/detail/{pointsConsumeRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("pointsConsumeRecordId") Integer pointsConsumeRecordId) {
        return pointsConsumeRecordService.selectById(pointsConsumeRecordId);
    }
}
