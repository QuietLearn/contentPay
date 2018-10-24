package com.stylefeng.guns.modular.system.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.VIdList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return favoriteService.list(uuidToken);
    }

    @RequestMapping(value = "/add")
    public Result addVideoToFav(VIdList vid, String uuidToken){
        return favoriteService.addVideoToFav(vid.getVid(),uuidToken);
    }
}
