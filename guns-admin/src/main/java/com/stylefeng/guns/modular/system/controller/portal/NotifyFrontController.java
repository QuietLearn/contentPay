package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.service.INoticeService;
import com.stylefeng.guns.modular.system.service.INotificationService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.VIdList;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyj on 2018/10/23
 */
@Api(description = "消息推送接口")
@RestController
@RequestMapping("/front/notify")
public class NotifyFrontController extends BaseController {

    @Autowired
    private INotificationService notificationService;

    @ApiOperation(value = "获得所有消息推送",notes = "获得所有消息推送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/get_all_notify", method = RequestMethod.POST)
    public Result<FavoriteVo> getAllNotify(String uuidToken){
        return notificationService.getAllNotify(uuidToken);
    }


    @ApiOperation(value = "获得推送消息",notes = "获得推送消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/get_push_notify", method = RequestMethod.POST)
    public Result getPushNotify(String uuidToken){
        return notificationService.getPushNotify(uuidToken);
    }
}
