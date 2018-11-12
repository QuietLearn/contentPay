package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.common.result.ReturnEx;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
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
@Api(tags = {"收藏夹接口"})
@RestController
@RequestMapping("/front/favorite")
public class FavoriteFrontController extends BaseController {

    @Autowired
    private IFavoriteService favoriteService;

    @ApiOperation(value = "用户收藏夹列表",notes = "用户收藏夹列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ReturnEx.favoriteReturnEx.successRetrun),
            @ApiResponse(code = 200-1, message = ReturnEx.favoriteReturnEx.failReturn),
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<FavoriteVo> list(String uuidToken){
        Result<FavoriteVo> list = favoriteService.list(uuidToken);
        List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(list.getData().getVideoVoList())));
        list.getData().setVideoVoList(videoVoList);
        return list;
    }

   /* @RequestMapping(value = "/list2", method = RequestMethod.POST)
    public Result<FavoriteVo> list2(String uuidToken){
        Result<FavoriteVo> list = favoriteService.list(uuidToken);
        super.warpObject(new TypeWarpper(BeanKit.listToMapList(list.getData().getVideoVoList())));
        return list;
    }*/

    @ApiOperation(value = "用户收藏夹同步",notes = "用户收藏夹同步")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vid", value = "视频id list(vid[0]/vid[1] …)", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码",required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = ReturnEx.favoriteReturnEx.successRetrun),
            @ApiResponse(code = 1, message = ReturnEx.favoriteReturnEx.failReturn),
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addVideoToFav(VIdList vid, String uuidToken){
        Result list = favoriteService.addVideoToFav(vid.getVid(), uuidToken);
        if (list.getData()!=null&&list.getData()!=""){
            List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(((FavoriteVo)list.getData()).getVideoVoList())));
            ((FavoriteVo)list.getData()).setVideoVoList(videoVoList);
        }
        return list;
    }



}
