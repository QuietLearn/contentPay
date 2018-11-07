package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Notification;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 消息推送表 Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-11-07
 */
public interface NotificationMapper extends BaseMapper<Notification> {

    Integer selectMaxId();
}
