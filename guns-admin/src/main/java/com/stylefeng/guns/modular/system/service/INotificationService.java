package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Notification;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 消息推送表 服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-26
 */
public interface INotificationService extends IService<Notification> {

    /**
     * 获取全部通知
     * @return
     */
    Result getAllNotify(String uuidToken);

    /**
     * 获取最新通知推送
     * @return
     */
    Result<Notification> getPushNotify(String uuidToken);
}
