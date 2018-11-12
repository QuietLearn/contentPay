package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
import com.stylefeng.guns.modular.system.warpper.FeedbackWarpper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyj on 2018/10/23
 */

@Api(tags = {"埋点"})
@RestController
@RequestMapping("/front/buried_point")
public class BuriedPointFrontController extends BaseController {

    @Autowired
    private IBuriedPointService iBuriedPointService;

    @ApiOperation(value = "埋点(添加)",notes = "埋点(添加)",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = " \"code\": 0,\n" +
                    "    \"data\": \"\",\n" +
                    "    \"msg\": \"埋点成功\""),
            @ApiResponse(code = 1, message = "  \"code\": 1,\n" +
                    "    \"data\": \"\",\n" +
                    "    \"msg\": \"埋点失败\""),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result insertBuriedPoint(BuriedPoint buriedPoint){
       return iBuriedPointService.insertAssemBuriedPoint(buriedPoint);
    }



}
