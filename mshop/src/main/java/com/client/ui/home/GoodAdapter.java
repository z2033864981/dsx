package com.client.ui.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.client.base.BaseAdapter;
import com.client.model.home.HomeBean;
import com.client.utils.ImageLoader;
import com.client.utils.TxtUtils;

import java.util.List;

public class GoodAdapter extends BaseAdapter<HomeBean.DataBean.CategoryListBean.GoodsListBean> {
    public GoodAdapter(Context context, List<HomeBean.DataBean.CategoryListBean.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_good_item;
    }

    @Override
    protected void bindData(HomeBean.DataBean.CategoryListBean.GoodsListBean data, VH vh) {
        ImageView imgGood = (ImageView) vh.getViewById(R.id.img_good);
        ImageLoader.loadImage(data.getList_pic_url(),imgGood);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_good_name);
        TxtUtils.setTextView(txtName,data.getName());
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_good_price);
        TxtUtils.setTextView(txtPrice,"$"+data.getRetail_price());

    }
}
