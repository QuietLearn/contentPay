package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.LoginLog;
import com.stylefeng.guns.modular.system.model.MemberLoginLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员登录记录 Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-11-04
 */
public interface MemberLoginLogMapper extends BaseMapper<MemberLoginLog> {
    /**
     * 获取登录日志
     */
    List<Map<String, Object>> getLoginLogs(@Param("page") Page<LoginLog> page, @Param("beginTime") String beginTime,
                                           @Param("endTime") String endTime, @Param("logName") String logName, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

    Date selectMaxCreatime(Integer memberId);

    MemberLoginLog selectLoginLogByCreateTime(@Param("creatime") Date creatime, @Param("memberId") Integer memberId);
}
