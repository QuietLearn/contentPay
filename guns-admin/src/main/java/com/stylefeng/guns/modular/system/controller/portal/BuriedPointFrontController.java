package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
import com.stylefeng.guns.modular.system.warpper.FeedbackWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyj on 2018/10/23
 */

@RestController
@RequestMapping("/front/buried_point")
public class BuriedPointFrontController extends BaseController {

    @Autowired
    private IBuriedPointService iBuriedPointService;

    @RequestMapping(value = "/add")
    public Result insertBuriedPoint(BuriedPoint buriedPoint){
       return iBuriedPointService.insertAssemBuriedPoint(buriedPoint);
    }



}
