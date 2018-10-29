package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.model.Feedback;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
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

    @RequestMapping(value = "/add")
    Result addFeedback(String uuidToken, Feedback feedback){
       return feedbackService.addFeedback(uuidToken,feedback);
    }



}
