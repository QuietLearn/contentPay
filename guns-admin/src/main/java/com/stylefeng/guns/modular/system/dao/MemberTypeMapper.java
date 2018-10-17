package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.MemberType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 会员购买详情表_待定（一种商品/付费类型） Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-15
 */
public interface MemberTypeMapper extends BaseMapper<MemberType> {

    String findNameByCode(int id);
}
