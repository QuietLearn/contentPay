package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.common.result.ReturnEx;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.model.Notification;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.service.INoticeService;
import com.stylefeng.guns.modular.system.service.INotificationService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.VIdList;
import com.stylefeng.guns.modular.system.warpper.NotificationFrontWarpper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * Created by hyj on 2018/10/23
 */
@Api(tags = {"消息推送接口"})
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
            @ApiResponse(code = 0, message = ReturnEx.notificationReturnEx.getPushNotifySuccessReturn),
            @ApiResponse(code = 1, message = ReturnEx.notificationReturnEx.getPushNotifySuccessReturn),
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
            @ApiResponse(code = 0, message = ReturnEx.notificationReturnEx.getAllNotifySuccessReturn),
            @ApiResponse(code = 1, message = ReturnEx.notificationReturnEx.getAllNotifyFailReturn),
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/get_push_notify", method = RequestMethod.POST)
    public Result getPushNotify(String uuidToken){
        Result<Notification> pushNotifyResult = notificationService.getPushNotify(uuidToken);
        Notification pushNotify = pushNotifyResult.getData();

        return Result.createBySuccess(super.warpObject(new NotificationFrontWarpper(BeanKit.beanToMap(pushNotify))));
    }
}
