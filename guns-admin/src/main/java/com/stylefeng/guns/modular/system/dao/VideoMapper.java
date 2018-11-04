package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.model.Video;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据条件查询视频列表
     */
    List<Map<String, Object>> selectVideos(@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("videoName") String videoName);

}
