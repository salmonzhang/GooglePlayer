package com.itheima.googleplaymark.utils;

/**
 * author:salmonzhang
 * Description:uri常量类
 * Date:2017/7/20 0020 20:41
 */

public class Uris {
    //主机地址
    public static final String HOSEADDRESS = "http://127.0.0.1:8090";
    //首页地址
    public static final String  HOMEADDRESS = HOSEADDRESS+"/home?index=";
    //专题地址
    public static final String SUBJECTADDRESS = HOSEADDRESS+"/subject?index=0";
    //图片前缀
    public static final String IMAGEFOREAD = HOSEADDRESS+"/image?name=";
    //分类地址
    public static final String CATEGORY = HOSEADDRESS + "/category?index=0";
    //推荐地址
    public static final String RECOMMEND = HOSEADDRESS + "/recommend?index=0";
}
