package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Member;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-15
 */
public interface MemberMapper extends BaseMapper<Member> {


    int checkName(String name);

    int checkEmail(String email);

    int updateGmtLastLoginAndLastIp(@Param("memberId") Integer accountId,@Param("ip") String ip);

    Member login(@Param("username") String username, @Param("md5Password") String md5Password);

    Member selectMemberByUsername(String username);

    Member selectMemberByUuidToken(String uuidToken);

    int updateUuidById(Member member);

    Member selectMemberByMobile(String mobile);

    Member loginByMobile(@Param("mobile") String mobile, @Param("md5Password") String md5Password);

    int updatePasswordByMobile(@Param("mobile")String mobile,@Param("password")String password
            ,@Param("md5Password")String md5Password);

    int checkEmailByUserId(@Param("email") String email,@Param("userId")  Integer userId);

    int checkMobileByUserId(@Param("mobile") String mobile, @Param("userId")  Integer userId);

    int checkUsernameByUserId(@Param("username") String username,@Param("userId") Integer userId);
}
