package com.example.demo2.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.OneBean;

import java.util.List;

public class CategroyListAdapter extends BaseAdapter {

    public CategroyListAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.home_item_newgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        OneBean.DataBean.CategoryListBean.GoodsListBean bean= (OneBean.DataBean.CategoryListBean.GoodsListBean) data;
        ImageView image = (ImageView) vh.getViewById(R.id.new_pic_url);
        TextView name = (TextView) vh.getViewById(R.id.name);
        TextView floor_price = (TextView) vh.getViewById(R.id.floor_price);


        Glide.with(context).load(bean.getList_pic_url()).into(image);
        name.setText(bean.getName());
        floor_price.setText("￥"+bean.getRetail_price());


    }
}
