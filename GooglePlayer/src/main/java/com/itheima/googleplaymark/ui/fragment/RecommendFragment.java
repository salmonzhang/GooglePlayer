package com.itheima.googleplaymark.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.cachemanager.JsonCacheManager;
import com.itheima.googleplaymark.gloab.GooglePlay;
import com.itheima.googleplaymark.ui.views.randomlayout.StellarMap;
import com.itheima.googleplaymark.utils.Uris;
import com.itheima.googleplaymark.utils.Utils;

import java.util.List;
import java.util.Random;

/**
 * author:salmonzhang
 * Description:推荐界面（飞入飞出飞窗效果）
 * Date:2017/8/1 0001 23:16
 */

public class RecommendFragment extends BaseFragment {

    private StellarMap mStellarMap;

    @Override
    public View createItemView() {
        mStellarMap = new StellarMap(GooglePlay.context);
        return mStellarMap;
    }

    @Override
    public Object questData() {
        //从网络获取数据
        final List<String> titles = JsonCacheManager.getInstance().getCacheList(Uris.RECOMMEND, String.class);
        if (titles == null && titles.size() == 0) {
            return null;
        }

        //数据不为空时，在主线程中修改UI
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                //给mStellarMap设置适配器
                mStellarMap.setAdapter(new StellarMap.Adapter() {
                    //得到组数
                    @Override
                    public int getGroupCount() {
                        return 3;
                    }

                    //得到每组中的个数
                    @Override
                    public int getCount(int group) {
                        return 11;
                    }

                    @Override
                    public View getView(int group, int position, View convertView) {
                        TextView textView = new TextView(GooglePlay.context);
                        int index = group * getCount(group) + position;
                        textView.setText(titles.get(index));
                        //设置文字大小
                        textView.setTextSize(getRandomTextSize());
                        //设置文字颜色
                        textView.setTextColor(getRandomTextColor());
                        return textView;
                    }

                    //用不到
                    @Override
                    public int getNextGroupOnPan(int group, float degree) {
                        return 0;
                    }

                    //告诉控件下一组是谁
                    @Override
                    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
                        return (group+1)%getGroupCount();
                    }
                });

                //设置第一组显示
                mStellarMap.setGroup(0, true);
                //设置格子数
                mStellarMap.setRegularity(11, 11);
                //设置padding
                mStellarMap.setInnerPadding(Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10),Utils.getDimens(R.dimen.dp10));
            }
        });

        return "";
    }


    private int getRandomTextColor() {
        int red = getRandomColorValue();
        int green = getRandomColorValue();
        int blue = getRandomColorValue();
        return Color.rgb(red,green,blue);
    }

    //设置文字颜色为0-180
    private int getRandomColorValue() {
        Random random = new Random();
        return random.nextInt(180);
    }

    //文字大小设置为14-24
    private int getRandomTextSize() {
        Random random = new Random();
        return random.nextInt(10)+14;
    }
}
