package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Feedback;
import com.stylefeng.guns.modular.system.dao.FeedbackMapper;
import com.stylefeng.guns.modular.system.service.IFeedbackService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
