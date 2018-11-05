package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.TokenCache;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Note;
import com.stylefeng.guns.modular.system.warpper.AppInfoWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;

import java.util.Date;
import java.util.List;

/**
 * 埋点统计控制器
 *
 * @author fengshuonan
 * @Date 2018-10-29 17:24:16
 */
@Controller
@RequestMapping("/buriedPoint")
public class BuriedPointController extends BaseController {

    private String PREFIX = "/system/buriedPoint/";

    @Autowired
    private IBuriedPointService buriedPointService;

    /**
     * 跳转到埋点统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "buriedPoint.html";
    }

    /**
     * 跳转到添加埋点统计
     */
    @RequestMapping("/buriedPoint_add")
    public String buriedPointAdd() {
        return PREFIX + "buriedPoint_add.html";
    }

    /**
     * 跳转到修改埋点统计
     */
    @RequestMapping("/buriedPoint_update/{buriedPointId}")
    public String buriedPointUpdate(@PathVariable Integer buriedPointId, Model model) {
        BuriedPoint buriedPoint = buriedPointService.selectById(buriedPointId);
        model.addAttribute("item",buriedPoint);
        LogObjectHolder.me().set(buriedPoint);
        return PREFIX + "buriedPoint_edit.html";
    }

    /**
     * 获取埋点统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer appId,Integer pointId) {
        //注意改成server
        if (appId==null ){
            Page<BuriedPoint> page =new PageFactory<BuriedPoint>().defaultPage();

            page = buriedPointService.selectPage(page);

            List<BuriedPoint> BuriedPointList = page.getRecords();
            page.setRecords((List<BuriedPoint>)super.warpObject(new AppInfoWarpper(BeanKit.listToMapList(BuriedPointList))));

            PageInfoBT<BuriedPoint> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<BuriedPoint> page = new PageFactory<BuriedPoint>().defaultPage();
            EntityWrapper<BuriedPoint> entityWrapper = new EntityWrapper<>();
            if (appId!=null&&appId!=0){
                entityWrapper.eq("app_id",appId);

            }
            if (pointId!=null&&pointId!=0){
                List<Integer> pointIdList = Lists.newArrayList();

                if (AllConst.PointMessageEnum.BEHAVIOR.getCode()==pointId){
                    for (AllConst.PointMessageEnum pointMessageEnum:AllConst.PointMessageEnum.values()) {
                        if (pointMessageEnum.getCode()<AllConst.PointMessageEnum.HOME.getCode()){
                            pointIdList.add(pointMessageEnum.getCode());
                        }
                    }
                    entityWrapper.in("point_id",pointIdList);
                } else if(AllConst.PointMessageEnum.AXN.getCode()==pointId){
                    for (AllConst.PointMessageEnum pointMessageEnum:AllConst.PointMessageEnum.values()) {
                        if (pointMessageEnum.getCode()>=AllConst.PointMessageEnum.HOME.getCode()){
                            pointIdList.add(pointMessageEnum.getCode());
                        }
                    }
                    entityWrapper.in("point_id",pointIdList);
                } else{
                    entityWrapper.eq("point_id",pointId);
                }
            }

            page = buriedPointService.selectPage(page,entityWrapper);
            List<BuriedPoint> BuriedPoints = page.getRecords();
            page.setRecords((List<BuriedPoint>)super.warpObject(new AppInfoWarpper(BeanKit.listToMapList(BuriedPoints))));
            PageInfoBT<BuriedPoint> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }
    }

    /**
     * 获取埋点统计的tree
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = Lists.newArrayList();
        String[] node = {AllConst.PointMessageEnum.BEHAVIOR.getMessage(),AllConst.PointMessageEnum.AXN.getMessage()};
        int i = 1;

        for (AllConst.PointMessageEnum pointMessageEnum:AllConst.PointMessageEnum.values()) {
            ZTreeNode zTreeNode = new ZTreeNode();
            zTreeNode.setChecked(true);
            zTreeNode.setIsOpen(true);
            if (i<=2){
                zTreeNode.setName(node[i-1]);
                zTreeNode.setpId(0L);
            }else{
                zTreeNode.setName(pointMessageEnum.getMessage());
                if (pointMessageEnum.getCode()<AllConst.PointMessageEnum.HOME.getCode()){
                    zTreeNode.setpId(1L);
                } else {
                    zTreeNode.setpId(2L);
                }
            }

            zTreeNode.setId(Integer.valueOf(i).longValue());
            zTreeNode.setPointId(pointMessageEnum.getCode());

            tree.add(zTreeNode);
            i++;
        }
        tree.add(ZTreeNode.createParent());


        return tree;
    }

    /**
     * 获取埋点统计类型
     */
    @RequestMapping(value = "/list_point_type")
    @ResponseBody
    public Object listType(String condition) {
        return buriedPointService.selectList(null);
    }

    /**
     * 新增埋点统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BuriedPoint buriedPoint) {
        buriedPoint.setGmtCreated(new Date());
        buriedPoint.setGmtModified(new Date());
        buriedPointService.insert(buriedPoint);
        return SUCCESS_TIP;
    }

    /**
     * 删除埋点统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer buriedPointId) {
        buriedPointService.deleteById(buriedPointId);
        return SUCCESS_TIP;
    }

    /**
     * 批量删除埋点统计
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deletePointList(@RequestParam String ids) {
        long deleteCount = buriedPointService.deletePointList(ids);
        if (deleteCount > 0){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("埋点记录批量删除失败，请稍后再试");
    }

    /**
     * 修改埋点统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BuriedPoint buriedPoint) {
        buriedPoint.setGmtModified(new Date());
        buriedPointService.updateById(buriedPoint);
        return SUCCESS_TIP;
    }

    /**
     * 埋点统计详情
     */
    @RequestMapping(value = "/detail/{buriedPointId}")
    @ResponseBody
    public Object detail(@PathVariable("buriedPointId") Integer buriedPointId) {
        return buriedPointService.selectById(buriedPointId);
    }
}
