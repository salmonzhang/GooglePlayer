package com.itheima.googleplaymark.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.itheima.googleplaymark.R;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/8/4 0004 11:01
 */

public class DetailFragment extends BaseFragment {
    @Override
    public View createItemView() {
//        TextView textView = new TextView(getContext());
//        textView.setText("详情界面");

        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.ic_launcher);
        return imageView;
    }

    @Override
    public Object questData() {
        return "";
    }
}
