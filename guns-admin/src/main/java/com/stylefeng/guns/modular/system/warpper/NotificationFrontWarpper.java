package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.state.AllConst;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class NotificationFrontWarpper extends BaseControllerWarpper {

    public NotificationFrontWarpper(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        //me工厂方法
        map.remove("isOfficial");
        map.remove("id");
        map.remove("isDel");

        map.remove("memberId");
        map.remove("picAddress");

        map.remove("creater");
        map.remove("gmtCreated");
        map.remove("gmtModified");
        map.remove("type");
    }

}
