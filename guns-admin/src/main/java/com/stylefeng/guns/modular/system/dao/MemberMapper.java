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

    int updateGmtLastLoginAndLastIp(Integer accountId, String ip);

    Member login(@Param("username") String username, @Param("md5Password") String md5Password);

    Member selectMemberByUsername(String username);

    Member selectMemberByUuidToken(String uuidToken);

    int updateUuidById(Member member);
}
