package com.stylefeng.guns.mobileArea;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class getMobileAreaTest {
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=13065708090";
        String json =loadJSON(url);
        System.out.println(json);
    }

    public static String loadJSON (String url)
    {
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

        return json.toString();
    }


}
