package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.system.model.Type;
import com.stylefeng.guns.modular.system.dao.TypeMapper;
import com.stylefeng.guns.modular.system.service.ITypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-26
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public String getTypeName(Wrapper wrapper){
        return (String)this.selectObj(wrapper);
    }

}
