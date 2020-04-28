package com.wjxselenium.demo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.wjxselenium.enums.OSPlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
public class OpenBrowser {

    //  该方法：用firefox和chrome浏览器访问页面的例子
    @RequestMapping(value = "/openbrowser",method = RequestMethod.GET)
    public void openBrowserWithFirxfoxAndChrome(){
        //(1)、firefox浏览器测试时，条件是selenium-java 版本3.12.0、selenium-chrome-driver版本3.12.0、添加依赖selenium-server-standalone-3.12.0.jar、安装Firefox 67.0.2版本、使用geckodriver-v0.24.0-macos。
        //(2)、Chrome浏览器测试时，条件是selenium-java 版本3.12.0、selenium-chrome-driver版本3.12.0、添加依赖selenium-server-standalone-3.12.0.jar、安装Firefox 70~73版本。

        // 如果测试的浏览器没有安装在默认目录，那么必须在程序中设置，例如下面
        //Chrome浏览器设置  System.setProperty("webdriver.chrome.driver", "D://tanzhenTest//chromedriver_win32//chromedriver.exe");
        //Firefox浏览器设置   System.setProperty("webdriver.gecko.driver", "/Users/finup/Documents/E/myproject/test/springboottest/selenium/src/main/resources/static/geckodriver/geckodriver-v0.24.0-macos");
        // 下面是用firefox浏览器所做的测试
        // 获取当前操作系统，设置geckodriverPath
        String osName = System.getProperty("os.name");
        String geckodriverPath = null;
        if (osName.equals(OSPlatform.getByValue(3).getDesc())||osName.equals(OSPlatform.getByValue(4).getDesc()))
        {
            geckodriverPath = System.getProperty("user.dir")+"/src/main/resources/static/geckodriver/geckodriver-v0.24.0-macos";
            System.out.println("系统是 "+OSPlatform.getByValue(4).getDesc()+" ， geckodriverPath = "+geckodriverPath);
        } else if (osName.equals("Windows")){
            //  如果失败，可能是Windows操作系统的32位和64位不兼容，切换一下指定的geckodriver试一下
            geckodriverPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\geckodriver\\geckodriver-v0.24.0-win64.exe";
            System.out.println("系统不是Mac OS X  ， geckodriverPath = "+geckodriverPath);
        }
        //  指定firefox的安装路径
        System.setProperty("webdriver.gecko.driver", geckodriverPath);
        //  直接指定firefox的安装路径时用右侧的语句  System.setProperty("webdriver.gecko.driver", "/Users/finup/Documents/D/测试/Selenium/geckodriver-v0.24.0-macos");
        // 启用firefox浏览器
        WebDriver driver = new FirefoxDriver();
        // 在火狐浏览器打开map.baidu.com
        driver.get("https://www.baidu.com");
        System.out.println("页面title是："+driver.getTitle());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//        Boolean webDriverWait = new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("百度一下，你就知道"));
//        System.out.println("页面标题包含\"百度一下，你就知道\"      "+webDriverWait);
        WebDriverWait webDriverWait = new WebDriverWait(driver,10,5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("kw"))).sendKeys("输入信息");
        System.out.println("页面标题包含\"百度一下，你就知道\"      "+webDriverWait);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df1.format(new Date()));// new Date()为获取当前系统时间
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df2.format(new Date()));// new Date()为获取当前系统时间
        driver.quit();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //如果测试的浏览器没有安装在默认目录，那么必须在程序中设置，例如下面
        //Chrome浏览器设置  System.setProperty("webdriver.chrome.driver", "D://tanzhenTest//chromedriver_win32//chromedriver.exe");
        //Firefox浏览器设置   System.setProperty("webdriver.gecko.driver", "/Users/finup/Documents/E/myproject/test/springboottest/selenium/src/main/resources/static/geckodriver/geckodriver-v0.24.0-macos");
        // 下面是用chrome浏览器所做的测试
        // 启用Chrome浏览器
        WebDriver driver1 = new ChromeDriver();
        // 在Chrome浏览器打开map.baidu.com
        driver1.get("https://map.baidu.com");
        //  获取网页的 title
        System.out.println("The testing page title is: " + driver1.getTitle());
        // 全局隐式等待
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // 页面最大化
        driver1.manage().window().maximize();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver1.quit();

    }

}
