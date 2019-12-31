package org.zxg.utils.uc;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

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
//                    System.out.println("找到关注"import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.openqa.selenium.WebDriver;
//
//import java.io.IOException;+page.getUrl());
                     abc(url);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    // https://pages.uc.cn/?uc_wx_page_name=SubHomePage&uc_wx_used_dp=0&uc_biz_str=S:custom%7CC:full_screen&uc_wx_ver=0.18.0.0&uc_wx_downgrade=true#uc_wx_init_params=%7B%22weex%22:true,%22wmId%22:%227737909731c141798153300adca85262%22,%22subType%22:%22wm%22%7D

    // https://bigsubs-api.uc.cn/api/bigsubs_personal/msgs?uc_param_str=frdnpfvecpntgibiniprdswiutmt&app=ucweb&sub_type=personal&b_version=0.4&errCode=2&errMsg=ucapi.invoke%20not%20exsit%2C%20should%20load%20in%20UCBrowser%20%21&ut=AAQuLl0WSFNXJwCIgccPZnizKN9OiLo2pkPjd5oIoq2jnA%3D%3D&size=12&wmId=7737909731c141798153300adca85262&pos=&col_id=muggle&col_cont_src=short_video&col_name=%E5%B0%8F%E8%A7%86%E9%A2%91&col_type=video&index=2&wm_id=7737909731c141798153300adca85262&max_pos=


    private static void abc(String url) {

//        try{
//           System.out.println("进入关注后的界面"+url); //TODO 获取小视频列表  未完成
//            WebDriver driver = new PhantomJSDriver();
//            driver.get("http://www.baidu.com");
//            System.out.println(driver.getCurrentUrl());
//        }catch (Exception e){
//
//        }
//
////        url="https://bigsubs-api.uc.cn/api/bigsubs_personal/msgs?uc_param_str=frdnpfvecpntgibiniprdswiutmt&app=ucweb&sub_type=personal&b_version=0.4&errCode=2&errMsg=ucapi.invoke%20not%20exsit%2C%20should%20load%20in%20UCBrowser%20%21&ut=AAQuLl0WSFNXJwCIgccPZnizKN9OiLo2pkPjd5oIoq2jnA%3D%3D&size=12&wmId=7737909731c141798153300adca85262&pos=&col_id=muggle&col_cont_src=short_video&col_name=%E5%B0%8F%E8%A7%86%E9%A2%91&col_type=video&index=2&wm_id=7737909731c141798153300adca85262&max_pos=";
//        try {
//            WebRequest request = new WebRequest(new URL(url));
////            request.setAdditionalHeader("user-agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
//            //        user-agent: Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1
//            HtmlPage page =  HtmlUnitUtil.getWc2().getPage(request); //模拟浏览器打开一个目标网址
//            System.out.println("主页:"+page.asXml());
//            DomNodeList<DomElement> list=page.getElementsByTagName("span");
//            for (DomElement e : list) {
//                System.out.println("查找video:"+e.asXml());
////                if ("media-header-avatar__follow".equals(e.getAttribute("class"))) {
////                    page = e.click();
////                    url=page.getUrl().toString();
////                    System.out.println("找到关注"+page.getUrl());
////                    abc(url);
////                    break;
////                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
