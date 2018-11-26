package com.stylefeng.guns.modular.system.warpper.repo;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.PicturesCategory;
import com.stylefeng.guns.modular.system.model.VideoSource;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class PlaysourceWarpper extends BaseControllerWarpper {

    public PlaysourceWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        //me工厂方法
        Integer videoSourceId = (Integer) map.get("videoSourceid");
        Integer isCheck = (Integer) map.get("isCheck");
        if (ToolUtil.isNotEmpty(videoSourceId)){
            String videoSourceName = ConstantFactory.me().getVideoSourceNameById(videoSourceId);
            map.put("videoSourceName",videoSourceName);
        }
        if (ToolUtil.isNotEmpty(isCheck)){
            if(isCheck==0){
                map.put("isCheckRemark","不选用");
            } else {
                map.put("isCheckRemark","选中");
            }
        }
    }

}
