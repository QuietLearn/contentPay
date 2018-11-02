package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.App;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
public interface AppMapper extends BaseMapper<App> {
    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();
}
