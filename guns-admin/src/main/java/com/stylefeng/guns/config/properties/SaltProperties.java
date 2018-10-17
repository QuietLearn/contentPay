package com.stylefeng.guns.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * guns项目配置
 *
 * @author hyj
 * @Date 2018/10/16
 */
@Component
@ConfigurationProperties(prefix = "saltpasswd")
public class SaltProperties {

    /*public static final String PREFIX = "passwd";*/
    //@Value("${saltpasswd.salt1}")
    private String salt1="";

    public String getSalt1() {
        return salt1;
    }

    public void setSalt1(String salt1) {
        this.salt1 = salt1;
    }
}
