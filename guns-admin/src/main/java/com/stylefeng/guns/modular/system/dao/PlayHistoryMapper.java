package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.PlayHistory;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-23
 */
public interface PlayHistoryMapper extends BaseMapper<PlayHistory> {

    PlayHistory selectByVideoId(@Param("vid") int vid, @Param("memberId") int memberId);
}
