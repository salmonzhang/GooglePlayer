package com.itheima.googleplaymark.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.interfaces.BodyType;
import com.itheima.googleplaymark.interfaces.HeadType;
import com.itheima.googleplaymark.interfaces.ItemType;
import com.itheima.googleplaymark.viewholder.BaseViewHolder;
import com.itheima.googleplaymark.viewholder.CategoryBodyViewHolder;
import com.itheima.googleplaymark.viewholder.CategoryHeadViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:分类界面多条目ListView
 * Date:2017/8/1 0001 15:57
 */

public class CategoryAdapter extends BaseAdapter{

    private List<ItemType> mShowItems = new ArrayList<>();

    public CategoryAdapter(List<ItemType> showItems) {
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
        BaseViewHolder baseViewHolder = null;
        if (convertView == null) {
            View view = null;
            //根据条目位置，返回不同的view
            switch (getItemViewType(position)) {
                case HEADTYPE:
                    //返回头部的view
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_title, null);
                    baseViewHolder = new CategoryHeadViewHolder();
                    break;
                case BODYTYPE:
                    //返回身体的view
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_info, null);
                    baseViewHolder = new CategoryBodyViewHolder();
                    break;
            }
        }

        //根据条目类型赋值
        switch (getItemViewType(position)) {
            case HEADTYPE:
                baseViewHolder.bindView(mShowItems.get(position));
                break;
            case BODYTYPE:
                baseViewHolder.bindView(mShowItems.get(position));
                break;
            default:
                break;
        }

        return baseViewHolder.getView();
    }

    //定义多条目类型
    public static final int HEADTYPE = 0;//头
    public static final int BODYTYPE = 1;//身体

    //获取条目类型
    @Override
    public int getItemViewType(int position) {
        //根据条目位置，返回条目类型
        if (mShowItems.get(position) instanceof HeadType) {
            return HEADTYPE;
        }
        if (mShowItems.get(position) instanceof BodyType) {
            return BODYTYPE;
        }
        return BODYTYPE;
    }

    //条目总数
    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
