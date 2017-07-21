package com.itheima.googleplaymark.viewholder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.bean.HomeBean;
import com.itheima.googleplaymark.gloab.GooglePlay;
import com.itheima.googleplaymark.utils.Utils;

/**
 * author:salmonzhang
 * Description:
 * Date:2017/7/21 0021 19:39
 */

/**
 * Adapter抽取的基本步骤：
 * 1：将getView中的成员变量移到HomeViewHolder的构造函数中进行初始化
 * 2：将getView中剩下的convertView中的两行变成一行
 * 3：将new HomeViewHolder构造函数中传入的值直接移到HomeViewHolder中
 * 4：将赋值的操作提取为一个方法，然后将方法放在HomeViewHolder中
 * 5：在HomeViewHolder中返回getView方法，避免return convertView时出现空指针
 */

public class HomeViewHolder {

    private ImageView ivHomeIcon;
    private TextView tvHomeTitle;
    private RatingBar rbHomeStart;
    private TextView tvHomeSize;
    private TextView tvHomeDesc;

    private View view;
    //在构造函数中初始化
    public HomeViewHolder() {
        //用填充器填充布局
        view = View.inflate(GooglePlay.context, R.layout.item_home, null);
        //初始化成员变量
        ivHomeIcon = (ImageView) view.findViewById(R.id.iv_home_icon);
        tvHomeTitle = (TextView) view.findViewById(R.id.tv_home_title);
        rbHomeStart = (RatingBar) view.findViewById(R.id.rb_home_start);
        tvHomeSize = (TextView) view.findViewById(R.id.tv_home_size);
        tvHomeDesc = (TextView) view.findViewById(R.id.tv_home_desc);
        //绑定
        view.setTag(this);
    }

    public void bindView(HomeBean.HomeItem homeItem) {
        tvHomeTitle.setText(homeItem.getName());
        //文件大小格式化
        String fileSize = Formatter.formatFileSize(GooglePlay.context,homeItem.getSize());
        tvHomeSize.setText(fileSize);
        tvHomeDesc.setText(homeItem.getDes());
        rbHomeStart.setRating(homeItem.getStars());
        Utils.SetRoundedImage("http://127.0.0.1:8090/image?name="+homeItem.getIconUrl(), ivHomeIcon);
    }

    //定义一个getView方法，返回到getView中
    public View getView() {
        return view;
    }

}
