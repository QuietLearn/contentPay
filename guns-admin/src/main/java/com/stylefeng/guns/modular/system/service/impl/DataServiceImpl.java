package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.dao.DataMapper;
import com.stylefeng.guns.modular.system.service.IDataService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-18
 */
@Service

public class DataServiceImpl extends ServiceImpl<DataMapper, Data> implements IDataService {

    @Autowired
    private DataMapper dataMapper;

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public Data getDataByVid(int vid){
        Data data = dataMapper.selectById(vid);
        return data;
    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public List<Data> selectList(){
        return this.selectList(null);
    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public Page<Data> selectPageL(Page<Data> page){
        return this.selectPage(page);
    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public Page<Data> selectPageL(Page<Data> page, Wrapper<Data> wrapper){
        return this.selectPage(page,wrapper);
    }
}
