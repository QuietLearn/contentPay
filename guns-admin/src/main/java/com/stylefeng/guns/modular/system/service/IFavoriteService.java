package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Favorite;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;

/**
 * <p>
 * 收藏夹表 分别查询三个表(先分别查出每个表资源的ids，在用in查询) 服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-23
 */
public interface IFavoriteService extends IService<Favorite> {

    /**
     * 列举用户的收藏夹
     * @param uuidToken
     * @return
     */
    Result<FavoriteVo> list(String uuidToken);

    /**
     * 视频加入用户收藏夹
     * @param vid
     * @param uuidToken
     * @return
     */
    Result addVideoToFav(int vid, String uuidToken);

}