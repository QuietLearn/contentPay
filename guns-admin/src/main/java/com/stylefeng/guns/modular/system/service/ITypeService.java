package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.Type;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-26
 */
public interface ITypeService extends IService<Type> {
    String getTypeName(Integer typeId);
}
