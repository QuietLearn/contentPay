package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.util.mobile.MobileInfoUtil;
import com.stylefeng.guns.modular.system.model.Active;
import com.stylefeng.guns.modular.system.dao.ActiveMapper;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IActiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 数据追踪-应用统计-激活统计表 服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
@Service
public class ActiveServiceImpl extends ServiceImpl<ActiveMapper, Active> implements IActiveService {

    @Autowired
    private IBuriedPointService buriedPointService;

    public Result insertAssemActive(BuriedPoint buriedPoint,String phoneType,String phoneBrand,String phoneSystem,String dpi){

        Active insertActive = assemActive(buriedPoint,phoneType,phoneBrand,phoneSystem,dpi);



        Wrapper<Active> wrapper = new EntityWrapper<>();
        wrapper.eq("IMEI",insertActive.getImei());
        wrapper.eq("app_id",insertActive.getAppId());

        int count = this.selectCount(wrapper);
        if (count>0){
            return Result.createBySuccessMessage("该用户已激活");
        }

        boolean insert = this.insert(insertActive);
        if (!insert ){
            return Result.createByErrorMessage("激活失败");
        }

        buriedPointService.insertAssemBuriedPoint(buriedPoint);

        return Result.createBySuccessMessage("激活成功");
    }

    public Active assemActive(BuriedPoint buriedPoint,String phoneType,String phoneBrand,String phoneSystem,String dpi){
        Active insertActive = new Active();
        BeanUtils.copyProperties(buriedPoint,insertActive);
        insertActive.setPhoneType(phoneType);
        insertActive.setPhoneBrand(phoneBrand);
        insertActive.setPhoneSystem(phoneSystem);
        insertActive.setDpi(dpi);
        String mobileAreaInfo = MobileInfoUtil.getMobileAreaInfo(buriedPoint.getMobile());
        insertActive.setAddress(mobileAreaInfo);

        insertActive.setOperator(MobileInfoUtil.getMobileOperatorInfo(buriedPoint.getMobile()));

        insertActive.setGmtCreated(new Date());
        insertActive.setGmtModified(new Date());
        return insertActive;
    }
}
