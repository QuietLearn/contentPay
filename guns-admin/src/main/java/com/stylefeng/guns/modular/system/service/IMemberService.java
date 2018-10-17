package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Member;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author hyj
 * @since 2018-10-15
 */
public interface IMemberService extends IService<Member> {

    /**
     * 注册功能
     * @param member
     * @return
     */
    Result<String> register(Member member);

    /**
     * 检查注册页面用户名和邮箱是否已存在
     * @param str
     * @param type
     * @return
     */
    Result<String> checkValid(String str, String type);

    /**
     * 忘记密码获取问题
     * @param username
     * @return
     */
    Result<String> forgetPassGetQuestion(String username);

    /**
     * 更新用户最近登录ip和时间
     * @param accountId
     * @return
     */
    Result updateGmtLastLoginAndLastIp(Integer accountId);

    List<Member> list(String condition);

    Result login(String username, String password);

    /**
     * 验证用户是否是vip
     * @param username
     * @return
     */
    Result checkVip(String username);
}
