package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.PlayHistory;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.vo.PlayHistoryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-23
 */
public interface IPlayHistoryService extends IService<PlayHistory> {


    Result addVideoToFav(int vid, String uuidToken);

    Result<PlayHistoryVo> list(String uuidToken);
}
