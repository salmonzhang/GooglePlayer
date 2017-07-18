package com.itheima.googleplaymark.cachemanager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:salmonzhang
 * Description:网络管理类
 * Date:2017/7/18 0018 12:43
 */

public class NetManager {

    public String getNetData(String url) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            //返回网络数据
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
