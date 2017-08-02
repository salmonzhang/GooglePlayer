package com.itheima.googleplaymark.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
import com.itheima.googleplaymark.utils.ToastUtil;
import com.itheima.googleplaymark.utils.Utils;
import com.itheima.googleplaymark.view.FlowLayout;

import java.util.List;

/**
 * author:salmonzhang
 * Description:热门界面的Fragment(使用流式布局)
 * Date:2017/8/3 0003 00:35
 */

public class HotFragment extends BaseFragment {

    private FlowLayout mFlowLayout;

    @Override
    public View createItemView() {
        //让布局滚动
        ScrollView scrollView = new ScrollView(getContext());
        //使用流式布局
        mFlowLayout = new FlowLayout(getContext());
        //给流式布局设置padding
        mFlowLayout.setPadding(Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10));
        //将流式布局添加到scrollView中
        scrollView.addView(mFlowLayout);
        return scrollView;
    }

    @Override
    public Object questData() {
        //使用网络框架请求数据
        final List<String> stringList = JsonCacheManager.getInstance().getCacheList(" http://127.0.0.1:8090/hot?index=0", String.class);

        //在主线程中修改UI界面
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < stringList.size(); i++) {
                    TextView textView = new TextView(getContext());
                    textView.setText(stringList.get(i));

                    //动态生成两张shape图片
                    GradientDrawable gradientDrawable1 = Utils.getGradientDrawable();
                    GradientDrawable gradientDrawable2 = Utils.getGradientDrawable();

                    //图片选择器
                    StateListDrawable stateListDrawable = new StateListDrawable();
                    //按下的图片
                    stateListDrawable.addState(new int[]{android.R.attr.state_pressed},gradientDrawable1);
                    //默认图片
                    stateListDrawable.addState(new int[]{},gradientDrawable2);

                    //设置退出、进入动画
                    stateListDrawable.setExitFadeDuration(500);
                    stateListDrawable.setEnterFadeDuration(500);

                    //给Textview设置背景图片
                    textView.setBackgroundDrawable(stateListDrawable);

                    //给Textview设置padding
                    textView.setPadding(Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10));

                    //设置文字颜色
                    textView.setTextColor(Color.WHITE);

                    //设置文字居中
                    textView.setGravity(Gravity.CENTER);

                    //给Textview设置点击事件
                    textView.setTag(i);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tv = (TextView) v;
                            ToastUtil.showToast(tv.getText().toString() + (int)tv.getTag());
                        }
                    });

                    //将Textview添加到mFlowLayout中
                    mFlowLayout.addView(textView);
                }
            }
        });
        return stringList;
    }
}
