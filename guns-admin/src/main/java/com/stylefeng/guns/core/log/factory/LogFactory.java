package com.stylefeng.guns.core.log.factory;

import com.stylefeng.guns.core.common.constant.state.LogSucceed;
import com.stylefeng.guns.core.common.constant.state.LogType;
import com.stylefeng.guns.modular.system.model.LoginLog;
import com.stylefeng.guns.modular.system.model.MemberLoginLog;
import com.stylefeng.guns.modular.system.model.OperationLog;

import java.util.Date;

/**
 * 日志对象创建工厂
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午9:18:27
 */
public class LogFactory {

    /**
     * 创建操作日志
     */
    public static OperationLog createOperationLog(LogType logType, Integer userId,Integer errorSite, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserid(userId);
        operationLog.setErrorsite(errorSite);
        operationLog.setClassname(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreatetime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     */
    public static LoginLog createLoginLog(LogType logType, Integer userId, String msg, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogname(logType.getMessage());
        loginLog.setUserid(userId);
        loginLog.setCreatetime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }


    public static MemberLoginLog createMemberLoginLog(LogType logType, Integer memebrId, String msg, String ip,int appId,String appVer,String channel) {
        MemberLoginLog memberLoginLog = new MemberLoginLog();
        memberLoginLog.setLogname(logType.getMessage());
        memberLoginLog.setMemberid(memebrId);
        memberLoginLog.setCreatetime(new Date());
        memberLoginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        memberLoginLog.setIp(ip);
        memberLoginLog.setMessage(msg);

        memberLoginLog.setAppId(appId);
        memberLoginLog.setAppVer(appVer);
        memberLoginLog.setChannel(channel);


        return memberLoginLog;
    }

}
