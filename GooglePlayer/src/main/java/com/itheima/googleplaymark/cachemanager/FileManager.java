package com.itheima.googleplaymark.cachemanager;

import android.os.Environment;

import com.itheima.googleplaymark.gloab.GooglePlay;
import com.itheima.googleplaymark.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * author:salmonzhang
 * Description:文件读写管理类
 * Date:2017/7/18 0018 12:48
 */

public class FileManager {
    private static FileManager singleton = new FileManager();
    private final String mFilePath;

    private FileManager(){
        //获取文件路径
        mFilePath = Environment.getExternalStorageDirectory().getPath() + File.separator + GooglePlay.context.getPackageName();
        //创建文件目录
        File fileDir = new File(mFilePath);
        //判断文件是否存在
        if (!fileDir.exists()) {
            fileDir.mkdirs();//如果不存在，则手动创建多级目录
        }
    }
    public static FileManager getInstance(){
           return singleton;
    }

    //文件写数据
    public void writeData(String url, String content) {
        //将数据写入到SD卡中
        try {
            //在文件目录下，创建文件
            File file = new File(mFilePath,Utils.getFileName(url));
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //文件读数据
    public String readData(String url) {
        //从SD卡中读取数据

        try {
            //在文件目录下，创建文件
            File file = new File(mFilePath,Utils.getFileName(url));
            FileInputStream fis = new FileInputStream(file);
            int len = -1;
            byte[] buffer = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer();
            while ((len = fis.read(buffer)) != -1) {
                stringBuffer.append(new String(buffer, 0, len));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
