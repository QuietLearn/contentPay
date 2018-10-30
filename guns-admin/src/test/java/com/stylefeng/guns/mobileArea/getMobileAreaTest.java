package com.stylefeng.guns.mobileArea;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class getMobileAreaTest {
    /**
     * @param args
     */
    public static void main(String[] args)
    {

    }

    public static String loadJSON (String mobile)
    {
        String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="+mobile;

        StringBuilder json = new StringBuilder();
        try{
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            //utf-8
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"gbk"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

        System.out.println(json);

        String substring = json.substring(json.indexOf("= ")+"= ".length());

        JSONObject obj = JSONObject.parseObject(substring);
        String province = obj.getString("province");

        System.out.println(province);

        return json.toString();
    }


}
