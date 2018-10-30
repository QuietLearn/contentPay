package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Active;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.BuriedPoint;

/**
 * <p>
 * 数据追踪-应用统计-激活统计表 服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
public interface IActiveService extends IService<Active> {

    Result insertAssemActive(BuriedPoint buriedPoint, String phoneType, String phoneBrand, String phoneSystem, String dpi);
}
