package com.stylefeng.guns.modular.system.warpper.repo;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.PicturesCategoryMapper;
import com.stylefeng.guns.modular.system.model.PicturesCategory;
import io.swagger.annotations.Api;
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
public class PhotosAppSourceWarpper extends BaseControllerWarpper {

    public PhotosAppSourceWarpper(List<Map<String, Object>> list) {
        super(list);
    }


    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        //me工厂方法
        Long picturesCategoryId = (Long) map.get("picturesCategoryId");
        if (ToolUtil.isNotEmpty(picturesCategoryId)){
            PicturesCategory picturesCategory = ConstantFactory.me().getPicturesCategoryById(picturesCategoryId);
            Map<String, Object> picturesCategoryMap = BeanKit.beanToMap(picturesCategory);
            picturesCategoryMap.remove("id");
            picturesCategoryMap.remove("isDel");
            picturesCategoryMap.remove("gmtCreated");
            picturesCategoryMap.remove("gmtModified");
            map.putAll(picturesCategoryMap);
        }
    }

}
