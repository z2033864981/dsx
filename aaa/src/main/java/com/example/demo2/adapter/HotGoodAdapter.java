package com.example.demo2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.HotGoodListBean;

import java.util.List;

public class HotGoodAdapter extends BaseAdapter {

    public HotGoodAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_hotgood_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HotGoodListBean.DataBeanX.DataBean bean= (HotGoodListBean.DataBeanX.DataBean) data;
        ImageView ivhotgoodimg = (ImageView) vh.getViewById(R.id.iv_hotgood_img);
        TextView tvhotgoodname = (TextView) vh.getViewById(R.id.tv_hotgood_name);
        TextView tvhotgoodprice = (TextView) vh.getViewById(R.id.tv_hotgood_price);
        Glide.with(context).load(bean.getList_pic_url()).into(ivhotgoodimg);
        tvhotgoodname.setText(bean.getName());
        tvhotgoodprice.setText(bean.getRetail_price());
    }
}
