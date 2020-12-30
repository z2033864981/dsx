package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.ShopBean;

import java.util.List;

import butterknife.BindView;

public class ShopAdapter extends BaseAdapter<ShopBean.DataBeanX.DataBean> {
    ImageView img;
    TextView tv;
    TextView tv1;
    TextView tv2;
    private Context context;

    public ShopAdapter(Context context, List<ShopBean.DataBeanX.DataBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.shop_item;
    }

    @Override
    protected void bindData(ShopBean.DataBeanX.DataBean data, VH vh) {
        img=vh.itemView.findViewById(R.id.img);
        tv=vh.itemView.findViewById(R.id.tv);
        tv1=vh.itemView.findViewById(R.id.tv1);
        tv2=vh.itemView.findViewById(R.id.tv2);
        Glide.with(context).load(data.getScene_pic_url()).into(img);
        tv.setText(data.getTitle());
        tv1.setText(data.getSubtitle());
        tv2.setText(data.getPrice_info()+"元起");
    }
}
