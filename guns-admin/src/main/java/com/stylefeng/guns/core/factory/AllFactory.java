package com.stylefeng.guns.core.factory;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.modular.system.model.*;

import java.util.Date;

/**
 * Created by hyj on 2018/10/29
 */
public class AllFactory {

    public static Note createNote(String message, String mobile,Integer appId,String appVer,String channel){
        Note note = new Note();
        note.setAging(AllConst.timeout);
        note.setIsDel(1);
        note.setMessage(message);
        note.setMobile(mobile);

        note.setAppId(appId);
        note.setAppVer(appVer);
        note.setChannel(channel);

        note.setGmtCreated(new Date());
        note.setGmtUpdated(new Date());

        return note;
    }

    public static PointsConsumeRecord assemPointsConsumeRecord(Video video,Integer points, Member member, MemberLoginLog memberLoginLog){
        PointsConsumeRecord pointsConsumeRecord = new PointsConsumeRecord();
        pointsConsumeRecord.setGmtCreated(new Date());
        pointsConsumeRecord.setGmtModified(new Date());
        if (video!=null){
            pointsConsumeRecord.setPoints("-"+video.getvMoney());
            //todo 获得视频名的表可能需要改动
            StringBuilder sb = new StringBuilder();
            String pointWaterGenerateReason = sb.append("观看《").append(ConstantFactory.me().getVideoName(String.valueOf(video.getvId()))) + "》".toString();
            pointsConsumeRecord.setReason(pointWaterGenerateReason);
        } else {
            pointsConsumeRecord.setPoints("+"+points);
            pointsConsumeRecord.setReason("签到");
        }

        pointsConsumeRecord.setMemberId(member.getId());
        pointsConsumeRecord.setVideoId(video.getvId());


        pointsConsumeRecord.setIsDel(1);

        pointsConsumeRecord.setAppId(memberLoginLog.getAppId());
        pointsConsumeRecord.setAppVer(memberLoginLog.getUpdateAppver());
        pointsConsumeRecord.setChannel(memberLoginLog.getChannel());
        return pointsConsumeRecord;
    }


    public static Email assemEmail(Integer memberId,String emailString,Integer appId){
        Email email = new Email();
        email.setMemberId(memberId);
        email.setEmail(emailString);
        email.setActive(0);
        email.setAppId(appId);
        email.setGmtCreated(new Date());
        email.setGmtModified(new Date());
        return email;
    }
}
