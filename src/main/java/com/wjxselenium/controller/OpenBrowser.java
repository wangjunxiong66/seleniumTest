package com.wjxselenium.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenBrowser {

    public static void main(String[] args) {
        //(1)、firefox浏览器测试时，条件是selenium-java 版本3.12.0、selenium-chrome-driver版本3.12.0、添加依赖selenium-server-standalone-3.12.0.jar、安装Firefox 67.0.2版本、使用geckodriver-v0.24.0-macos。
        //(2)、Chrome浏览器测试时，条件是selenium-java 版本3.12.0、selenium-chrome-driver版本3.12.0、添加依赖selenium-server-standalone-3.12.0.jar、安装Firefox 70~73版本。

        // 如果测试的浏览器没有安装在默认目录，那么必须在程序中设置，例如下面
        //bug1:System.setProperty("webdriver.chrome.driver", "C://Program Files (x86)//Google//Chrome//Application//chrome.exe");
        //bug2:System.setProperty("webdriver.chrome.driver", "C://Users//Yoga//Downloads//chromedriver_win32//chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "D://tanzhenTest//chromedriver_win32//chromedriver.exe");
        // 下面是用firefox浏览器所做的测试
        // 获取当前操作系统，设置geckodriverPath
        String osName = System.getProperty("os.name");
        String geckodriverPath;
        if (osName.equals("Mac OS X"))
        {
            geckodriverPath = System.getProperty("user.dir")+"/src/main/resources/static/geckodriver/geckodriver-v0.24.0-macos";
            System.out.println("系统是Mac OS X  ， geckodriverPath = "+geckodriverPath);
        } else {
            geckodriverPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\geckodriver\\geckodriver-v0.24.0-macos";
            System.out.println("系统不是Mac OS X  ， geckodriverPath = "+geckodriverPath);
        }
        //  指定firefox的安装路径
        System.setProperty("webdriver.gecko.driver", geckodriverPath);
        //  直接指定firefox的安装路径时用右侧的语句  System.setProperty("webdriver.gecko.driver", "/Users/finup/Documents/D/测试/Selenium/geckodriver-v0.24.0-macos");
        // 启用firefox浏览器
        WebDriver driver = new FirefoxDriver();
        // 在火狐浏览器打开map.baidu.com
        driver.get("https://www.baidu.com");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果测试的浏览器没有安装在默认目录，那么必须在程序中设置，例如下面
        //bug1:System.setProperty("webdriver.chrome.driver", "C://Program Files (x86)//Google//Chrome//Application//chrome.exe");
        //bug2:System.setProperty("webdriver.chrome.driver", "C://Users//Yoga//Downloads//chromedriver_win32//chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "D://tanzhenTest//chromedriver_win32//chromedriver.exe");
        // 下面是用chrome浏览器所做的测试
        // 启用Chrome浏览器
        WebDriver driver1 = new ChromeDriver();
        // 在Chrome浏览器打开map.baidu.com
        driver1.get("https://map.baidu.com");
        //        // 获取 网页的 title
        System.out.println("The testing page title is: " + driver1.getTitle());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver1.quit();

    }
}
