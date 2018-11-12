package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.common.result.ReturnEx;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.model.Feedback;
import com.stylefeng.guns.modular.system.model.FeedbackType;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
import com.stylefeng.guns.modular.system.service.IFeedbackTypeService;
import com.stylefeng.guns.modular.system.warpper.FeedbackTypeWarpper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyj on 2018/10/23
 */
@Api(tags = {"用户问题反馈"})
@RestController
    @RequestMapping("/front/feedbacktype")
public class FeedbackTypeFrontController extends BaseController {

    @Autowired
    private IFeedbackTypeService feedbackTypeService;

    @ApiOperation(value = "得到问题反馈类型",notes = "得到问题反馈类型")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = ReturnEx.feedbackTypeReturnEx.addSuccessReturn),
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getFeedbackType(){
        List<FeedbackType> data = feedbackTypeService.getFeedbackType().getData();
        return Result.createBySuccess(super.warpObject(new FeedbackTypeWarpper(BeanKit.listToMapList(data))));
    }



}
