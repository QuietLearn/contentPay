package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.service.INoticeService;
import com.stylefeng.guns.modular.system.service.INotificationService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.VIdList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyj on 2018/10/23
 */

@RestController
@RequestMapping("/front/notify")
public class NotifyFrontController extends BaseController {

    @Autowired
    private INotificationService notificationService;

    @RequestMapping(value = "/get_all_notify")
    public Result<FavoriteVo> getAllNotify(String uuidToken){
        return notificationService.getAllNotify(uuidToken);
    }

    @RequestMapping(value = "/get_push_notify")
    public Result getPushNotify(String uuidToken){
        return notificationService.getPushNotify(uuidToken);
    }
}
