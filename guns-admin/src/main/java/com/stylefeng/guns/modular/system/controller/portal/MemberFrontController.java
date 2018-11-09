package com.stylefeng.guns.modular.system.controller.portal;

import com.aliyuncs.exceptions.ClientException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.TokenCache;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.log.factory.LogTaskFactory;
import com.stylefeng.guns.core.log.factory.MemberLogTaskFactory;
import com.stylefeng.guns.modular.system.service.IMemberService;
import com.stylefeng.guns.modular.system.vo.MemberVo;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.stylefeng.guns.core.support.HttpKit.getIp;


/**
 * Created by hyj on 2018/10/15
 */
@Api(description = "用户接口")
@RestController
@RequestMapping("/front/member")
public class MemberFrontController extends BaseController {

    @Autowired
    private IMemberService memberService;

    /**
     * 注册会员 除了前端表单的信息也需要设置默认的信息
     */
    //responseContainer = "Item"
    @ApiOperation(value = "用户手机注册",notes = "用户手机注册",response = Result.class,produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "message", value = "短信", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "appVer", value = "应用版本", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "channel", value = "渠道号", required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            /*@ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 1, message = "失败"),*/
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<MemberVo> register(String mobile, String password, String message,Integer appId,String appVer,String channel) {

        return memberService.register(mobile,password,message,appId,appVer,channel);
    }

    /**
     * 检测用户名或者邮箱是否已用
     * @param str  具体内容
     * @param type username还是email
     * @return
     */
    @ApiOperation(value = "检测用户名或者邮箱、电话是否已用",notes = "检测用户名或者邮箱、电话是否已用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "具体内容", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "Integer", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 1, message = "失败"),
            @ApiResponse(code = 500, message = "服务器错误")})

    @RequestMapping(value = "/checkValid",method = RequestMethod.GET)
    public Result<String> checkValid(String str,String type,Integer appId){
        return memberService.checkValid(str,type,appId);
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(String username ,String password,Integer appId){
        return memberService.login(username,password,appId);
    }

    @ApiOperation(value = "手机登录",notes = "手机登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "appVer", value = "应用版本", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "channel", value = "渠道号", required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/login_use_mobile",method = RequestMethod.POST)
    public Result loginByMobile(String mobile,String password,Integer appId,String appVer,String channel){
        Result<MemberVo> memberVoResult = memberService.loginByMobile(mobile, password, appId);
        MemberVo memberVo = memberVoResult.getData();
        if (memberVo!=null){
            //传递task即可记录，不会占用业务时间,所以不直接用insert
            LogManager.me().executeLog(MemberLogTaskFactory.memberLoginLog(memberVo.getId(), getIp(),appId, appVer,channel));
        }

        return memberVoResult;
    }

    @ApiOperation(value = "用户检测是否是vip",notes = "用户检测是否是vip")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "uuid唯一识别码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "vid", value = "视频id", required = true, dataType = "int", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "/checkVip",method = RequestMethod.POST)
    public Result checkVip(@RequestParam(defaultValue = "0") String uuidToken, int vid){
        return memberService.checkVip(uuidToken,vid);
    }

    @ApiOperation(value = "获取短信",notes = "获取短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "密码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "appVer", value = "应用版本", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "channel", value = "渠道号", required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value="/getMessage",method = RequestMethod.GET)
    public Result getMessage(String mobile,String type,Integer appId,String appVer,String channel) throws ClientException, InterruptedException {
        return memberService.getMessage2(mobile,type,appId,appVer,channel);
    }

    @ApiOperation(value = "重置密码",notes = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "message", value = "短信", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "Integer", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value="/reset_password",method = RequestMethod.POST)
    public Result resetPassword(String mobile,String message,String newPassword,Integer appId){
        return memberService.resetPassword(mobile,message,newPassword,appId);
    }

    @ApiOperation(value = "获取uuidToken状态",notes = "获取uuidToken状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "uuid唯一识别码", required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "get_uuid_validity",method = RequestMethod.GET)
    public Result getUuidValidity(String uuidToken){
        return memberService.getUuidValidity(uuidToken);
    }

    @ApiOperation(value = "更新用户信息",notes = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "短信（在修改用户手机时需要验证短信才能修改）", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "safeUuid", value = "验证用户解绑手机验证码正确与否", required = false, dataType = "string", paramType = "query")
    })

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "update_user_info",method = RequestMethod.POST)

    public Result updateUserInfo(MemberVo alterMember,String message,String safeUuid){
        return memberService.updateUserInfo(alterMember,message,safeUuid);
    }

    @ApiOperation(value = "短信验证（用户解绑手机时）",notes = "短信验证（用户解绑手机时）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "mobile", value = "手机", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "message", value = "短信", required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "verify_message",method = RequestMethod.POST)
    Result verifyMessageResult(String uuidToken,String mobile,String message){
        return memberService.verifyMessageResult(uuidToken,mobile,message);
    }

    @ApiOperation(value = "根据对应视频扣除用户金币（积分）",notes = "www.relaxstudy.top/front/member/deducted_goin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vid", value = "视频id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "uuidToken", value = "用户唯一识别码", required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")})
    @RequestMapping(value = "deducted_goin",method = RequestMethod.POST)
    Result deductedGoin(int vid,String uuidToken){
        return memberService.deductedGoin(vid,uuidToken);
    }
}
