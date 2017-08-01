package com.itheima.googleplaymark.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:分类界面多条目ListView
 * Date:2017/8/1 0001 15:57
 */

public class CategoryAdapter extends BaseAdapter{

    private List<Object> mShowItems = new ArrayList<>();

    public CategoryAdapter(List<Object> showItems) {
        mShowItems = showItems;
    }

    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mShowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //根据条目位置，返回不同的view
        switch (getItemViewType(position)) {
            case HEADTYPE:
                //返回头部的view
                break;
            case BODYTYPE:
                //返回身体的view
                break;
        }
        return null;
    }

    //定义多条目类型
    public static final int HEADTYPE = 0;//头
    public static final int BODYTYPE = 1;//身体

    //获取条目类型
    @Override
    public int getItemViewType(int position) {
        //根据条目位置，返回条目类型
        if (mShowItems.get(position) instanceof String) {
            return HEADTYPE;
        } else {
            return BODYTYPE;
        }
    }

    //条目总数
    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
