package org.zxg.utils.selenium;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.InputStream;
import java.util.List;

/**
 * 这是一个通过Selenium实现自动化操作浏览器的测试类
 * 根据版本测试，本测试选择版本如下：
 * selenium版本：selenium3.8.0(最新版，导入client-combined-3.8.0.jar即可)。翻墙到官网可下载http://www.seleniumhq.org/download/
 * 使用火狐浏览器做测试，浏览器版本 firefox57.0.3(最新版)
 * geckodriver 驱动：geckodriver 0.19.0（最新版）  由于firefox47以上版本不再内置驱动，需要自己装对应版本的驱动。
 * 下载地址：https://github.com/mozilla/geckodriver/tags下载对应版本
 * @author kevin5211
 * @2018-1-1.
 */
public class SeleniumTest {
    private static WebDriver.Navigation navigation;
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.firefox.bin","D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");

        System.setProperty("webdriver.gecko.driver","D:\\sjht_workspace\\git-other\\commonutil\\src\\main\\resources\\exe\\geckodriver.exe");

        long l1=System.currentTimeMillis();
        //登陆测试模板
       test1();
//        test2();
//        test3();
//        test4();
//        test5();
        long l2=System.currentTimeMillis();
        System.out.println(l2-l1);
    }
    /**
     * 这是一个模拟输入并点击登陆的测试模板
     */
    public static void test1() throws  Exception {
        //首先要注册系统属性，如果是firefox浏览器，需要设置webdriver.gecko.driver（注意不是webdriver.firefox.driver）
        //创建WebDriver对象
        FirefoxOptions chromeOptions=new FirefoxOptions();
        chromeOptions.addArguments("-headless");

        WebDriver driver = new FirefoxDriver(chromeOptions);
        //输入指定的url地址
//        driver.get("http://www.baidu.com/");
        //获取一个导航窗口
        navigation = driver.navigate();
        //加载到指定url
        navigation.to("https://www.baidu.com/");
        driver.get("http://182.254.189.226:8080/");

        System.out.println();
        //id选择器获取指定元素，清空
        driver.findElement(By.xpath("//*[@id=\"username\"]/input")).clear();
        //在******中填你的用户名
        driver.findElement(By.xpath("//*[@id=\"username\"]/input")).sendKeys("test");

        //id选择器获取指定元素，清空
        driver.findElement(By.xpath("//*[@id=\"psd\"]/input")).clear();
        //在*******填你密码
        driver.findElement(By.xpath("//*[@id=\"psd\"]/input")).sendKeys("a");
//        //模拟点击登录按钮
        driver.findElement(By.id("login-btn")).click();

         //测试完成关闭浏览器
        driver.close();

    }

    /**
     *通过firefox浏览器内核渲染的方式获取花瓣网的图片并下载
     */
    public static void test5(){
//        D:\sjht_workspace\git-other\commonutil\src\main\resources\exe\IEDriverServer.exe
        //创建WebDriver对象
        WebDriver driver = new FirefoxDriver();
        //输入指定的url地址
//        driver.get("http://www.baidu.com/");
        //获取一个导航窗口
        navigation = driver.navigate();
        //指定登陆页面
        String path = "https://www.baidu.com/";
        //加载到指定url
        navigation.to(path);

        try {
            /**
             * 下面通过元素选择器对获取到的页面进行图片url抽取，通过url下载。
             */
            System.out.println(driver.getPageSource());
//            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='image-holder']/img"));
//            for (WebElement element : elements) {
//                String src = element.getAttribute("src");
//                System.out.println(src);
//            }
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *通过firefox浏览器内核渲染的方式获取angularjs的主页源代码，产生了实际数据
     */
    public static void test4(){
        System.setProperty("webdriver.gecko.driver","D:\\Program Files\\Driver\\geckodriver.exe");
        //创建WebDriver对象
        WebDriver driver = new FirefoxDriver();
        //输入指定的url地址
//        driver.get("http://www.baidu.com/");

        //获取一个导航窗口
        navigation = driver.navigate();
        //指定登陆页面
        String path = "http://angularjs.cn/";
        //加载到指定url
        navigation.to(path);

        try {
            /*//获取渲染页面源代码字符串
            String pageSource = driver.getPageSource();
            System.out.println(pageSource.toString());*/

            /**
             * 下面通过元素选择器对获取到的页面进行字段抽取，遍历打印出需要的数据。
             */
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='media-header']"));
            for (WebElement element : elements) {
                System.out.println(element.getText());
            }
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *通过普通方式获取angularjs的主页源代码，没有实际数据
     */
    public static void test3(){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //指定登陆页面
        String path = "http://angularjs.cn/";
        //初始化一个Http对象
        HttpPost httpPost = new HttpPost(path);
        try {
            CloseableHttpResponse resp = httpClient.execute(httpPost);
            HttpEntity entity = resp.getEntity();
            InputStream content = entity.getContent();
            int len = 0;
            byte[] b = new byte[1024];
            int read = content.read();
            StringBuffer sb = new StringBuffer();
            while((len=content.read(b))!=-1){
                sb.append(new String(b,"UTF-8") );
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试百度检索获取结果
     */
    public static void test2() {
        System.setProperty("webdriver.gecko.driver","D:\\Program Files\\Driver\\geckodriver.exe");
        //创建WebDriver对象
        WebDriver driver = new FirefoxDriver();
        //输入指定的url地址
//        driver.get("http://www.baidu.com/");

        //获取一个导航窗口
        navigation = driver.navigate();
        //加载到指定url
        navigation.to("https://www.baidu.com/");

        //id选择器获取指定元素，清空
        driver.findElement(By.id("kw")).clear();
        //在输入框中填写检索词
        driver.findElement(By.id("kw")).sendKeys("selenium");

        //模拟点击登录按钮
        driver.findElement(By.id("su")).click();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取检索结果
        WebElement element = driver.findElement(By.xpath("/html"));
        String s = element.getText().toString();
        System.out.println(s);

        //测试完成关闭浏览器
//        driver.close();

    }


}
