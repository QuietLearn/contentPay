package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Favorite;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 收藏夹表 分别查询三个表(先分别查出每个表资源的ids，在用in查询) Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-23
 */
public interface FavoriteMapper extends BaseMapper<Favorite> {

}
