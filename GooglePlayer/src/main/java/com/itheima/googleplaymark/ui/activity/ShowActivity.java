package com.itheima.googleplaymark.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.itheima.googleplaymark.R;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/8/4 0004 10:56
 */

public class ShowActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //获取传过来的Class
        Class className = (Class) getIntent().getSerializableExtra("className");

        //获取传入的数据
        String data = getIntent().getStringExtra("str");


        try {
            //使用反射获取到传过来的对象
            Fragment fragment = (Fragment) className.newInstance();

            //将获取到的数据传入到Fragment中
            Bundle args = new Bundle();
            args.putString("data", data);
            fragment.setArguments(args);

            //使用事务对ShowActivity的布局进行控制
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_show_layout, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
