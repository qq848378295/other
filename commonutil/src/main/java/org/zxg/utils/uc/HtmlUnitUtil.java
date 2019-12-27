package org.zxg.utils.uc;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;

/**
 * https://www.cnblogs.com/GH0522/p/9854593.html
 */
public class HtmlUnitUtil {
    public static void main(String[] args) throws Exception {

        //主页   http://a.mp.uc.cn/media.html?mid=84229754edf544798a3868966dcb6980&client=ucweb&uc_param_str=frdnsnpfvecpntnwprdsssnikt

        //通过主页 获取到小视频列表
        //  https://pages.uc.cn/?uc_wx_page_name=SubHomePage&uc_wx_used_dp=0&uc_biz_str=S:custom%7CC:full_screen&uc_wx_ver=0.18.0.0&uc_wx_downgrade=true#uc_wx_init_params=%7B%22weex%22:true,%22wmId%22:%2284229754edf544798a3868966dcb6980%22,%22subType%22:%22wm%22%7D

        //这是获取视频分页的 pos 是分页的参数
        //   https://bigsubs-api.uc.cn/api/bigsubs_personal/msgs?uc_param_str=frdnpfvecpntgibiniprdswiutmt&app=ucweb&sub_type=personal&b_version=0.4&errCode=2&errMsg=ucapi.invoke%20not%20exsit%2C%20should%20load%20in%20UCBrowser%20%21&ut=AAQuLl0WSFNXJwCIgccPZnizKN9OiLo2pkPjd5oIoq2jnA%3D%3D&size=12&wmId=84229754edf544798a3868966dcb6980&pos=&col_id=muggle&col_cont_src=short_video&col_name=%E5%B0%8F%E8%A7%86%E9%A2%91&col_type=video&index=2&wm_id=84229754edf544798a3868966dcb6980&max_pos=

        String sx = "http://share.v.uc.cn/video/share?video_id=329539293965978624&module_id=&size=4&biz_id=1014&scene_id=muggle&xss_item_id=15484208737159782965";
       long l1=System.currentTimeMillis();
       Document doc = getDocument(sx);
        System.out.println(doc.html());
        long l2=System.currentTimeMillis();
        System.out.println("耗时:"+(l2-l1));
//        System.out.println(doc.select("#videoPlay"));
        // 通过
    }
    public static WebClient getWc(){
        WebClient wc = new WebClient(BrowserVersion.BEST_SUPPORTED);
        wc.getOptions().setUseInsecureSSL(true);//是否使用不安全的SSL
        wc.getOptions().setJavaScriptEnabled(true);//启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false);//禁用CSS
        wc.getOptions().setThrowExceptionOnScriptError(false);//js运行错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);//状态码错误时，是否抛出异常
        wc.getOptions().setActiveXNative(false);//是否允许使用ActiveX
        wc.waitForBackgroundJavaScript(600 * 1000);//等待js时间
        wc.setAjaxController(new NicelyResynchronizingAjaxController());//设置Ajax异步处理控制器即启用Ajax支持
        wc.getOptions().setTimeout(10000);//设置超时时间
        wc.getOptions().setDoNotTrackEnabled(false);//不跟踪抓取
        return wc;
    }
    public static WebClient getWc2(){
        WebClient wc = new WebClient(BrowserVersion.INTERNET_EXPLORER);
        wc.getOptions().setUseInsecureSSL(true);//是否使用不安全的SSL
        wc.getOptions().setJavaScriptEnabled(true);//启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false);//禁用CSS
        wc.getOptions().setThrowExceptionOnScriptError(false);//js运行错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);//状态码错误时，是否抛出异常
        wc.getOptions().setActiveXNative(false);//是否允许使用ActiveX
        wc.waitForBackgroundJavaScript(600 * 1000);//等待js时间
        wc.setAjaxController(new NicelyResynchronizingAjaxController());//设置Ajax异步处理控制器即启用Ajax支持
        wc.getOptions().setTimeout(10000);//设置超时时间
        wc.getOptions().setDoNotTrackEnabled(false);//不跟踪抓取
        return wc;
    }

    public static Document getDocument(String url) throws Exception {
        WebRequest request = new WebRequest(new URL(url));
//        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        try {
            HtmlPage htmlPage = getWc().getPage(request); //模拟浏览器打开一个目标网址
            //为了获取js执行的数据 线程开始沉睡等待
//            Thread.sleep(1000);//这个线程的等待 因为js加载需要时间的
            //以xml形式获取响应文本
            String xml = htmlPage.asXml();
            //并转为Document对象return
            return Jsoup.parse(xml);
            //System.out.println(xml.contains("结果.xls"));//false
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//        videoDetail
}
