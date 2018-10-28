package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.PlayHistory;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 播放历史表 Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
public interface PlayHistoryMapper extends BaseMapper<PlayHistory> {
    PlayHistory selectByVideoId(@Param("vid") int vid, @Param("memberId") Integer memberId);

    List<Integer> selectVideoIdsByMember(Integer memberId);
}
