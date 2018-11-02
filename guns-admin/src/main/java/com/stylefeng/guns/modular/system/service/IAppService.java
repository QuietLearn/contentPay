package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.App;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
public interface IAppService extends IService<App> {
    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();
}
