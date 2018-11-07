package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.Member;

/**
 * Created by hyj on 2018/11/7
 */
public interface IMailService {
    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, Member member);
}
