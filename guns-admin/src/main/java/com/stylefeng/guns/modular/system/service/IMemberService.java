package com.stylefeng.guns.modular.system.service;

import com.aliyuncs.exceptions.ClientException;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Member;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.vo.MemberVo;

import javax.servlet.http.HttpSession;
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
     * @param mobile
     * @param password
     * @return
     */
    Result<MemberVo> register(String mobile, String password, String message);

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

    /**
     * 根据条件列表
     * @param condition
     * @return
     */
    List<Member> list(String condition);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Result login(String username, String password);

    /**
     * 根据电话登录
     * @param mobile
     * @param password
     * @return
     */
    Result loginByMobile(String mobile, String password);

    /**
     * 检测用户是否是vip
     * @param UuidToken
     * @param vid
     * @return
     */
    Result checkVip(String UuidToken,int vid);


    /*Result getMessage(String mobile);*/
    /**
     * 获取短信验证码
     * @param mobile
     * @return
     */
    Result getMessage2(String mobile) throws ClientException, InterruptedException;
}
