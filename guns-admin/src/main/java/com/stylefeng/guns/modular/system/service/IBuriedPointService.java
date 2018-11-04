package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 埋点 服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
public interface IBuriedPointService extends IService<BuriedPoint> {

    /**
     *
     * @param buriedPoint
     * @return
     */
    Result insertAssemBuriedPoint(BuriedPoint buriedPoint);

    List<ZTreeNode> tree();

}
