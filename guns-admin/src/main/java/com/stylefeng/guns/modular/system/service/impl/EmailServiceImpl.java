package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Email;
import com.stylefeng.guns.modular.system.dao.EmailMapper;
import com.stylefeng.guns.modular.system.service.IEmailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-11-07
 */
@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements IEmailService {

}
