package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Feedback;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 回馈表 服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
public interface IFeedbackService extends IService<Feedback> {

    Result addFeedback(String uuidToken, Feedback feedback);

    Result<List<Feedback>> list(String uuidToken);
}
