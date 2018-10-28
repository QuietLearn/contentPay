package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.PointsConsumeRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
public interface IPointsConsumeRecordService extends IService<PointsConsumeRecord> {
    boolean isPayVideo(Integer vid,Integer memberId);
}
