package org.zxg.utils.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class SeleniumConstant {
    public static void initChrome() {
        System.setProperty("webdriver.chrome.bin", "C:\\Users\\29106\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\sjht_workspace\\git-other\\commonutil\\src\\main\\resources\\exe\\chromedriver.exe");
    }

    public static void initFirefox() {
        System.setProperty("webdriver.firefox.bin", "D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\sjht_workspace\\git-other\\commonutil\\src\\main\\resources\\exe\\geckodriver.exe");

    }

    public static WebDriver getChromeHeadlessDriver() {
        initChrome();
        return new ChromeDriver(new ChromeOptions().setHeadless(true));
    }

    public static WebDriver getChromeDriver() {
       return getChromeDriver(null);
    }

    public static WebDriver getChromeDriver(String crx) {
        initChrome();
        // 设置代理ip
//        String ip = "127.0.0.1:8888";
        ChromeOptions options = new ChromeOptions().setHeadless(false);
            if(crx!=null){
                //存放插件的位置
                File plugPath = new File(crx);
                //添加插件到chrome浏览器中
                if(plugPath.exists()) {
                    options.addExtensions(plugPath);
                }
                //添加插件到chrome浏览器中
            }


//        options.addArguments("--proxy-server=http://" + ip);
        //        D:\sjht_workspace\git-other\chrome\demo1.crx



        WebDriver driver = new ChromeDriver(options); //创建Drive实例
        return driver;


    }
}
