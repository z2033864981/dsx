package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.GoosListBean;

import java.util.List;

public class NewGoodsAdapter extends BaseAdapter<GoosListBean.DataBeanX.DataBean> {
    private Context context;
    public NewGoodsAdapter(Context context, List<GoosListBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_newgoods;
    }

    @Override
    protected void bindData(GoosListBean.DataBeanX.DataBean data, VH vh) {
        ImageView imageView=vh.itemView.findViewById(R.id.img);
        TextView name=vh.itemView.findViewById(R.id.tv_name);
        TextView tv=vh.itemView.findViewById(R.id.tv_price);
        Glide.with(context).load(data.getList_pic_url()).into(imageView);
        name.setText(data.getName());
        tv.setText(data.getRetail_price()+"");
    }
}
