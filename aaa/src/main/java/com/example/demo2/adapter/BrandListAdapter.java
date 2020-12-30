package com.example.demo2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.OneBean;

import java.util.List;

public class BrandListAdapter extends BaseAdapter {
    private List<OneBean.DataBean.BrandListBean> listBeans;
    private Context context;

    public BrandListAdapter(Context context, List data) {
        super(context, data);
        this.listBeans=data;
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        OneBean.DataBean.BrandListBean bean= (OneBean.DataBean.BrandListBean) data;
        ImageView ivbrandimg = (ImageView) vh.getViewById(R.id.iv_brand_img);
        TextView tvbrandname = (TextView) vh.getViewById(R.id.tv_brand_name);
        TextView tvbrandfloorprice = (TextView) vh.getViewById(R.id.tv_brand_floor_price);
        Glide.with(context).load(bean.getNew_pic_url()).into(ivbrandimg);
        tvbrandname.setText(bean.getName());
        tvbrandfloorprice.setText(bean.getFloor_price()+"元起");

    }
}
