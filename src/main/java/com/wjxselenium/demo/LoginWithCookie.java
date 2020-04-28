package com.wjxselenium.demo;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashSet;
import java.util.Set;

public class LoginWithCookie {

    //  该方法：利用登录前后cookie的区别，获取登录所需的cookie值，依此来跳过验证码直接登录成功
    @RequestMapping(value = "/loginwithcookie",method = RequestMethod.GET)
    public void loginWithCookie(){
        //  chromeOptions 是一个配置 chrome 启动时属性的类
        //  下面四行这么写是去掉谷歌浏览器上面提示的，第二行和第三行分别对应不同的提示
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("excludeSwitches") ;
//        options.addArguments("ignore-certificate-errors") ;
//        options.addArguments("disable-infobars");
//        WebDriver cdriver = new ChromeDriver(options) ;
        WebDriver cdriver = new ChromeDriver() ;
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


}
