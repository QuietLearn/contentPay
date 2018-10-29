package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.dao.BuriedPointMapper;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 埋点 服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
@Service
public class BuriedPointServiceImpl extends ServiceImpl<BuriedPointMapper, BuriedPoint> implements IBuriedPointService {


    public Result insertAssemBuriedPoint(BuriedPoint buriedPoint){
        BuriedPoint insertBuriedPoint = assemBuriedPoint(buriedPoint);
        boolean insert = this.insert(insertBuriedPoint);
        if (!insert ){
            return Result.createByErrorMessage("埋点统计失败");
        }

        return Result.createBySuccessMessage("埋点统计成功");
    }

    public BuriedPoint assemBuriedPoint(BuriedPoint buriedPoint){
        BuriedPoint insertBuriedPoint = new BuriedPoint();
        BeanUtils.copyProperties(buriedPoint,insertBuriedPoint);
        String mobile = buriedPoint.getMobile();
        return insertBuriedPoint;
    }
}
