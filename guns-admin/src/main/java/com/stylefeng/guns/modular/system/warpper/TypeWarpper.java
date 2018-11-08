package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.core.util.ToolUtil;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class TypeWarpper extends BaseControllerWarpper {

    public TypeWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        if (ToolUtil.isNotEmpty(map.get("typeId"))){
            map.put("typeName", ConstantFactory.me().getTypeName((Integer) map.get("typeId"))); //me工厂方法
        }
        if (ToolUtil.isNotEmpty(map.get("type"))){
            map.put("messageTypeName", AllConst.MessageEnum.valueOf((Integer) map.get("type"))); //me工厂方法
        }

    }

}
