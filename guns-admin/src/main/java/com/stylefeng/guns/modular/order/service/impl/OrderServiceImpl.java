package com.stylefeng.guns.modular.order.service.impl;

import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.dao.OrderMapper;
import com.stylefeng.guns.modular.order.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
