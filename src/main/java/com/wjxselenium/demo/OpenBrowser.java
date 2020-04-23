package com.wjxselenium.demo;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class OpenBrowser {

    public static void main(String[] args) {
        OpenBrowser openBrowser = new OpenBrowser();
        openBrowser.verifyImg();
    }

    //  该方法：利用登录前后cookie的区别，获取登录所需的cookie值，依此来跳过验证码直接登录成功
    public void verifyImg(){
        //  chromeOptions 是一个配置 chrome 启动时属性的类
        //  下面四行这么写是去掉谷歌浏览器上面提示的，第二行和第三行分别对应不同的提示
        ChromeOptions options = new ChromeOptions();
        options.addArguments("excludeSwitches") ;
        options.addArguments("ignore-certificate-errors") ;
        options.addArguments("disable-infobars");
        WebDriver cdriver = new ChromeDriver(options) ;
        //  输入想打开的地址
        cdriver.get("https://m.iqianjin.com");
        //  将浏览器页面最大化
        cdriver.manage().window().maximize();
        //  用下面的代码获取登录前的cookie
        Set<Cookie> beforelogincookies=cdriver.manage().getCookies();
        //  打印登录前的cookie
        System.out.println("登录前的cookie是  ："+beforelogincookies);
        //  等待30秒，用这30秒时间完成登录操作
        try {
            Thread.sleep(30000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  用下面的代码获取登录后的cookie
        Set<Cookie> afterlogincookies=cdriver.manage().getCookies();
        //  打印登录后的cookie
        System.out.println("登录后的cookie是  ："+afterlogincookies);
        //  等待20秒，用这20秒时间完成退出操作
        try {
            Thread.sleep(20000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  将获取到的cookie进行筛选，将满足需要的cookie加到临时的变量中
        Set<Cookie> cookieSet = new HashSet<Cookie>();
        for (Cookie cookie:afterlogincookies){
//            if (cookie.getName().equals("cookie的名字，例如 lm")){
            if (cookie.getName().equals("lm")){
                Cookie temp = new Cookie(cookie.getName(),cookie.getValue());
                cookieSet.add(temp);
//            } else if (cookie.getName().equals("cookie的名字，例如 lp")){
            } else if (cookie.getName().equals("lp")){
                Cookie temp1 = new Cookie(cookie.getName(),cookie.getValue());
                cookieSet.add(temp1);
            }
        }
        //  下面两行代码，也能实现上面for循环语句的流程【代替1】
//        Cookie cookielm = cdriver.manage().getCookieNamed("lm");
//        Cookie cookielp = cdriver.manage().getCookieNamed("lp");
//        System.out.println("获取到的 cookielm 和 cookielm  值是：  "+cookielm+"       "+cookielp);
        System.out.println("获取到的 cookieSet 值是：  "+cookieSet);
        //  清除获取到的cookie
        cdriver.manage().deleteAllCookies();
        //  打印清除cookie的cookie值
        Set<Cookie> clearlogincookies=cdriver.manage().getCookies();
        System.out.println("清除cookie值后的cookie是  ："+clearlogincookies);
        //  将保存的cookieSet中的cookie，写到浏览器的cookie中去【代替2】
        for (Cookie cookie:cookieSet){
            cdriver.manage().addCookie(cookie);
        }
        //  上面筛选的语句如果用【代替1】代替，那上面加入保存cookie值的【代替2】，就要用下面的语句替代
//        cdriver.manage().addCookie(cookielm);
//        cdriver.manage().addCookie(cookielp);
        //  再次输入网址
        cdriver.get("https://m.iqianjin.com/m/user-center");
        try {
            Thread.sleep(10000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  浏览器退出
        cdriver.quit();

    }

    //  该方法：在CMS系统上添加一个链接
    public void openCMS(){
        String url =null;
        url="http://www.baidu.com" ;
        //指定firefox的安装路径
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
        // 打开火狐浏览器
        WebDriver driver = new FirefoxDriver();
        // 打开CMS并登录
        driver.get("CMS地址");
        driver.manage().window().maximize();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("123456");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.name("submit")).click();
        // 在CMS中添加新信息

        driver.switchTo().frame("leftFrame");
        // 点击"内容"
        driver.findElement(By.xpath("//*[@id=\"tb_13\"]/a[text()='内容']")).click();

        //退回上一级表单
        driver.switchTo().defaultContent();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 切换到左侧，点击左侧页签"app发现页(新版)"
        driver.switchTo().frame("mainFrame");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame("leftFrame");
        driver.findElement(By.xpath("//*[@id=\"435\"]/span/a[text()='app发现页(新版) ']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 点击左侧页签"app发现页(新版)"下面的"平台资讯列表（发现页新UI） "
        driver.findElement(By.xpath("//*[@id=\"438\"]/span/a")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 切换到右侧页面
        driver.switchTo().parentFrame();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame("rightFrame") ;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //鼠标悬停在"发布"按钮上，出现app发现页(新版)，再点击app发现页(新版)
        WebElement deploy = driver.findElement(By.xpath("//*[@id=\"jsddm\"]/li/a"));
        Actions action = new Actions(driver);
        action.moveToElement(deploy).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        driver.findElement(By.linkText("app发现页(新版)")).click();
        driver.findElement(By.xpath("//*[@id=\"jsddm\"]/li/ul/li/a")).click(); ;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 在添加页面上勾选、添加各种信息
        driver.findElement(By.xpath("//*[@id=\"tr-typeId\"]/td[2]/label[1]/input[1]")).click();       // 勾选"推荐"前面的勾选框

        driver.findElement(By.xpath("//*[@id=\"tr-title\"]/td[2]/input")).sendKeys("添加个标题"); ;    //  在"标题"处输入标题

        driver.findElement(By.xpath("//*[@id=\"tr-title\"]/td[2]/label/input")).click();       // 勾选"外部链接"前面的勾选框

        driver.findElement(By.xpath("//*[@id=\"link\"]")).sendKeys(url);  // 在url处输入URL

        driver.findElement(By.xpath("//*[@id=\"tr-order\"]/td[2]/label[1]/input")).click();  // 勾选"展示顺序"前面的勾选框

        driver.findElement(By.xpath("//*[@id=\"tr-newsType\"]/td[2]/label[1]/input")).click();  // 勾选"咨询类型"前面的勾选框

        driver.findElement(By.xpath("//*[@id=\"jvForm\"]/table/tbody/tr[17]/td/input[4]")).click();  // 点击"提交"按钮

        // 关闭浏览器
        driver.quit();

    }

    //  该方法：用firefox和chrome浏览器访问页面的例子
    public void demo(){
        //(1)、firefox浏览器测试时，条件是selenium-java 版本3.12.0、selenium-chrome-driver版本3.12.0、添加依赖selenium-server-standalone-3.12.0.jar、安装Firefox 67.0.2版本、使用geckodriver-v0.24.0-macos。
        //(2)、Chrome浏览器测试时，条件是selenium-java 版本3.12.0、selenium-chrome-driver版本3.12.0、添加依赖selenium-server-standalone-3.12.0.jar、安装Firefox 70~73版本。

        // 如果测试的浏览器没有安装在默认目录，那么必须在程序中设置，例如下面
        //Chrome浏览器设置  System.setProperty("webdriver.chrome.driver", "D://tanzhenTest//chromedriver_win32//chromedriver.exe");
        //Firefox浏览器设置   System.setProperty("webdriver.gecko.driver", "/Users/finup/Documents/E/myproject/test/springboottest/selenium/src/main/resources/static/geckodriver/geckodriver-v0.24.0-macos");
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
