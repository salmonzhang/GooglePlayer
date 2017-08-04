package com.itheima.googleplaymark.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/8/4 0004 11:01
 */

public class DetailFragment extends BaseFragment {
    @Override
    public View createItemView() {
        Bundle bundle = getArguments();
        String string = bundle.getString("data");
        TextView textView = new TextView(getContext());
        textView.setText(string);

//        ImageView imageView = new ImageView(getContext());
//        imageView.setImageResource(R.mipmap.ic_launcher);
        return textView;
    }

    @Override
    public Object questData() {
        return "";
    }
}
