package com.wjxselenium.common;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class Test {

    @RequestMapping(value = "screenshot",method = RequestMethod.GET)
    public void screenShot(){

        // 启用Chrome浏览器
        WebDriver driver1 = new ChromeDriver();
        // 在Chrome浏览器打开map.baidu.com
        driver1.get("https://m.iqianjin.com/m/login/");
        //  获取网页的 title
        System.out.println("The testing page title is: " + driver1.getTitle());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("下面发送组合键");
        driver1.findElement(By.className("img-code")).sendKeys(Keys.COMMAND,Keys.ALT,"j");
        System.out.println("发送组合键结束");
        // 全局隐式等待
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement ele = driver1.findElement(By.className("img-code"));
        //  利用TakesScreenshot截屏
        File screenshot = ((TakesScreenshot)driver1).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg =null;
        try {
            fullImg = ImageIO.read(screenshot);
        }catch (IOException e1) {
            e1.printStackTrace();
        }

        Point point = ele.getLocation();
        System.out.println("验证码位于   "+point);
        //getSubimage（）定位元素   x轴位置  y轴位置  宽  高  定位到验证码图片
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), 120, 48);
        try {
            ImageIO.write(eleScreenshot, "png", screenshot);
        }catch (IOException e1) {
            e1.printStackTrace();
        }
//替换文件夹内图片
        File screenshotLocation =new File(System.getProperty("user.dir")+"/src/main/resources/static/screenshot/11.png");
        try {
            FileUtils.copyFile(screenshot, screenshotLocation);
        }catch (IOException e1) {
            e1.printStackTrace();
        }

        driver1.quit();

    }

}
