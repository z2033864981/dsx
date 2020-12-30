package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.BanBean;

import java.util.List;

public class NewGoodAdapter extends BaseAdapter<BanBean.DataBean.NewGoodsListBean> {
    private final Context context;

    public NewGoodAdapter(Context context, List<BanBean.DataBean.NewGoodsListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.newgood;
    }

    @Override
    protected void bindData(BanBean.DataBean.NewGoodsListBean data, VH vh) {
        ImageView imageView=vh.itemView.findViewById(R.id.img);
        TextView tv=vh.itemView.findViewById(R.id.tv1);
        TextView tv2=vh.itemView.findViewById(R.id.tv2);
        Glide.with(context).load(data.getList_pic_url()).into(imageView);
        tv.setText(data.getName());
        tv2.setText(data.getRetail_price()+"");
    }
}
