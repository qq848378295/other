package org.zxg.utils.uc;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        String url="http://a.mp.uc.cn/media.html?mid=7737909731c141798153300adca85262&client=ucweb&uc_param_str=frdnsnpfvecpntnwprdsssnikt";
        WebRequest request = new WebRequest(new URL(url));
        try {
            HtmlPage page =  HtmlUnitUtil.getWc().getPage(request); //模拟浏览器打开一个目标网址
//            System.out.println(page.asXml());
            DomNodeList<DomElement> list=page.getElementsByTagName("button");
            for (DomElement e : list) {
                if ("media-header-avatar__follow".equals(e.getAttribute("class"))) {
                    page = e.click();
                    url=page.getUrl().toString();
//                    System.out.println("找到关注"+page.getUrl());
                     abc(url);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void abc(String url) {
        System.out.println("进入关注后的界面"+url); //TODO 获取小视频列表  未完成
//        url="https://bigsubs-api.uc.cn/api/bigsubs_personal/msgs?uc_param_str=frdnpfvecpntgibiniprdswiutmt&app=ucweb&sub_type=personal&b_version=0.4&errCode=2&errMsg=ucapi.invoke%20not%20exsit%2C%20should%20load%20in%20UCBrowser%20%21&ut=AAQuLl0WSFNXJwCIgccPZnizKN9OiLo2pkPjd5oIoq2jnA%3D%3D&size=12&wmId=7737909731c141798153300adca85262&pos=&col_id=muggle&col_cont_src=short_video&col_name=%E5%B0%8F%E8%A7%86%E9%A2%91&col_type=video&index=2&wm_id=7737909731c141798153300adca85262&max_pos=";
        try {
            WebRequest request = new WebRequest(new URL(url));
            HtmlPage page =  HtmlUnitUtil.getWc2().getPage(request); //模拟浏览器打开一个目标网址
            System.out.println("主页:"+page.asXml());
            DomNodeList<DomElement> list=page.getElementsByTagName("span");
            for (DomElement e : list) {
                System.out.println("查找video:"+e.asXml());
//                if ("media-header-avatar__follow".equals(e.getAttribute("class"))) {
//                    page = e.click();
//                    url=page.getUrl().toString();
//                    System.out.println("找到关注"+page.getUrl());
//                    abc(url);
//                    break;
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
