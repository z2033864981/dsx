package com.example.demo2.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo2.R;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.bean.CategoryBottomInfoBean;
import com.example.demo2.utils.ImageLoader;
import com.example.demo2.utils.TxtUtils;

import java.util.List;

public class CategoryButtomInfoAdapter extends BaseAdapter {

    public CategoryButtomInfoAdapter(Context context, List Data) {
        super(context, Data);
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
