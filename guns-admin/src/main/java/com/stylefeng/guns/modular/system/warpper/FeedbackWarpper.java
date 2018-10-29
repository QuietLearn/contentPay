package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class FeedbackWarpper extends BaseControllerWarpper {

    public FeedbackWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        map.remove("gmtModified");
        map.remove("id");
        map.remove("isDel");

        map.remove("appId");
        map.remove("appVer");
        map.remove("channel");

        map.remove("memberId");
        map.remove("memberName");

        map.remove("feedbackType");

        map.put("feedbackTypeName", ConstantFactory.me().getFeedbackTypeName((String)map.get("feedbackType"))); //me工厂方法
    }

}
