package com.itheima.googleplaymark.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.bean.SubjectBean;
import com.itheima.googleplaymark.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/7/20 0020 00:29
 */

public class SubjectAdapter extends BaseAdapter {

    private List<SubjectBean> mShowsItems = new ArrayList<>();

    public SubjectAdapter(List<SubjectBean> showsItems) {
        mShowsItems = showsItems;
    }

    @Override
    public int getCount() {
        return mShowsItems.size();
    }

    @Override
    public SubjectBean getItem(int position) {
        return mShowsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubjectViewHolder subjectViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_subject, parent, false);
            subjectViewHolder = new SubjectViewHolder();
            subjectViewHolder.ivImage = (ImageView) convertView.findViewById(R.id.iv_image);
            subjectViewHolder.tvDes = (TextView) convertView.findViewById(R.id.tv_des);
            convertView.setTag(subjectViewHolder);
        } else {
            subjectViewHolder = (SubjectViewHolder) convertView.getTag();
        }

        //赋值
        subjectViewHolder.tvDes.setText(mShowsItems.get(position).getDes());

        //设置图片
        Utils.SetFadeImage("http://127.0.0.1:8090/image?name="+mShowsItems.get(position).getUrl(),subjectViewHolder.ivImage);

        return convertView;
    }

    class SubjectViewHolder{
        public ImageView ivImage;
        public TextView tvDes;
    }
}
