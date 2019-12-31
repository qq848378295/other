package org.zxg.utils.little.video;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.zxg.utils.HttpClientUtil;
import org.zxg.utils.HttpUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DouYin {
    public static void main(String[] args) throws IOException {
        String url = "";
//        url = "https://v.douyin.com/VVaJRg/";
        url = "https://v.douyin.com/VVyJ17/";
        List list = getListByUser(url);
    }

    private static List getListByUser(String url) {
        try {
            String secUid = getSecUid(url);
            System.out.println(secUid);
//           https://www.iesdouyin.com/web/api/v2/aweme/post/?sec_uid=MS4wLjABAAAAChT8-kMqeSGnH6Z0NvDdhTQ4bvE0Gi6BeC63NUvtfq2c718BuH-3xdIxB1JN5Kou&count=21&max_cursor=0&aid=1128&_signature=HeeqPxARQ.H6kFI5Z7JIix3nqi&dytk=a01262cbdd1593696dfcc1c4ae4c8878
            String ex = "&time112=" + System.currentTimeMillis();
            String url2 = "https://www.iesdouyin.com/web/api/v2/aweme/post/?sec_uid=" + secUid + "&count=21&max_cursor=0&aid=1128&_signature=HeeqPxARQ.H6kFI5Z7JIix3nqi&dytk=a01262cbdd1593696dfcc1c4ae4c8878" + ex;
//            System.out.println("url22:"+url2);
//                   url2 = "https://www.iesdouyin.com/web/api/v2/aweme/post/?sec_uid=MS4wLjABAAAAChT8-kMqeSGnH6Z0NvDdhTQ4bvE0Gi6BeC63NUvtfq2c718BuH-3xdIxB1JN5Kou&count=21&max_cursor=1577243088000&aid=1128&_signature=HeeqPxARQ.H6kFI5Z7JIix3nqi&dytk=a01262cbdd1593696dfcc1c4ae4c8878" +ex;
//            System.out.println("url23:"+url2);
            Map map = Maps.newHashMap();
            map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
            System.out.println("result............................");
            while (true) {
                JSONObject json = JSON.parseObject(HttpClientUtil.doGet(url2, null, map));
                if (!json.containsKey("aweme_list")) {
                    System.out.println(json.toString());
                    break;
                }
                System.out.println("size:::" + json.getJSONArray("aweme_list").size());
                if (json.getJSONArray("aweme_list").size() != 0) {
                    System.out.println(json.toString());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getSecUid(String url) throws IOException {
        return HttpUtil.getURLParam(Jsoup.connect(url).followRedirects(true).execute().url(), "sec_uid");
    }
}
