package com.stylefeng.guns.core.common.constant.dictmap;

import com.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * @author wangyang
 * @date 2018/10/9 10:30
 **/
public class OrderDict extends AbstractDictMap {
    @Override
    public void init() {
        put("id", "订单id");
        put("user", "下单人");
        put("place", "地点");
        put("goods", "货物名称");
        put("createtime", "下单时间");
    }
    @Override
    protected void initBeWrapped() {
    }
}
