package com.stylefeng.guns.modular.system.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.stylefeng.guns.config.datasource.MultiDataSourceConfig;
import com.stylefeng.guns.config.properties.SaltProperties;
import com.stylefeng.guns.core.common.TokenCache;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.core.common.constant.state.ServerType;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.config.properties.MutiDataSourceProperties;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.core.util.meassge.IndustrySMS;
import com.stylefeng.guns.core.util.meassge.SendSMSUtilLZ;
import com.stylefeng.guns.modular.system.dao.DataMapper;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.dao.MemberTypeMapper;
import com.stylefeng.guns.modular.system.dao.NoteMapper;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Note;
import com.stylefeng.guns.modular.system.service.IDataService;
import com.stylefeng.guns.modular.system.service.IMemberService;
import com.stylefeng.guns.modular.system.vo.MemberVo;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.stylefeng.guns.core.common.constant.DatasourceEnum.DATA_SOURCE_GUNS;

/**
 * <p>
 * 会员表 服务实现类 可以参照app抓取小说项目的account逻辑
 * </p>
 *
 * @author hyj
 * @since 2018-10-15
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberTypeMapper memberTypeMapper;
    
    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private IDataService dataService;

    @Autowired
    public SaltProperties saltProperties;




    //后台逻辑方法
    public List<Member> list(String condition){
        List<Member> members = this.selectList(null);
        for (Member member :members) {
            member.setMobile(addHidePhone(member.getMobile()));
            member.setEmail(addHideEmail(member.getEmail()));
        }
        return members;
    }


    //前台逻辑方法
    //注册
    public Result<MemberVo> register(String mobile,String password,String message) {
        Member existMember = memberMapper.selectMemberByMobile(mobile);
        if (existMember!=null){
            logger.info("这个手机号还没注册过哦，请先去注册一下吧");
            return Result.createByErrorMessage("这个手机号还没注册过哦，请先去注册一下吧");
        }
        if (!verifyMessage(mobile,message)){
            return Result.createByErrorMessage(AllConst.MESSAGE_ERROR_MSG);
        }

        Member member = new Member();
        member.setMobile(mobile);
        member.setPassword(password);

        /*Result<String> result = this.checkValid(member.getUsername(), Const.USERNAME);
        if (!result.isSuccess()){
            return result;
        }
        result = this.checkValid(member.getEmail(), Const.EMAIL);
        if (!result.isSuccess()){
            return result;
        }*/

        //ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //设置用户名
        String username = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        username = username + "-mo";
        member.setUsername(username);

        //获取HttpServletRequest   HttpKit.getRequest();
        HttpServletRequest request = HttpKit.getRequest();
        //设置获取到的请求方ip
        member.setRegisterIp(request.getRemoteAddr());
        //设置md5加密以及盐值
        member.setMd5Password(MD5Util.encrypt(member.getPassword()+saltProperties.getSalt1()));
        //设置会员购买类型和会员等级
        member.setMemberTypeId(ServerType.REGULAR.getCode());
        member.setMemberTypeName(memberTypeMapper.findNameByCode(ServerType.REGULAR.getCode()));
        member.setVipLevel(0);
        //设置积分
        member.setPoints(0);
        //设置用户等级和经验
        //设置注册时间
        member.setRegisterTime(new Date());
        //设置创建时间以及修改时间
        member.setGmtCreated(new Date());
        member.setGmtModified(new Date());

        //设置用户登录的uuid防止多端登录，已经登录的话就用以前的uuid访问用户数据
        String uuidString = UUID.randomUUID().toString().replaceAll("-", "");
        member.setUuidToken(uuidString);

        //重要，签到的逻辑
        /*if (flag > 0) {
            Sign sign = signService.selectByTodayAndAccountId(account
                    .getId());
            if (sign != null) {
                accountUser.setSignCount(sign.getCount());
            }
        }*/
        boolean result = this.insert(member);

        if (!result){
            return Result.createBySuccessMessage("注册失败");
        }

        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(member,memberVo);


        return Result.createBySuccess("注册成功",memberVo);
    }


    public Result login(String username,String password){
        int userNum = memberMapper.checkName(username);
        if (userNum==0){
            return Result.createByErrorMessage("用户名不存在");
        }
        String md5Password = MD5Util.encrypt(password+saltProperties.getSalt1());
        Member member = memberMapper.login(username,md5Password);
        if (member==null){
            return Result.createByErrorMessage("密码错误");
        }
        String uuidString = UUID.randomUUID().toString().replaceAll("-", "");
        member.setUuidToken(uuidString);
        Integer updateCount = memberMapper.updateUuidById(member);
        if (updateCount<=0){
            logger.error("更新失败，更新数据库行：{}",updateCount);
        }
        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(member,memberVo);

        updateGmtLastLoginAndLastIp(member.getId());

        return Result.createBySuccess("登录成功",memberVo);
    }


    public Result loginByMobile(String mobile, String password){
        Member existMember = memberMapper.selectMemberByMobile(mobile);
        if (existMember==null){
            return Result.createByErrorMessage("这个手机号还没注册过哦，请先去注册一下吧");
        }
        //MD5加密 加盐，
        String md5Password = MD5Util.encrypt(password+saltProperties.getSalt1());
        //根据手机密码登录
        Member member = memberMapper.loginByMobile(mobile,md5Password);
        if (member==null){
            return Result.createByErrorMessage("密码错误");
        }

        //设置用户登录的uuid防止多端登录，已经登录的话就用以前的uuid访问用户数据
        String uuidString = UUID.randomUUID().toString().replaceAll("-", "");
        member.setUuidToken(uuidString);
        Integer updateCount = memberMapper.updateUuidById(member);
        if (updateCount<=0){
            logger.error("更新失败，更新数据库行：{}",updateCount);
        }

        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(member,memberVo);

        updateGmtLastLoginAndLastIp(member.getId());
        return Result.createBySuccess("登录成功",memberVo);
    }


    @DataSource(name = DatasourceEnum.DATA_SOURCE_GUNS)
    @Transactional
    public Member getMemberByUuidtoken(String uuidToken){
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        return member;
    }

    //判断是否是vip

    public Result checkVip(String uuidToken,int vid){

        if (StringUtils.isBlank(uuidToken)){
            return Result.createByErrorMessage("用户需要登录");
        }

        Data data = dataService.getDataByVid(vid);
        if (data==null){
            return Result.createByErrorMessage("视频不存在");
        }
        Member member = this.getMemberByUuidtoken(uuidToken);
        if (member==null){
            return Result.createByErrorMessage("token失效，请重新登录");
        }

        if (AllConst.VideoReqVip.NO_NEED_VIP==data.getvReqVip()){
            return Result.createBySuccessMessage("欢迎观看");
        } else if(AllConst.VideoReqVip.NEED_VIP==data.getvReqVip()){
            if (ServerType.VIP.getCode()==member.getMemberTypeId()){
                return Result.createBySuccessMessage("欢迎观看");
            }
            return Result.createByErrorMessage("请充值会员后观看");
        }

        return Result.createBySuccessMessage("欢迎观看");
    }

    //注册获取短信1
    /*
    public Result getMessage(String mobile){

        int i = noteMapper.selectSendMobileNoteNum(mobile);
        logger.info("同手机超过1分钟发送短信的次数:{}",i);
        if (i>=1){
            return Result.createByErrorMessage("1分钟内发送短信不能超过1条");
        }
        //发送短信
        Result result = IndustrySMS.execute(mobile);
        //
        //如果mobile非空获取短信码存入缓存中
        if (result.isSuccess()){
            String message = (String)result.getData();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+mobile,message);


            Note note = new Note();
            note.setAging(AllConst.timeout);
            note.setIsDel(1);
            note.setMessage(message);
            note.setMobile(mobile);


            note.setGmtCreated(new Date());
            note.setGmtUpdated(new Date());
            noteMapper.insert(note);

        }
        return result;
    }*/

    //2
    public Result getMessage2(String mobile) throws ClientException, InterruptedException {

        if(StringUtils.isBlank(mobile)){
            logger.info("手机号为空，请重新传值");
            return null;
        }

        int i = noteMapper.selectSendMobileNoteNum(mobile);
        logger.info("同手机超过1分钟发送短信的次数:{}",i);
        if (i>=1){
            return Result.createByErrorMessage("1分钟内发送短信不能超过1条");
        }

        SendSMSUtilLZ sendSMSUtilLZ = new SendSMSUtilLZ(mobile);
        Result result = sendSMSUtilLZ.main("1000");

        //如果mobile非空获取短信码存入缓存中
        if (result.isSuccess()){
            String message = (String)result.getData();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+mobile,message);
            logger.info("Token mobile message:{}", TokenCache.getKey(TokenCache.TOKEN_PREFIX+mobile));

            Note note = new Note();
            note.setAging(AllConst.timeout);
            note.setIsDel(1);
            note.setMessage(message);
            note.setMobile(mobile);


            note.setGmtCreated(new Date());
            note.setGmtUpdated(new Date());
            noteMapper.insert(note);

        }

        result.setData(null);
        return result;
    }


    public Result resetPassword(String mobile,String message,String password){
        Member member = memberMapper.selectMemberByMobile(mobile);
        if (member==null){
            return Result.createByErrorMessage("这个手机号还没注册过哦，请先去注册一下吧");
        }
        if (!verifyMessage(mobile,message)){
            return Result.createByErrorMessage(AllConst.MESSAGE_ERROR_MSG);
        }

        String md5Password = MD5Util.encrypt(password+saltProperties.getSalt1());
        int updateCount = memberMapper.updatePasswordByMobile(mobile,password,md5Password);
        if (updateCount>0){
            return Result.createBySuccessMessage("密码修改成功");
        }
        //防止其他人直接调用修改密码接口
        /*String safeUuid = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKEN_SAFE+mobile,safeUuid);*/

        return Result.createByErrorMessage("密码修改错误");
    }

    public Result updateUserInfo(MemberVo alterMember,String message){
        if (StringUtils.isBlank(alterMember.getUuidToken())){
            return Result.createByErrorMessage("请重新登录");
        }
        Member member = memberMapper.selectMemberByUuidToken(alterMember.getUuidToken());
        if (member==null){
            return Result.createByErrorMessage("请重新登录");
        }
        int resultCount = memberMapper.checkEmailByUserId(alterMember.getEmail(),alterMember.getId());
        if (resultCount>0){
            return Result.createByErrorMessage("该邮箱已被使用");
        }
        resultCount = memberMapper.checkMobileByUserId(alterMember.getMobile(),alterMember.getId());
        if (resultCount>0){
            return Result.createByErrorMessage("该手机已被使用");
        }
        resultCount = memberMapper.checkUsernameByUserId(alterMember.getMobile(),alterMember.getId());
        if (resultCount>0){
            return Result.createByErrorMessage("该昵称已被使用");
        }

        if (StringUtils.isNotBlank(message)){
            if (!verifyMessage(alterMember.getMobile(),message)){
                return Result.createByErrorMessage(AllConst.MESSAGE_ERROR_MSG);
            }
        }

        Member updateMember = new Member();
        BeanUtils.copyProperties(alterMember,updateMember);
        updateMember.setId(member.getId());
        Integer updateCount = memberMapper.updateById(updateMember);

        //重新获取数据库中已经修改过的用户数据
        Member member1 = memberMapper.selectById(member.getId());
        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(member1,memberVo);
        if (updateCount>0){
            //user.setPassword(StringUtils.EMPTY);//密码设为空，不暴露出去
            return  Result.createBySuccess("用户信息更新成功",memberVo);
        }
        return Result.createByErrorMessage("用户信息更新失败");
    }


    //公用方法，用于验证短信是否正确
    public boolean verifyMessage(String mobile,String message){
        String smscode = TokenCache.getKey(TokenCache.TOKEN_PREFIX + mobile);
        return StringUtils.equals(smscode,message);

    }

    public Result verifyMessageResult(String mobile,String message){
        if (!verifyMessage(mobile,message)){
            return Result.createByErrorMessage(AllConst.MESSAGE_ERROR_MSG);
        }
        return Result.createBySuccessMessage("验证通过");
    }

    /*public Result resetPassword(String mobile,String password,String token){
        Member member = memberMapper.selectMemberByMobile(mobile);
        if (member==null){
            return Result.createByErrorMessage("这个手机号还没注册过哦，请先去注册一下吧");
        }
        //获取token
        String existToken = TokenCache.getKey(TokenCache.TOKEN_SAFE+mobile);
        //验证token，是否是正确输入验证码的用户
        if (StringUtils.equals(existToken,token)){
            String md5Password = MD5Util.encrypt(password+saltProperties.getSalt1());
            int updateCount = memberMapper.updatePasswordByMobile(mobile,password,md5Password);
            if (updateCount>0){
                return Result.createBySuccessMessage("密码修改成功");
            }
        } else {
            return Result.createByErrorMessage("token有误，请重新获取验证码");
        }

        return Result.createByErrorMessage("密码修改错误");
    }*/

    public Result<String> checkValid(String str, String type){
        if (StringUtils.isBlank(type)){
            return Result.createByErrorMessage("type为空，无法检测类型是否已存在");
        }
        if (Const.USERNAME.equals(type)){
            int resultCount = memberMapper.checkName(str);
            if(resultCount > 0){
                return Result.createByErrorMessage("用户名已存在");
            }
        } else if (Const.EMAIL.equals(type)){
            int resultCount = memberMapper.checkEmail(str);
            if(resultCount > 0){
                return Result.createByErrorMessage("邮箱已存在");
            }
        }

        return Result.createBySuccessMessage("检测成功，数据库中无重复");
    }

    /**
     * 更新用户最近登录ip和时间
     * @param accountId
     * @return
     */
    public void updateGmtLastLoginAndLastIp(Integer accountId) {
        String ip = HttpKit.getRequest().getRemoteAddr();
        int updateCount = memberMapper.updateGmtLastLoginAndLastIp(accountId, ip);
        if (updateCount<=0){
            logger.info("更新用户最近登陆ip失败");
        }
        logger.info("更新用户最近登陆ip成功");
    }

    public Result getUuidValidity(String uuidToken){
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if (member==null)
            return Result.createByErrorMessage("该uuid已过期");
        //MessageFormat.format
        //Message(String.format("uuid对应用户为%s",member.getUsername())
        return Result.createBySuccess();
    }


    //公用部分
    /**
     * 加星隐藏保密隐私(只是电话的)
     *
     * @param str
     * @return
     */
    private String addHidePhone(String str) {
        if (str.length() < 6) {
            return str;
        }
        int start = 0;
        if (str.length() > 7) {
            start = 3;
        } else {
            start = 3 - (8 - str.length());
        }
        int end = 0;
        if (str.length() < 9) {
            end = 1;
        } else {
            end = 1 + (str.length() - 8);
        }
        str = str.substring(0, start) + "****"
                + str.substring(str.length() - end, str.length());
        return str;
    }

    /**
     * 加星隐藏保密隐私(只是电话的)
     *
     * @param str
     * @return
     */
    private String addHideEmail(String str) {
        if (StringUtils.isBlank(str)){
            return "";
        }
        if (!str.matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+")){
            return "不是邮箱格式";
        }
        String prefix = str.substring(0,str.lastIndexOf("@"));
        if (prefix.length() < 3) {
            return str;
        }
        int start = 0;
        if (str.length() > 4) {
            start = 3;
        } else {
            start = 3 - (4 - str.length());
        }
       /* int end = 0;
        if (str.length() < 9) {
            end = 1;
        } else {
            end = 1 + (str.length() - 8);
        }*/
        str = str.substring(0, start) + "****"
                + str.substring(str.lastIndexOf("@"), str.length());
        return str;
    }
}
