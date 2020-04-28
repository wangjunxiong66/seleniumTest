package com.wjxselenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddActToCMS {

    //  该方法：在CMS系统上添加一个链接
    @RequestMapping(value = "/addactivity",method = RequestMethod.GET)
    public void addActToCMS(@RequestParam("name") String name, @RequestParam("url") String url){
        String actname = name;
        String acturl = url;
        System.out.println("actname 是 ："+actname+"     acturl 是：  "+acturl);
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

        driver.findElement(By.xpath("//*[@id=\"tr-title\"]/td[2]/input")).sendKeys(actname); ;    //  在"标题"处输入标题

        driver.findElement(By.xpath("//*[@id=\"tr-title\"]/td[2]/label/input")).click();       // 勾选"外部链接"前面的勾选框

        driver.findElement(By.xpath("//*[@id=\"link\"]")).sendKeys(acturl);  // 在url处输入URL

        driver.findElement(By.xpath("//*[@id=\"tr-order\"]/td[2]/label[1]/input")).click();  // 勾选"展示顺序"前面的勾选框

        driver.findElement(By.xpath("//*[@id=\"tr-newsType\"]/td[2]/label[1]/input")).click();  // 勾选"咨询类型"前面的勾选框

        driver.findElement(By.xpath("//*[@id=\"jvForm\"]/table/tbody/tr[17]/td/input[4]")).click();  // 点击"提交"按钮

        // 关闭浏览器
        driver.quit();

    }

}
