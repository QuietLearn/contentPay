package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.Feedback;
import com.stylefeng.guns.modular.system.dao.FeedbackMapper;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private FeedbackMapper feedbackMapper;

    public Result<List<Feedback>> list(String uuidToken){
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        Wrapper<Feedback> wrapper = new EntityWrapper<>();
        wrapper.eq("memberId",member.getId());
        List<Feedback> feedbackList = this.selectList(wrapper);
        return Result.createBySuccess(feedbackList);
    }

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
        insertFeedback.setMemberId(member.getId());
        insertFeedback.setMemberName(member.getUsername());

        insertFeedback.setGmtCreated(new Date());
        insertFeedback.setGmtModified(new Date());

        insertFeedback.setFeedbackType(feedback.getFeedbackType());
        insertFeedback.setAppId(feedback.getAppId());
        insertFeedback.setAppVer(feedback.getAppVer());
        insertFeedback.setChannel(feedback.getChannel());
        insertFeedback.setInfo(feedback.getInfo());
        return insertFeedback;
    }
}
