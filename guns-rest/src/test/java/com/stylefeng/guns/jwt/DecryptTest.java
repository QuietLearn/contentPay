package com.stylefeng.guns.jwt;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.SimpleObject;
import com.stylefeng.guns.rest.modular.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.rest.modular.auth.security.impl.Base64SecurityAction;

/**
 * jwt测试
 *
 * @author fengshuonan
 * @date 2017-08-21 16:34
 */
public class DecryptTest {

    public static void main(String[] args) {

        String salt = "fmr7ql";

        //Bearer 规范，如果authorization加了，服务端验证也要加
        String jwtToken ="eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJmbXI3cWwiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU0MjcwNDcyMSwiaWF0IjoxNTQyMDk5OTIxfQ.6qqwznwRjZAABQcQCWrEO7Cn-h2C3RQrEgFU_k159qgYfC89LkHrj7SIMi4ut8VkNDKy6RSfL8pNYoxiWDqbKw";


        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setUser("stylefeng");
        simpleObject.setAge(12);
        simpleObject.setName("ffff");
        simpleObject.setTips("code");

        String jsonString = JSON.toJSONString(simpleObject);
        String encode = new Base64SecurityAction().doAction(jsonString);
        //加上混淆的随机字符串
        String md5 = MD5Util.encrypt(encode + salt);
        //
        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
