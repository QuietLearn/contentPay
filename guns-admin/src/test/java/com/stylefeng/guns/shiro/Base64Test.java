package com.stylefeng.guns.shiro;

import org.apache.shiro.codec.Base64;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Base64Test {

    /**
     * Shiro 记住密码采用的是AES加密，AES key length 需要是16位，该方法生成16位的key
     */
    public static void main(String[] args) {
    	
        String keyStr = "guns";
        
        byte[] keys;
		try {
			keys = keyStr.getBytes("UTF-8");
			//需要十六位，需要进行base64的转化
			//十六位shiro的rememberme才可以开启，生效
	        System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys, 16)));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		//是每一个字节的输出，ASCII码
		System.out.println(Arrays.toString(keyStr.getBytes()));
		System.out.println(Arrays.toString(Arrays.copyOf(keyStr.getBytes(),16)));
		System.out.println(Arrays.toString(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA==")));
    }

}
