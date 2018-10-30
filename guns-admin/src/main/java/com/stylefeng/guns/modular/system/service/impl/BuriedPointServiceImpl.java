package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.util.mobile.MobileInfoUtil;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.dao.BuriedPointMapper;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

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


    @Override
    public Result insertAssemBuriedPoint(BuriedPoint buriedPoint){
        BuriedPoint insertBuriedPoint = assemBuriedPoint(buriedPoint);
        boolean insert = this.insert(insertBuriedPoint);
        if (!insert ){
            return Result.createByErrorMessage("埋点失败");
        }

        return Result.createBySuccessMessage("埋点成功");
    }

    public BuriedPoint assemBuriedPoint(BuriedPoint buriedPoint){
        BuriedPoint insertBuriedPoint = new BuriedPoint();
        BeanUtils.copyProperties(buriedPoint,insertBuriedPoint);
        String mobileAreaInfo = MobileInfoUtil.getMobileAreaInfo(buriedPoint.getMobile());
        insertBuriedPoint.setAddress(mobileAreaInfo);
        insertBuriedPoint.setOperator(MobileInfoUtil.getMobileOperatorInfo(buriedPoint.getMobile()));
        insertBuriedPoint.setGmtCreated(new Date());
        insertBuriedPoint.setGmtModified(new Date());
        return insertBuriedPoint;
    }
}
