package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.PointsConsumeRecord;
import com.stylefeng.guns.modular.system.dao.PointsConsumeRecordMapper;
import com.stylefeng.guns.modular.system.service.IPointsConsumeRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-25
 */
@Service
public class PointsConsumeRecordServiceImpl extends ServiceImpl<PointsConsumeRecordMapper, PointsConsumeRecord> implements IPointsConsumeRecordService {


    //公共方法
    public boolean isPayVideo(Integer vid,Integer memberId){
        Wrapper<PointsConsumeRecord> wrapper = new EntityWrapper<>();
        wrapper.eq("videoId",vid);
        wrapper.eq("memberId",memberId);
        //pointsConsumeRecordMapper.sele
        PointsConsumeRecord existPointsConsumeRecord = this.selectOne(wrapper);

        if(existPointsConsumeRecord!=null){
            return true;
        }
        return false;
    }
}
