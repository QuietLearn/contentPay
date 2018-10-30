package com.stylefeng.guns.core.util.mobile;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hyj on 2018/10/30
 */
public class MobileInfoUtil {

    private static final Logger logger = LoggerFactory.getLogger(MobileInfoUtil.class);

    public static String getMobileAreaInfo (String mobile)
    {
        JSONObject mobileJson = loadJson(mobile);

        String province = mobileJson.getString("province");

        logger.info("province:{}",province);
        return province;
    }

    public static String getMobileOperatorInfo (String mobile)
    {
        JSONObject mobileJson = loadJson(mobile);

        String catName = mobileJson.getString("catName");

        logger.info("catName:{}",catName);
        return catName;
    }


    private static JSONObject loadJson(String mobile) {
        String changemobile = mobile.substring(3);
        String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + changemobile;

        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            //utf-8
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "gbk"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

        String jsonString = json.toString();
        System.out.println(jsonString);

        String substring = jsonString.substring(jsonString.indexOf("= ") + "= ".length());

        JSONObject obj = JSONObject.parseObject(substring);

        return obj;
    }

    public static void main(String[] args) {
        System.out.println(getMobileAreaInfo("+8613065708090"));
    }
}
