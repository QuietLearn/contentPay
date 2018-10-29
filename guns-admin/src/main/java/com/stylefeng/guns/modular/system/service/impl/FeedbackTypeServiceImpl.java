package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.FeedbackType;
import com.stylefeng.guns.modular.system.dao.FeedbackTypeMapper;
import com.stylefeng.guns.modular.system.service.IFeedbackTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
@Service
public class FeedbackTypeServiceImpl extends ServiceImpl<FeedbackTypeMapper, FeedbackType> implements IFeedbackTypeService {


    public Result<List<FeedbackType>> getFeedbackType(){

        return Result.createBySuccess(this.selectList(null));
    }
}
