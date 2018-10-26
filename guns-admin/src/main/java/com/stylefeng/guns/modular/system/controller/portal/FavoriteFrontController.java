package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.VIdList;
import com.stylefeng.guns.modular.system.vo.VideoVo;
import com.stylefeng.guns.modular.system.warpper.TypeWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyj on 2018/10/23
 */

@RestController
@RequestMapping("/front/favorite")
public class FavoriteFrontController extends BaseController {

    @Autowired
    private IFavoriteService favoriteService;

    @RequestMapping(value = "/list")
    public Result<FavoriteVo> list(String uuidToken){
        Result<FavoriteVo> list = favoriteService.list(uuidToken);
        List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(list.getData().getVideoVoList())));
        list.getData().setVideoVoList(videoVoList);
        return list;
    }
    @RequestMapping(value = "/list2")
    public Result<FavoriteVo> list2(String uuidToken){
        Result<FavoriteVo> list = favoriteService.list(uuidToken);
        super.warpObject(new TypeWarpper(BeanKit.listToMapList(list.getData().getVideoVoList())));
        return list;
    }

    @RequestMapping(value = "/add")
    public Result addVideoToFav(VIdList vid, String uuidToken){
        Result list = favoriteService.addVideoToFav(vid.getVid(), uuidToken);
        if (list.getData()!=null&&list.getData()!=""){
            List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(((FavoriteVo)list.getData()).getVideoVoList())));
            ((FavoriteVo)list.getData()).setVideoVoList(videoVoList);
        }
        return list;
    }



}
