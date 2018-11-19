package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.AppContent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-11-19
 */
public interface AppContentMapper extends BaseMapper<AppContent> {

    AppContent searchAppContent(@Param("appId") String appId, @Param("appVer") String appVer, @Param("channelId") String channelId);
}
