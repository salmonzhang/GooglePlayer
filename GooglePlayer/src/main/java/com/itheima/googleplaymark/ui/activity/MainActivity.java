package com.itheima.googleplaymark.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itheima.googleplaymark.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {


        /**
         * 设置标题属性
         */
//        //得到ActionBar
//        ActionBar actionBar = getSupportActionBar();
//        //更改标题
//        actionBar.setTitle("谷歌市场");
//        //显示箭头
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

        /**
         * 让actionbar与侧滑菜单进行联动
         */
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDl_main_root_layout, R.string.open, R.string.close);

    }
}
