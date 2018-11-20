package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.ModelRepository;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-11-19
 */
public interface ModelRepositoryMapper extends BaseMapper<ModelRepository> {

    List<ModelRepository> selectListByContentId(Long id);

}
