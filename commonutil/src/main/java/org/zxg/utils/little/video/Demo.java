package org.zxg.utils.little.video;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.zxg.utils.HttpClientUtil;
import org.zxg.utils.MD5;
import org.zxg.utils.UnicodeUtil;

import java.io.UnsupportedEncodingException;
import java.util.Random;


public class Demo {
    public static void main(String[] args) throws  Exception {
        String url="https://v.douyin.com/Vm41qH/";
//        url="https://v.douyin.com/VVaJRg/";
        kukuTool(url);
    }

    /**
     *  支持 快手，抖音，秒拍，tiktok，贴吧，火锅，微博，微视，小红书，美拍等30多个短视频平台
     * @param url https://dy.kukutool.com/kuaishou.html
     */
    private static void kukuTool( String url) {
        String r=String.valueOf(new Random().nextDouble()).substring(2);
        String e= MD5.getMd5(url+"@&^"+r);
        String str=String.format("{\"sourceURL\":\"%s\",\"e\":\"%s\",\"r\":\"%s\",\"ticket\":\"\",\"randstr\":\"\"}",url,e,r);
        String result=HttpClientUtil.doPostJson("https://dy.kukutool.com/kuaishou",str);
        System.out.println(UnicodeUtil.decodeUnicode(result));
    }

}
