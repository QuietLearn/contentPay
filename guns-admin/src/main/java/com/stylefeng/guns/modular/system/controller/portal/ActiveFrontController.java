package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Active;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IActiveService;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyj on 2018/10/23
 */

@RestController
@RequestMapping("/front/active")
public class ActiveFrontController extends BaseController {

    @Autowired
    private IActiveService activeService;

    @RequestMapping(value = "/add")
    public Result insertActive(BuriedPoint buriedPoint,String phoneType,String phoneBrand,String phoneSystem,String dpi){
       return activeService.insertAssemActive(buriedPoint,phoneType,phoneBrand,phoneSystem,dpi);
    }


    /*@RequestMapping(value="/error")
    public Result Get(){
        throw new RuntimeException();
    }*/

}
