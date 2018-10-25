package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Video;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-25
 */
public interface VideoMapper extends BaseMapper<Video> {
    int syncDeleteVideoIds(@Param("dataIdList") List<Integer> dataIdList);

    Video selectByVid(int vid);

}
