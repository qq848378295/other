package org.zxg.utils.selenium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome {
    public static void main(String[] args) {
//        C:\Users\29106\AppData\Local\Google\Chrome\Application
        //设置本地chromedriver地址
//        System.setProperty("webdriver.chrome.driver", "G:\\software\\chromedriver_win32\\chrome.exe");
        System.setProperty("webdriver.chrome.bin","C:\\Users\\29106\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver","D:\\sjht_workspace\\git-other\\commonutil\\src\\main\\resources\\exe\\chromedriver.exe");
        //创建无Chrome无头参数
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("-headless");
        //创建Drive实例
//        WebDriver driver = new ChromeDriver(chromeOptions);
//        System.out.println(driver.getPageSource());



        WebDriver webDriver = null;
        try {
            String url = "https://www.jianshu.com/p/675ea919230e";
            //启动一个 chrome 实例
            webDriver = new ChromeDriver(chromeOptions);
            //访问网址
            webDriver.get(url);
            Document document = Jsoup.parse(webDriver.getPageSource());
            System.out.println(document.html());
//            Element titleElement = document.selectFirst("div.article h1.title");
//            Element authorElement = document.selectFirst("div.article div.author span.name");
//            Element timeElement = document.selectFirst("div.article span.publish-time");
//            Element wordCountElement = document.selectFirst("div.article span.wordage");
//            Element viewCountElement = document.selectFirst("div.article span.views-count");
//            Element commentCountElement = document.selectFirst("div.article span.comments-count");
//            Element likeCountElement = document.selectFirst("div.article span.likes-count");
//            Element contentElement = document.selectFirst("div.article div.show-content");
//            if (titleElement != null) {
//                System.out.println("标题：" + titleElement.text());
//            }
//            if (authorElement != null) {
//                System.out.println("作者：" + authorElement.text());
//            }
//            if (timeElement != null) {
//                System.out.println("发布时间：" + timeElement.text());
//            }
//            if (wordCountElement != null) {
//                System.out.println(wordCountElement.text());
//            }
//            if (viewCountElement != null) {
//                System.out.println(viewCountElement.text());
//            }
//            if (commentCountElement != null) {
//                System.out.println(commentCountElement.text());
//            }
//            if (likeCountElement != null) {
//                System.out.println(likeCountElement.text());
//            }
//
//            if (contentElement != null && contentElement.text() != null) {
//                System.out.println("正文长度：" + contentElement.text().length());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (webDriver != null) {
                //退出 chrome
                webDriver.quit();
            }
        }

    }
}
