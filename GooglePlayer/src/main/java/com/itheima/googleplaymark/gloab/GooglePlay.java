package com.itheima.googleplaymark.gloab;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * author:salmonzhang
 * Description:定义一个全局的application
 * Date:2017/7/18 0018 01:01
 */

public class GooglePlay extends Application {
    public static Handler mainHandler;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mainHandler = new Handler();
        context = this;
    }
}
