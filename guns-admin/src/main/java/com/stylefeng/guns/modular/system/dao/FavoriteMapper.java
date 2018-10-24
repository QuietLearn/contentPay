package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Favorite;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收藏夹表 分别查询三个表(先分别查出每个表资源的ids，在用in查询) Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-24
 */
public interface FavoriteMapper extends BaseMapper<Favorite> {

    List<Integer> selectVideoIdsByMember(Integer memberId);

    int deleteVideoIdsByMember(@Param("memberId") Integer memberId, @Param("vids")List<Integer> vids);

//    void addVideoIdsByMember(Integer id, List<Integer> vids);
}
