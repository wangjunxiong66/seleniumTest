package com.wjxselenium.demo;

import com.wjxselenium.enums.OSPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@RestController
public class VerifyImgLogin {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    /*
     * 根据url下载验证码图片并保存到指定的地址
     * @imgUrl 图片下载地址
     * @imgPath 保存路径
     */
    @RequestMapping(value = "yanzheng",method = RequestMethod.GET)
    public void download(String imgUrl,String imgPath) {
//    public void download() {
//        String imgUrl = null;
//        String imgPath = null;
//        imgUrl = "http://m.iqianjin.com/user/getCode?v=1587955675252";
        FileOutputStream outStream = null;
                String osName = System.getProperty("os.name");
        if (osName.equals(OSPlatform.getByValue(3).getDesc())||osName.equals(OSPlatform.getByValue(4).getDesc()))
        {
            imgPath = System.getProperty("user.dir")+"/src/main/resources/static/img/verificationcode.png";
            System.out.println("系统是 "+OSPlatform.getByValue(4).getDesc()+" ， imgPath = "+imgPath);
        } else if (osName.equals("Windows")){
            //  如果失败，可能是Windows操作系统的32位和64位不兼容，切换一下指定的geckodriver试一下
            imgPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\geckodriver\\geckodriver-v0.24.0-win64.exe";
            System.out.println("系统不是Mac OS X  ， imgPath = "+imgPath);
        }

        try {
            //打开链接
            URL url = new URL(imgUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data =readInputStream(inStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(imgPath);
            //创建输出流
            outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 读取的文件流
    * @batPath batPath 存放的二进制图片路径
    *
    */
    public byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /*
     * java运行tesseract批量处理bat文件​，产生TXT文件保存到本地
     * @batPath batPath 存放的二进制图片路径
     *
     */
//    public void runTesseractBat(String batPath) throws InterruptedException{
    public void runTesseractBat() throws InterruptedException{
//        String cmd = batPath;
        String cmd = null;
        String font = null;
        String fonttif = null;
        String fontbox = null;
        String fontunicharset = null;
        String fonttr = null;
        String fontnormproto = null;
        String fontinttemp = null;
        String fontpffmtable = null;
        String fontshapetable = null;

        String osName = System.getProperty("os.name");
        if (osName.equals(OSPlatform.getByValue(3).getDesc())||osName.equals(OSPlatform.getByValue(4).getDesc()))
        {
            cmd  = System.getProperty("user.dir")+"/src/main/resources/static/script/test.bat";
            font = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font";
            fonttif = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font.tif";
            fontbox = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font.box";
            fontunicharset  = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font.unicharset";
            fonttr = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font.tr";
            fontnormproto = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font.normproto";
            fontinttemp  = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font.inttemp";
            fontpffmtable = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font.pffmtable";
            fontshapetable = System.getProperty("user.dir")+"/src/main/resources/static/screenshot/font.shapetable";
        } else if (osName.equals("Windows")){
            //  如果失败，可能是Windows操作系统的32位和64位不兼容，切换一下指定的geckodriver试一下
            cmd  = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\script/test.bat";
            font = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font";
            fonttif = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font.tif";
            fontbox = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font.box";
            fontunicharset  = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font.unicharset";
            fonttr = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font.tr";
            fontnormproto = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font.normproto";
            fontinttemp  = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font.inttemp";
            fontpffmtable = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font.pffmtable";
            fontshapetable = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\creenshot\\font.shapetable";
        }

        Thread.sleep(2000);
        try {
            Runtime.getRuntime().exec(cmd);
        }
        catch (IOException e) {
// TODO Auto-generated catch block
            LOG.error("运行tesseract批量处理bat文件异常", e);
        }
    }

    /*
     * 读取得到的txt文档读取获取到的文本内容
     * @batPath batPath 存放的二进制图片路径
     *
     */
//    public static String readTextFile(String filePath) {
    public String readTextFile(String filePath) {
        String IdentificateTxt = null;
        String lineTxt = null;
        try {
            String encoding = "GBK";
            File file = new File(filePath);

            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    LOG.info(lineTxt);
                    IdentificateTxt = lineTxt;
                }
                read.close();
            } else {
                LOG.error("----找不到指定的文件");
            }
        } catch (Exception e) {
            LOG.error("读取文件内容出错",e);
        }
        return IdentificateTxt;

    }

}
