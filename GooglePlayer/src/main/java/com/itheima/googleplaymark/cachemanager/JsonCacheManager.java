package com.itheima.googleplaymark.cachemanager;

import android.text.TextUtils;

import com.itheima.googleplaymark.utils.GsonUtil;

/**
 * author:salmonzhang
 * Description:Json缓存框架
 * Date:2017/7/18 0018 12:41
 */

public class JsonCacheManager {

    //获取缓存数据
    public Object getCacheData(String url,Class clss){
        //1.获取网络数据
        NetManager netManager = new NetManager();
        String content = netManager.getNetData(url);

        //2.对获取到的数据进行判空
        if (TextUtils.isEmpty(content)) {
            //如果获取到的数据为空，则从缓存中去拿缓存数据
            FileManager fileManager = new FileManager();
            fileManager.readData(url);
        } else {
            //如果获取到的数据不为空，则从缓存中更新最新的数据
            FileManager fileManager = new FileManager();
            fileManager.writeData(url,content);
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
