package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Data;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-18
 */
public interface IDataService extends IService<Data> {
    //通过id查询
    Data getDataByVid(int vid);

    //批量查找
    List<Data> selectVideosByIds(List<Integer> videoIds);
    List<Data> selectBatchVideoIds(List<Integer> vids);


    List<Data> selectList();

    Page<Data> selectPageL(Page<Data> page);

    Page<Data> selectPageL(Page<Data> page, Wrapper<Data> wrapper);


}
