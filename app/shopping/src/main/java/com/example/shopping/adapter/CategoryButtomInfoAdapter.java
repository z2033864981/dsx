package com.example.shopping.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.CategoryBottomInfoBean;
import com.example.shopping.utils.ImageLoader;
import com.example.shopping.utils.TxtUtils;

import java.util.List;


public class CategoryButtomInfoAdapter extends BaseAdapter {


    public CategoryButtomInfoAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        CategoryBottomInfoBean.DataBean.GoodsListBean bean= (CategoryBottomInfoBean.DataBean.GoodsListBean) data;

        ImageView image = (ImageView) vh.getViewById(R.id.iv_category_img);
        TextView name = (TextView) vh.getViewById(R.id.tv_category_name);
        TextView floor_price = (TextView) vh.getViewById(R.id.tv_category_price);

        ImageLoader.loadImage(bean.getList_pic_url(),image);
        TxtUtils.setTextView(name,bean.getName());
        floor_price.setText("￥"+bean.getRetail_price());
    }
}
