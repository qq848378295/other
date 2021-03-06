package org.zxg.utils.uc;

import org.openqa.selenium.*;
import org.zxg.utils.selenium.SeleniumConstant;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SeleniumDemo {
    public static void main(String[] args) throws  Exception {
//        a();
        b();
//        https://pages.uc.cn/?uc_wx_page_name=SubHomePage&uc_wx_used_dp=0&uc_biz_str=S:custom%7CC:full_screen&uc_wx_ver=0.18.0.0&uc_wx_downgrade=true#uc_wx_init_params=%7B%22weex%22:true,%22wmId%22:%2284229754edf544798a3868966dcb6980%22,%22subType%22:%22wm%22%7D

    }

    private static void b() throws  Exception {
        WebDriver driver = SeleniumConstant.getChromeDriver("D:\\sjht_workspace\\git-other\\chrome\\demo1.crx");
        driver.get("https://pages.uc.cn/?uc_wx_page_name=SubHomePage&uc_wx_used_dp=0&uc_biz_str=S:custom%7CC:full_screen&uc_wx_ver=0.18.0.0&uc_wx_downgrade=true#uc_wx_init_params=%7B%22weex%22:true,%22wmId%22:%2284229754edf544798a3868966dcb6980%22,%22subType%22:%22wm%22%7D");
       By by=By.xpath("//*[@id=\"slide-tab\"]/div/span[3]");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element = driver.findElement(by);
       element.click();
        //  https://bigsubs-api.uc.cn/api/bigsubs_personal/msgs?
        //  通过fidller 脚本记录下 请求url 然后 通过http 上传到网络
        JavascriptExecutor js= ((JavascriptExecutor) driver);

//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        System.out.println(driver.getPageSource());
//        System.out.println(driver.getCurrentUrl());
//        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

//        sendKeys(Keys.ESCAPE) 回退键（Esc）
//
//        Thread.sleep(6000);


        String txt=driver.findElement(By.id("wrap")).getText();
        System.out.println("txt::"+txt);
        //处理txt  如果存在自定义的数据 则证明已拿到数据
        if(txt.contains("<data>")){
            System.out.println(txt.substring(txt.indexOf("<data>")));
        }else{
            WebElement html = driver.findElement(By.tagName("html"));
            System.out.println("开始pagedown ");
            for(int i=0;i<15;i++){
                html.sendKeys(Keys.PAGE_DOWN);
//            String title = js.executeScript("return document.getElementById('wrap');").toString();
                String title=driver.findElement(By.id("wrap")).getText();
                System.out.println("title::"+title);
                Thread.sleep(1000);

            }Thread.sleep(60000);
        }

        driver.close();
        driver.quit();
        //taskkill /f /t /im chromedriver.exe
        //taskkill /f /t /im chromedriver.exe
    }



    private static void a() throws  Exception {
        WebDriver driver = SeleniumConstant.getChromeDriver();
        driver.get("http://a.mp.uc.cn/media.html?mid=84229754edf544798a3868966dcb6980&client=ucweb&uc_param_str=frdnsnpfvecpntnwprdsssnikt");
        driver.findElement(By.cssSelector("#slide-tab > div > span:nth-child(3)")).click();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getPageSource());
        driver.close();
    }
}
