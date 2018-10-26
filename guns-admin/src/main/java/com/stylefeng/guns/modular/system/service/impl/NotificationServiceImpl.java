package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Notification;
import com.stylefeng.guns.modular.system.dao.NotificationMapper;
import com.stylefeng.guns.modular.system.service.INotificationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 消息推送表 服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-26
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements INotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    public Result getAllNotify(){
        Wrapper<Notification> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id",false);
        List<Notification> notifications = this.selectList(wrapper);
        return Result.createBySuccess(notifications);
    }

    public Result getPushNotify(){

        Integer maxId = notificationMapper.selectMaxId();
        Notification notification = this.selectById(maxId);
        return Result.createBySuccess(notification);
    }
}
