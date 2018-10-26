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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyj on 2018/10/23
 */

@RestController
@RequestMapping("/front/playHistory")
public class PlayHistoryFrontController extends BaseController {

    @Autowired
    private IPlayHistoryService playHistoryService;

    @RequestMapping(value = "/list")
    public Result<PlayHistoryVo> list(String uuidToken){

        Result<PlayHistoryVo> list = playHistoryService.list(uuidToken);
        List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(list.getData().getVideoVoList())));
        list.getData().setVideoVoList(videoVoList);
        return list;
    }

    @RequestMapping(value = "/add")
    public Result addVideoToFav(VIdList vid, String uuidToken){
        Result list = playHistoryService.addVideoToFav(vid.getVid(), uuidToken);
        if (list.getData()!=null&&list.getData()!=""){
            List<VideoVo> videoVoList = (List<VideoVo>) super.warpObject(new TypeWarpper(BeanKit.listToMapList(((PlayHistoryVo)list.getData()).getVideoVoList())));
            ((PlayHistoryVo)list.getData()).setVideoVoList(videoVoList);
        }

        return list;
    }
}
