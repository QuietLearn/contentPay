package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 埋点 Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-11-02
 */
public interface BuriedPointMapper extends BaseMapper<BuriedPoint> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();
}
