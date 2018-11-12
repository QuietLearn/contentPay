package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.common.result.ReturnEx;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.model.Feedback;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.VIdList;
import com.stylefeng.guns.modular.system.vo.VideoVo;
import com.stylefeng.guns.modular.system.warpper.FeedbackTypeWarpper;
import com.stylefeng.guns.modular.system.warpper.FeedbackWarpper;
import com.stylefeng.guns.modular.system.warpper.TypeWarpper;
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
@RequestMapping("/front/feedback")
public class FeedbackFrontController extends BaseController {

    @Autowired
    private IFeedbackService feedbackService;

   /* @RequestMapping(value = "/list")
    public Result<FavoriteVo> list(String uuidToken){
        Result<FavoriteVo> list = favoriteService.list(uuidToken);
        List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(list.getData().getVideoVoList())));
        list.getData().setVideoVoList(videoVoList);
        return list;
    }*/

    @ApiOperation(value = "（可选）历史反馈列表",notes = "（可选）    历史反馈列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = ReturnEx.feedbackReturnEx.listSuccessReturn),
            @ApiResponse(code = 1, message = ReturnEx.feedbackReturnEx.failReturn),
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result listFeedbakcRecord(String uuidToken){
        List<Feedback> data = feedbackService.list(uuidToken).getData();
        return Result.createBySuccess(super.warpObject(new FeedbackWarpper(BeanKit.listToMapList(data))));
    }

    @ApiOperation(value = "添加问题反馈",notes = "添加问题反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = ReturnEx.feedbackReturnEx.addSuccessReturn),
            @ApiResponse(code = 1, message = ReturnEx.feedbackReturnEx.failReturn),
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addFeedback(String uuidToken, Feedback feedback){
       return feedbackService.addFeedback(uuidToken,feedback);
    }



}
