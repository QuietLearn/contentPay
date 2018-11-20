package com.stylefeng.guns.modular.system.warpper.repo;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.ModelRepositoryMapper;
import com.stylefeng.guns.modular.system.dao.PicturesCategoryMapper;
import com.stylefeng.guns.modular.system.model.ModelRepository;
import com.stylefeng.guns.modular.system.model.PicturesCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class ModelAppSourceWarpper extends BaseControllerWarpper {

    public ModelAppSourceWarpper(List<Map<String, Object>> list) {
        super(list);
    }


    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        //me工厂方法
        Long modelId = (Long) map.get("modelId");
        if (ToolUtil.isNotEmpty(modelId)){
            ModelRepository modelRepository = ConstantFactory.me().getModelRepositoryById(modelId);
            Map<String, Object> modelRepositoryMap = BeanKit.beanToMap(modelRepository);
            modelRepositoryMap.remove("id");
            modelRepositoryMap.remove("isDel");
            modelRepositoryMap.remove("gmtCreated");
            modelRepositoryMap.remove("gmtModified");
            map.putAll(modelRepositoryMap);
        }
    }

}
