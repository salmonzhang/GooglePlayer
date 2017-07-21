package com.itheima.googleplaymark.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.bean.SubjectBean;
import com.itheima.googleplaymark.gloab.GooglePlay;
import com.itheima.googleplaymark.utils.Utils;

/**
 * author:salmonzhang
 * Description:专题界面的viewHolder
 * Date:2017/7/21 0021 20:02
 */

public class SubjectViewHolder{
    public ImageView ivImage;
    public TextView tvDes;
    View view;
    public SubjectViewHolder() {
        view = View.inflate(GooglePlay.context, R.layout.adapter_subject, null);
        ivImage = (ImageView) view.findViewById(R.id.iv_image);
        tvDes = (TextView) view.findViewById(R.id.tv_des);
        //绑定
        view.setTag(this);
    }

    public void bindView(SubjectBean subjectBean) {
        //赋值
        tvDes.setText(subjectBean.getDes());
        //设置图片
        Utils.SetFadeImage("http://127.0.0.1:8090/image?name="+subjectBean.getUrl(),ivImage);
    }

    //定义一个getView方法，返回到getView中
    public View getView() {
        return view;
    }
}
