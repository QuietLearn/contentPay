package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.config.properties.SaltProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.state.ServerType;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.modular.system.dao.MemberTypeMapper;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.service.IMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.vo.MemberVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberTypeMapper memberTypeMapper;

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
        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(member,memberVo);


        return Result.createBySuccess("登录成功",memberVo);
    }

    //判断是否是vip
    public Result checkVip(String username){
        if (StringUtils.isBlank(username)){
            return Result.createByErrorMessage("username传值为空");
        }

        Member member = memberMapper.selectMemberByUsername(username);
        if (member==null){
            return Result.createByErrorMessage("用户不存在");
        }

        if (ServerType.REGULAR.getCode()==member.getMemberTypeId()){
            return Result.createByErrorMessage("请充值会员后观看");
        }

        return Result.createBySuccessMessage("欢迎观看");
    }

    public Result<String> register(Member member) {
        if (member==null){
            return Result.createByErrorMessage("member为空");
        }
         Result<String> result = this.checkValid(member.getUsername(), Const.USERNAME);

        if (!result.isSuccess()){
            return result;
        }
        result = this.checkValid(member.getEmail(), Const.EMAIL);
        if (!result.isSuccess()){
            return result;
        }

        //ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
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
        //重要，签到的逻辑
        /*if (flag > 0) {
            Sign sign = signService.selectByTodayAndAccountId(account
                    .getId());
            if (sign != null) {
                accountUser.setSignCount(sign.getCount());
            }
        }*/
        this.insert(member);

        return Result.createBySuccessMessage("注册成功");
    }




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

    @Override
    public Result<String> forgetPassGetQuestion(String username) {
        Result<String> result = this.checkValid(username, Const.USERNAME);
        if (result.isSuccess()){
            return Result.createByErrorMessage("不存在该用户");
        }
        /*String question =
        return ;
*/
        return null;
    }


    public Result updateGmtLastLoginAndLastIp(Integer accountId) {
        String ip = HttpKit.getRequest().getRemoteAddr();
        int updateCount = memberMapper.updateGmtLastLoginAndLastIp(accountId, ip);
        if (updateCount<=0){
            return Result.createByErrorMessage("更新用户最近登陆ip失败");
        }
        return Result.createBySuccessMessage("更新用户最近登陆ip成功");
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
