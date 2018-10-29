package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Active;
import com.stylefeng.guns.modular.system.dao.ActiveMapper;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IActiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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


    public Result insertAssemActive(Active active){
        Active insertActive = assemActive(active);
        boolean insert = this.insert(insertActive);
        if (!insert ){
            return Result.createByErrorMessage("激活统计失败");
        }

        return Result.createBySuccessMessage("激活统计成功");
    }

    public Active assemActive(Active active){
        Active insertActive = new Active();
        BeanUtils.copyProperties(active,insertActive);
        String mobile = active.getMobile();
        insertActive.setGmtCreated(new Date());
        insertActive.setGmtModified(new Date());
        return insertActive;
    }
}
