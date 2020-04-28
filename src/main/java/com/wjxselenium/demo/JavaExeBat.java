package com.wjxselenium.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaExeBat {
    public static void main(String[] args)
    {
        Process p;
        //mac系统下，test.bat中的命令是ifconfig，需要先给test.bat赋权限，chmod 777 test.bat，否则执行的时候提示拒绝
        String cmd="/Users/finup/Documents/D/test.bat";
        //mWindows系统下，test.bat中的命令是ipconfig
        String cmd1="c:\\test\\test.bat";

        try
        {
            //执行命令
            p = Runtime.getRuntime().exec(cmd);
            //取得命令结果的输出流
            InputStream fis=p.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr=new InputStreamReader(fis);
            //用缓冲器读行
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            //直到读完为止
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
