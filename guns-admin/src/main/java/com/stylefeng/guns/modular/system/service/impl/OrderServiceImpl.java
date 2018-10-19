package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.common.result.ResultCode;
import com.stylefeng.guns.modular.system.dao.VipPriceMapper;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.dao.OrderMapper;
import com.stylefeng.guns.modular.system.model.VipPrice;
import com.stylefeng.guns.modular.system.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private VipPriceMapper vipPriceMapper;

    public Result<OrderVo> generateOrderVo(Integer vipTypeId){
        if (vipTypeId==null){
            return Result.createByErrorCodeMessage(ResultCode.ILLEAGAL_ARGUMENT.getCode(),ResultCode.ILLEAGAL_ARGUMENT.getDesc());
        }
        //取出vip价格表中对应的价格
        VipPrice vipPrice = vipPriceMapper.selectById(vipTypeId);
        if (vipPrice==null){
            return Result.createByErrorMessage("该vip价格不存在");
        }


        long orderNo = this.generateOrderNo();

        return null;
    }

    /**
     * 生成orderNo的策略
     * @return
     */
    private long generateOrderNo(){
        long currentTimeMillis = System.currentTimeMillis();
        long orderNo = currentTimeMillis + new Random().nextInt(100);
        return orderNo;
    }
}

