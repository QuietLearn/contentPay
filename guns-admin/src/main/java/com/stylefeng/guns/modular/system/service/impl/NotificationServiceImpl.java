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
import java.util.stream.Collectors;

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
        if (member==null){
            return Result.createByErrorMessage("请重新登录");
        }
        Wrapper<Notification> wrapper = new EntityWrapper<>();
        wrapper.orderBy("id",false);
        List<Notification> notifications = this.selectList(wrapper);

        for (Notification notification: notifications) {
            String memberIds = notification.getMemberId();
            if (StringUtils.equals("0",memberIds)||1==notification.getIsOfficial()){
                continue;
            }
           /* String[] memberIdString = StringUtils.split(memberIds, ",");
            List<String> memberIdList = Arrays.asList(memberIdString);*/
           //todo 函数式编程
            List<Integer> ids =Arrays.stream(memberIds.split(",")).map(s->Integer.parseInt(s.trim())).
                    collect(Collectors.toList());

            if (0==notification.getIsOfficial()){
                notifications.remove(notification);
            }
            if (!ids.contains(member.getId())){
                notifications.remove(notification);
            }
        }
        return Result.createBySuccess(notifications);
    }

    public Result getPushNotify(String uuidToken){
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if (member==null){
            return Result.createByErrorMessage("请重新登录");
        }
        Integer maxId = notificationMapper.selectMaxId();
        Notification notification = this.selectById(maxId);
        String memberIds = notification.getMemberId();
        if (StringUtils.equals("0",memberIds)){
            return Result.createBySuccess(notification);
        }
        //todo 函数式编程
        List<Integer> ids =Arrays.stream(memberIds.split(",")).map(s->Integer.parseInt(s.trim())).
                collect(Collectors.toList());
        if (0==notification.getIsOfficial()){
            return Result.createByErrorMessage("没有新推送");
        }

        if (!ids.contains(member.getId())){
           return Result.createByErrorMessage("没有新推送");
        }

        return Result.createBySuccess(notification);
    }
}
