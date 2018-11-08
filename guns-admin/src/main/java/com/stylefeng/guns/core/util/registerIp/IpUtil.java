package com.stylefeng.guns.core.util.registerIp;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hyj on 2018/11/8
 */
public class IpUtil {
    //获得客户端真实IP地址的方法一：
    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null ) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    //获得客户端真实IP地址的方法二：
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null  || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip ==  null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "本地";
        }
        return ip;
    }


    public static String IP() throws IOException {
        String IP=null;
        URL url = new URL("http://iframe.ip138.com/ic.asp");
        URLConnection conn = url.openConnection();
        conn.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.15) Gecko/20110303 Firefox/3.6.15");
        conn.setRequestProperty("Content-Type", "text/html");
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,
                "GB2312"));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.contains("您的IP是")) {
//	System.out.println(line);
                int start = line.indexOf('[') + 1;
                int end = line.indexOf(']');
                IP=line.substring(start, end);
            }
        }
        br.close();
        return IP;
    }

    public static void main(String[] args) throws IOException {
        IP();
    }
}
