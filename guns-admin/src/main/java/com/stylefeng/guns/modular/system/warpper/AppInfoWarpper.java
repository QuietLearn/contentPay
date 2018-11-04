package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import org.apache.commons.lang3.StringUtils;

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
//        getTypeName(map);
    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_GUNS)
    public void getAppInfo(Map<String, Object> map){
        if (map.get("appId") instanceof String){
            map.put("appName", ConstantFactory.me().getAppName(Integer.valueOf((String)map.get("appId"))));
        } else {
            map.put("appName", ConstantFactory.me().getAppName((Integer)map.get("appId")));
        }

        map.put("appVerName", ConstantFactory.me().getAppVerName( (String)map.get("appVer")));
        if (map.get("updateAppver")!=null&& !StringUtils.equals("",(String)map.get("updateAppver"))){
            map.put("updateAppverName", ConstantFactory.me().getAppVerName( (String)map.get("updateAppver")));
        }
        map.put("channelName", ConstantFactory.me().getChannelName((String) map.get("channel")));

        if (map.get("gender")!=null){
            map.put("genderName", ConstantFactory.me().getSexName((Integer) map.get("gender")));
        }

        if (map.get("memberid")!=null){
            map.put("memberName", ConstantFactory.me().getMemberName((Integer) map.get("memberid")));
        }

    }

    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public void getTypeName(Map<String, Object> map){
        map.put("typeName", ConstantFactory.me().getTypeName((Integer) map.get("typeId"))); //me工厂方法
    }
}
