package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.App;
import com.stylefeng.guns.modular.system.dao.AppMapper;
import com.stylefeng.guns.modular.system.service.IAppService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements IAppService {

    @Override
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }
}
