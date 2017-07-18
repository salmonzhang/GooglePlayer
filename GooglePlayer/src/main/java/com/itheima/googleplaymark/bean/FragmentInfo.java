package com.itheima.googleplaymark.bean;

import android.support.v4.app.Fragment;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/7/17 0017 20:52
 */

public class FragmentInfo {
    public Fragment fragment;
    public String title;

    public FragmentInfo(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }
}
