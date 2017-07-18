package com.itheima.googleplaymark.utils;

import android.content.res.Resources;
import android.graphics.Color;

import com.itheima.googleplaymark.gloab.GooglePlay;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static com.itheima.googleplaymark.gloab.GooglePlay.context;


/**
 * Created by heima_sy on 2016/5/19.
 */
public class Utils
{


    //这个是在主线程去更新ui,在没有上下文的环境,
    public static void runOnUIThread(Runnable runnable)
    {
        GooglePlay.mainHandler.post(runnable);
    }

    //得到字符串数组信息
    public static String[] getStringArray(int resId)
    {
        //getResources:R
        return getResources().getStringArray(resId);
    }


    //得到资源管理的类
    public static Resources getResources()
    {
        return context.getResources();
    }

    //在屏幕适配时候使用,让代码中使用dip属性
    public static int getDimens(int resId)
    {

        return getResources().getDimensionPixelSize(resId);
    }

    //得到颜色
    public static int getColor(int resId)
    {
        return getResources().getColor(resId);
    }

    /**
     * 拿到一个随机颜色
     * @return
     */
    public static int createRandomColor() {
        Random random = new Random();
        return random.nextInt(180);
    }

    // 创建一个随机的颜色
    public static int randomColor()
    {

        Random random = new Random();
        int red = random.nextInt(180);
        int blue = random.nextInt(180);
        int green = random.nextInt(180);
        System.out.println(red + ":" + blue + ":" + green);
        return Color.rgb(red, green, blue);
        // return 0;
    }

    //根据url创建一个唯一的文件名
    public static String getFileName(String content) {
        //md5

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes());
            byte[] digest = messageDigest.digest();

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                String hexString = Integer.toHexString(digest[i]&0xFF);
                //System.out.println(hexString);
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //这里暂时不处理,后期根据业务需求去改
            return "";
        }
    }

}
