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
public class FeedbackBackWarpper extends BaseControllerWarpper {

    public FeedbackBackWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        if (ToolUtil.isNotEmpty(map.get("feedback_type"))){
            map.put("feedbackTypeName", ConstantFactory.me().getFeedbackTypeName((String) map.get("feedback_type"))); //me工厂方法
        }

    }

}
