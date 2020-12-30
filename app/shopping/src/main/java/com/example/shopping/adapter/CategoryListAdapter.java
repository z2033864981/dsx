package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.BanBean;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {
    private final Context context;

    public CategoryListAdapter(Context context, List<BanBean.DataBean.CategoryListBean.GoodsListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.home_item_newgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.new_pic_url);
        TextView name = (TextView) vh.getViewById(R.id.name);
        TextView floor_price = (TextView) vh.getViewById(R.id.floor_price);

        BanBean.DataBean.CategoryListBean.GoodsListBean bean= (BanBean.DataBean.CategoryListBean.GoodsListBean) data;

        Glide.with(context).load(bean.getList_pic_url()).into(image);
        name.setText(bean.getName());
        name.setTextSize(20f);
        floor_price.setText("￥"+bean.getRetail_price());
    }
}
