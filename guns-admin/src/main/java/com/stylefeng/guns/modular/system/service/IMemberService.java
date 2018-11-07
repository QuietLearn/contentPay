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
     * 后台批量删除功能
     * @param ids
     * @return
     */
    long deleteMemberList(String ids);

    /**
     * 注册功能
     * @param mobile
     * @param password
     * @return
     */
    Result register(String mobile,String password,String message,Integer appId,String appVer,String channel);

    /**
     * 检查注册页面用户名和邮箱是否已存在
     * @param str
     * @param type
     * @return
     */
    Result<String> checkValid(String str, String type,Integer appId);

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
    Result login(String username, String password,Integer appId);

    /**
     * 根据电话登录
     * @param mobile
     * @param password
     * @return
     */
    Result<MemberVo>  loginByMobile(String mobile, String password,Integer appId);

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
     * @param type
     * @param appId
     * @param appVer
     * @param channel
     * @return
     * @throws ClientException
     * @throws InterruptedException
     */
    Result getMessage2(String mobile,String type,Integer appId,String appVer,String channel) throws ClientException, InterruptedException;

    /**
     * 忘记密码确认短信
     * @param mobile
     * @param message
     * @return
     */
    Result resetPassword(String mobile,String message,String password,Integer appId);

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
    Result updateUserInfo(MemberVo alterMember,String message,String safeUuid);

    /**
     * 验证短信
     * @param uuidToken
     * @param mobile
     * @param message
     * @return
     */
    Result verifyMessageResult(String uuidToken,String mobile,String message);

    /**
     * 扣除用户视频相应金币
     * @param vid
     * @param uuidToken
     * @return
     */
    Result deductedGoin(int vid,String uuidToken);
}
