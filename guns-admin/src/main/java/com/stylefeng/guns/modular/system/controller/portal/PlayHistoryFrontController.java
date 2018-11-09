package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.service.IPlayHistoryService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.PlayHistoryVo;
import com.stylefeng.guns.modular.system.vo.VIdList;
import com.stylefeng.guns.modular.system.vo.VideoVo;
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
@Api(description = "用户播放历史接口")
@RestController
@RequestMapping("/front/playHistory")
public class PlayHistoryFrontController extends BaseController {

    @Autowired
    private IPlayHistoryService playHistoryService;

    @ApiOperation(value = "用户播放历史列表",notes = "用户播放历史列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<PlayHistoryVo> list(String uuidToken){

        Result<PlayHistoryVo> list = playHistoryService.list(uuidToken);
        List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(list.getData().getVideoVoList())));
        list.getData().setVideoVoList(videoVoList);
        return list;
    }

    @ApiOperation(value = "同步用户播放历史",notes = "同步用户播放历史")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vid", value = "视频id list(vid[0]/vid[1] …)", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addVideoToFav(VIdList vid, String uuidToken){
        Result list = playHistoryService.addVideoToFav(vid.getVid(), uuidToken);
        if (list.getData()!=null&&list.getData()!=""){
            List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(((PlayHistoryVo)list.getData()).getVideoVoList())));
            ((PlayHistoryVo)list.getData()).setVideoVoList(videoVoList);
        }

        return list;
    }
}
