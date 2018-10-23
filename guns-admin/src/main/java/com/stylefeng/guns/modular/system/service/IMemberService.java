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
     * @param uuidToken
     * @param vid
     * @return
     */
    Result checkVip(String uuidToken,int vid);


    /*Result getMessage(String mobile);*/
    /**
     * 获取短信验证码
     * @param mobile
     * @return
     */
    Result getMessage2(String mobile,String type) throws ClientException, InterruptedException;

    /**
     * 忘记密码确认短信
     * @param mobile
     * @param message
     * @return
     */
    Result resetPassword(String mobile,String message,String password);

    /**
     * 重置用户密码
     * @param mobile
     * @param password
     * @param token
     * @return
     */
    //Result resetPassword(String mobile,String password,String token);

    /**
     * 获取uuid对应状态
     * @param uuidToken
     * @return
     */
    Result getUuidValidity(String uuidToken);

    /**
     * 更新用户信息
     * @param alterMember
     * @param message
     * @return
     */
    Result updateUserInfo(MemberVo alterMember,String message);

    /**
     * 验证短信
     * @param mobile
     * @param message
     * @return
     */
    Result verifyMessageResult(String mobile,String message);
}
