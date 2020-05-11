package com.wjxselenium.utils;


import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.imageio.ImageIO;

public class CaptureScreen {

    //  截屏操作
    public void captureScreen() throws AWTException, IOException {
        //  在测试中发现，如果调用截屏的用例执行较慢，或者下一个调用截屏的用例执行较慢，相邻截屏的图片可能很相似，所以需要在截屏操作前后都添加一个时间缓冲
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取屏幕的尺寸（dimension是Java的一个类，封装了一个构件的高度和宽度）。Toolkit是抽象类，所以不能用new Toolkit()实例化对象。但是Toolkit有静态方法getDefaultToolkit()，通过这个方法可以获取到Toolkit的对象。
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("The width and the height of the screen are " + screenSize.getWidth() + " x " + screenSize.getHeight());
        // 截取屏幕。从jdk1.3开始，提供了一个Robot类，在java.awt.*包下面。这个类在jdk中描述如下：(1)、此类用于为测试自动化、自运行演示程序和其他需要控制鼠标和键盘的应用程序生成本机系统输入事件。Robot 的主要目的是便于 Java 平台实现自动测试。(2)、使用该类生成输入事件与将事件发送到 AWT 事件队列或 AWT 组件的区别在于：事件是在平台的本机输入队列中生成的。例如，Robot.mouseMove 将实际移动鼠标光标，而不是只生成鼠标移动事件。(3)、注意，某些平台需要特定权限或扩展来访问低级输入控件。如果当前平台配置不允许使用输入控件，那么试图构造 Robot 对象时将抛出 AWTException。例如，如果 X 服务器不支持（或没有启用）XTEST 2.2 标准扩展，则 X-Window 系统会抛出异常。(4)、出于自测试之外的目的而使用 Robot 的应用程序应妥善处理这些错误条件。(5)、这个类除了模拟鼠标键盘操作以外，还可以用来截取屏幕，具体api参考javadoc。
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(screenSize));
        // 设置日期格式，作为目录名
        DateFormat dfDirectory = new SimpleDateFormat("yyyyMMdd");
        // 创建一个用于保存图片的文件夹。File file=new File(); 这句是新建一个文件。File.separator这个代表系统目录中的间隔符，说白了就是斜线，不过有时候需要双线，有时候是单线，你用这个静态变量就解决兼容问题了。
//        File screenCaptureDirectory = new File("J:" + File.separator + "ScreenCapture" + File.separator + dfDirectory.format(new Date()));
        String projectDirectory = System.getProperty("user.dir") ;
        File screenCaptureDirectory = new File(projectDirectory + File.separator + "src" +  File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "ScreenCapture" + File.separator + dfDirectory.format(new Date()));
        if (!screenCaptureDirectory.exists()) {
            //  利用Java的自带命令file.mkdirs() 是可以直接在系统创建文件夹的
            screenCaptureDirectory.mkdirs();
            System.out.println("The directory " + screenCaptureDirectory.getName() + " is created.");
        }
        // 设置日期格式，作为图片名
        DateFormat dfImageName = new SimpleDateFormat("yyyyMMddHHmmss");
        // 指定路径，并以特定的日期格式作为图片的名称
//        File imageFile = new File(screenCaptureDirectory, (dfImageName.format(new Date()) + ".png"));  //  或者下面的语句也可以
        File imageFile = new File(screenCaptureDirectory + File.separator + (dfImageName.format(new Date()) + ".png"));
        // 以指定的图片格式将截取的图片写到指定的文件
        ImageIO.write(image, "png", imageFile);
        // 自动打开图片
//        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
//            Desktop.getDesktop().open(imageFile);
//                     }
        //  在测试中发现，如果调用截屏的用例执行较慢，或者下一个调用截屏的用例执行较慢，相邻截屏的图片可能很相似，所以需要在截屏操作前后都添加一个时间缓冲
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //   下面这段代码可以展示，JDK内置支持的图片格式
    public void pictureGeShi() {
        // ImageIO.getReaderFormatNames();  得到ImageIo可以读取图片的格式：[JPG, jpg, bmp, BMP, gif, GIF, WBMP, png, PNG, jpeg, wbmp, JPEG]
        String readFormats[] = ImageIO.getReaderFormatNames();
        // ImageIO.getWriterFormatNames();  得到ImageIo可以写图片的格式：[JPG, jpg, bmp, BMP, gif, GIF, WBMP, png, PNG, jpeg, wbmp, JPEG]
        String writeFormats[] = ImageIO.getWriterFormatNames();
        System.out.println("Readers:  " + Arrays.asList(readFormats));
        System.out.println("Writers:  " + Arrays.asList(writeFormats));
    }

}
