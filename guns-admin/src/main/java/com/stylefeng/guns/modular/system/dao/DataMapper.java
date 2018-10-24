package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Data;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-18
 */
public interface DataMapper extends BaseMapper<Data> {

    List<Data> selectVideosByIds(@Param("videoIds") List<Integer> videoIds);
}
