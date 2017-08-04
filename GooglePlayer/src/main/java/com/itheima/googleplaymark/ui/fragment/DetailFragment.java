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
        //获取ShowActivity中传过来的Bundle数据
        Bundle bundle = getArguments();
        String string = bundle.getString("data");
        int num = bundle.getInt("num");
        TextView textView = new TextView(getContext());
        textView.setText(string+"......."+num);

        return textView;
    }

    @Override
    public Object questData() {
        return "";
    }
}
