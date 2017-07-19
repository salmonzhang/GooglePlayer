package com.itheima.googleplaymark.adapter;

import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/7/18 0018 20:40
 */

public class HomeAdapter extends BaseAdapter {
    private List<HomeBean.HomeItem> datas = new ArrayList<>();

    public HomeAdapter(List<HomeBean.HomeItem> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public HomeBean.HomeItem getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeViewHolder homeViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
            homeViewHolder = new HomeViewHolder();
            homeViewHolder.ivHomeIcon = (ImageView) convertView.findViewById(R.id.iv_home_icon);
            homeViewHolder.tvHomeTitle = (TextView) convertView.findViewById(R.id.tv_home_title);
            homeViewHolder.rbHomeStart = (RatingBar) convertView.findViewById(R.id.rb_home_start);
            homeViewHolder.tvHomeSize = (TextView) convertView.findViewById(R.id.tv_home_size);
            homeViewHolder.tvHomeDesc = (TextView) convertView.findViewById(R.id.tv_home_desc);
            convertView.setTag(homeViewHolder);
        } else {
            homeViewHolder = (HomeViewHolder) convertView.getTag();
        }
        //赋值
        homeViewHolder.tvHomeTitle.setText(datas.get(position).getName());
        //文件大小格式化
        String fileSize = Formatter.formatFileSize(parent.getContext(),datas.get(position).getSize());
        homeViewHolder.tvHomeSize.setText(fileSize);
        homeViewHolder.tvHomeDesc.setText(datas.get(position).getDes());

        homeViewHolder.rbHomeStart.setRating(datas.get(position).getStars());
        return convertView;
    }

    class HomeViewHolder{
        public ImageView ivHomeIcon;
        public TextView tvHomeTitle;
        public RatingBar rbHomeStart;
        public TextView tvHomeSize;
        public TextView tvHomeDesc;
    }
}
