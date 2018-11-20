package com.stylefeng.guns.modular.system.warpper.repo;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.PicturesCategoryMapper;
import com.stylefeng.guns.modular.system.dao.VideoRepositoryMapper;
import com.stylefeng.guns.modular.system.model.PicturesCategory;
import com.stylefeng.guns.modular.system.model.VideoRepository;
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
public class VideoAppSourceWarpper extends BaseControllerWarpper {

    public VideoAppSourceWarpper(List<Map<String, Object>> list) {
        super(list);
    }


    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        //me工厂方法
        Long videoId = (Long) map.get("videoId");
        if (ToolUtil.isNotEmpty(videoId)){
            VideoRepository videoRepository = ConstantFactory.me().getVideoRepositoryById(videoId);
            Map<String, Object> videoRepositoryMap = BeanKit.beanToMap(videoRepository);
            videoRepositoryMap.remove("id");
            videoRepositoryMap.remove("isDel");
            videoRepositoryMap.remove("gmtCreated");
            videoRepositoryMap.remove("gmtModified");
            map.putAll(videoRepositoryMap);
        }
    }

}
