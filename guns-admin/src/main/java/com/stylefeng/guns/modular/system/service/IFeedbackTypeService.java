package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.FeedbackType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
public interface IFeedbackTypeService extends IService<FeedbackType> {

    Result<List<FeedbackType>> getFeedbackType();
}
