package com.itheima.googleplaymark.viewholder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplaymark.R;
import com.itheima.googleplaymark.bean.HomeBean;
import com.itheima.googleplaymark.gloab.GooglePlay;
import com.itheima.googleplaymark.utils.Uris;
import com.itheima.googleplaymark.utils.Utils;

import butterknife.BindView;

/**
 * author:salmonzhang
 * Description:首页界面的ViewHolder
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

//使用BaseViewHolder
public class HomeViewHolder extends BaseViewHolder<HomeBean.HomeItem> {

    @BindView(R.id.iv_home_icon)
    ImageView mIvHomeIcon;
    @BindView(R.id.rb_home_start)
    RatingBar mRbHomeStart;
    @BindView(R.id.tv_home_title)
    TextView mTvHomeTitle;
    @BindView(R.id.tv_home_size)
    TextView mTvHomeSize;
    @BindView(R.id.tv_home_desc)
    TextView mTvHomeDesc;

    //创建布局
    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.item_home, null);
        return view;
    }

    //绑定数据
    @Override
    public void bindView(HomeBean.HomeItem homeItem) {
        mTvHomeTitle.setText(homeItem.getName());
        mTvHomeSize.setText(Formatter.formatFileSize(GooglePlay.context, homeItem.getSize()));
        mTvHomeDesc.setText(homeItem.getDes());
        mRbHomeStart.setRating(homeItem.getStars());
        Utils.SetRoundedImage(Uris.IMAGEFOREAD+homeItem.getIconUrl(), mIvHomeIcon);
    }
}
