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
    /**
     * 检测是否是已付费视频
     * @param vid
     * @param memberId
     * @return
     */
    boolean isPayVideo(Integer vid,Integer memberId);
}
