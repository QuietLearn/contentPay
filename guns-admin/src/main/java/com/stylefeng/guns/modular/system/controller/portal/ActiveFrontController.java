package com.stylefeng.guns.modular.system.controller.portal;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Active;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IActiveService;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyj on 2018/10/23
 */

@Api(tags = {"激活接口"})
@RestController
@RequestMapping("/front/active")
public class ActiveFrontController extends BaseController {

    @Autowired
    private IActiveService activeService;

    @ApiOperation(value = "激活(添加)",notes = "激活(添加)",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneType", value = "手机型号",  dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "phoneBrand", value = "手机品牌",  dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "phoneSystem", value = "手机系统",  dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "dpi", value = "分辨率",  dataType = "Integer", paramType = "query")
    })
    //response = Result.class
    @ApiResponses(value = {
            @ApiResponse(code = 0,  message = "\"code\": 0,\n" +
                    "    \"data\": \"\",\n" +
                    "    \"msg\": \"激活成功\""),
            @ApiResponse(code = 1, message = "\"code\": 1,\n" +
                    "    \"data\": \"\",\n" +
                    "    \"msg\": \"激活失败\""),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result insertActive(BuriedPoint buriedPoint,String phoneType,String phoneBrand,String phoneSystem,String dpi){
       return activeService.insertAssemActive(buriedPoint,phoneType,phoneBrand,phoneSystem,dpi);
    }


    /*@RequestMapping(value="/error")
    public Result Get(){
        throw new RuntimeException();
    }*/

}
