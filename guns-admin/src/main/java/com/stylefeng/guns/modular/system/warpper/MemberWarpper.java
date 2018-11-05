package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class MemberWarpper extends BaseControllerWarpper {

    public MemberWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        //typeName
        //me工厂方法
        map.put("memberName", ConstantFactory.me().getMemberName((Integer) map.get("memberId")));
        map.put("appName", ConstantFactory.me().getAppName(Integer.valueOf((String) map.get("appId"))));
        map.put("appVerName", ConstantFactory.me().getAppVerName( (String)map.get("appVer")));
        map.put("channelName", ConstantFactory.me().getChannelName((String) map.get("channel")));
        if (map.get("channelId")!=null&& !StringUtils.equals("",(String)map.get("channelId"))) {
            map.put("channelName", ConstantFactory.me().getChannelName((String) map.get("channelId")));
        }
        map.put("videoName", ConstantFactory.me().getVideoName(String.valueOf((Integer)map.get("videoId"))));
    }

}
