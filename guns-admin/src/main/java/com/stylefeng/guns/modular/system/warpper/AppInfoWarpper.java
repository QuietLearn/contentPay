package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class AppInfoWarpper extends BaseControllerWarpper {

    public AppInfoWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        getAppInfo(map);
        getTypeName(map);
    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_GUNS)
    public void getAppInfo(Map<String, Object> map){
        map.put("appName", ConstantFactory.me().getAppName((Integer)map.get("appId")));
        map.put("appVerName", ConstantFactory.me().getAppVerName( (String)map.get("appVer")));
        map.put("channelName", ConstantFactory.me().getChannelName((String) map.get("channel")));
        map.put("genderName", ConstantFactory.me().getSexName((Integer) map.get("gender")));
    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public void getTypeName(Map<String, Object> map){
        map.put("typeName", ConstantFactory.me().getTypeName((Integer) map.get("typeId"))); //me工厂方法
    }
}
