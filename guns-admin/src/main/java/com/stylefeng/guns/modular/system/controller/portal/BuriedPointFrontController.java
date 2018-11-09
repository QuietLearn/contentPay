package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
import com.stylefeng.guns.modular.system.warpper.FeedbackWarpper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyj on 2018/10/23
 */

@Api(description = "埋点接口")
@RestController
@RequestMapping("/front/buried_point")
public class BuriedPointFrontController extends BaseController {

    @Autowired
    private IBuriedPointService iBuriedPointService;

    @RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.GET})
    public Result insertBuriedPoint(BuriedPoint buriedPoint){
       return iBuriedPointService.insertAssemBuriedPoint(buriedPoint);
    }



}
