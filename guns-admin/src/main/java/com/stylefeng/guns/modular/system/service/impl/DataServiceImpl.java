package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.dao.DataMapper;
import com.stylefeng.guns.modular.system.model.Video;
import com.stylefeng.guns.modular.system.service.IDataService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.vo.DataVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public List<Data> selectVideosByIds(List<Integer> videoIds){
        return dataMapper.selectVideosByIds(videoIds);
    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public List<Data> selectBatchVideoIds(List<Integer> vids){
        return this.selectBatchIds(vids);
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


    public List<DataVo> assemDataVoList(List<Data> dataList){
        if (CollectionUtils.isEmpty(dataList)){
            return null;
        }
        List<DataVo> dataVoList = Lists.newArrayList();
        for (Data data:dataList) {
            DataVo dataVo = new DataVo();
            BeanUtils.copyProperties(data,dataVo);
            dataVoList.add(dataVo);
        }
        return dataVoList;
    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public List<Integer> selectDataIds(){
        /*this.selectObjs()*/
        Wrapper<Data> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("v_id");
        List<Integer> dataIdList = ( List<Integer>)(List)this.selectObjs(wrapper);
        return dataIdList;
    }
}
