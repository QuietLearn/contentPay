package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Notification;
import com.stylefeng.guns.modular.system.dao.NotificationMapper;
import com.stylefeng.guns.modular.system.service.INotificationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Autowired
    private MemberMapper memberMapper;

    //String uuidToken,int appId,String appVer
    public Result getAllNotify(String uuidToken){

        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        Wrapper<Notification> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id",false);
        List<Notification> notifications = this.selectList(wrapper);

        for (Notification notification: notifications) {
          /*  String memberIdList = notification.getMemberId();
            String[] memberIdString = StringUtils.split(memberIdList, ",");
            Arrays.asList(memberIdString);*/

            /*Wrapper<Notification> wrapper1 = new EntityWrapper<>();
            wrapper.in("memberId",notification.getMemberId());*/

        }


        return Result.createBySuccess(notifications);
    }



    public Result getPushNotify(String uuidToken){

        Integer maxId = notificationMapper.selectMaxId();
        Notification notification = this.selectById(maxId);
        return Result.createBySuccess(notification);
    }
}
