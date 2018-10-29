package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.Favorite;
import com.stylefeng.guns.modular.system.model.Feedback;
import com.stylefeng.guns.modular.system.dao.FeedbackMapper;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;

/**
 * <p>
 * 回馈表 服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Autowired
    private MemberMapper memberMapper;


    public Result addFeedback(String uuidToken,Feedback feedback){
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }
        Feedback insertFeedback = assemFeedback(member, feedback);
        boolean insert = this.insert(insertFeedback);
        if (!insert){
            return Result.createByErrorMessage("反馈失败");
        }

        return Result.createBySuccessMessage("反馈成功");
    }

    public Feedback assemFeedback(Member member,Feedback feedback){
        Feedback insertFeedback = new Feedback();
        feedback.setMemberId(member.getId());
        feedback.setMemberName(member.getUsername());

        feedback.setFeedbackType(feedback.getFeedbackType());
        feedback.setAppId(feedback.getAppId());
        feedback.setAppVer(feedback.getAppVer());
        feedback.setChannel(feedback.getAppVer());
        feedback.setInfo(feedback.getInfo());
        return insertFeedback;
    }
}
