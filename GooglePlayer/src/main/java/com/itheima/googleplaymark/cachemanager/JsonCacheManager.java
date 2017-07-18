package com.itheima.googleplaymark.cachemanager;

import android.text.TextUtils;

import com.itheima.googleplaymark.utils.GsonUtil;

/**
 * author:salmonzhang
 * Description:Json缓存框架
 * Date:2017/7/18 0018 12:41
 */

public class JsonCacheManager {

    private static JsonCacheManager singleton = new JsonCacheManager();
    private JsonCacheManager(){}
    public static JsonCacheManager getInstance(){
           return singleton;
    }

    //获取缓存数据
    public<T> T getCacheData(String url,Class<T> clss){
        //1.获取网络数据
        String content = NetManager.getInstance().getNetData(url);

        //2.对获取到的数据进行判空
        if (TextUtils.isEmpty(content)) {
            //如果获取到的数据为空，则从缓存中去拿缓存数据
            content = FileManager.getInstance().readData(url);
        } else {
            //如果获取到的数据不为空，则从缓存中更新最新的数据
            FileManager.getInstance().writeData(url,content);
        }

        //3.解析数据
        if (TextUtils.isEmpty(content)) {
            //如果为空，则返回null
            return null;
        } else {
            //如果不为空，解析成json数据
            return GsonUtil.parseJsonToBean(content, clss);
        }
    }
}
