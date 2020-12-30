package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.moudel.bean.BanBean;

import java.util.List;

public class ManufacturerAdapter extends BaseAdapter<BanBean.DataBean.BrandListBean> {

    ImageView iamgeBrand;

    TextView nameBrand;

    TextView priceBrand;
    private Context context;
    private List<BanBean.DataBean.BrandListBean> data;

    public ManufacturerAdapter(Context context, List<BanBean.DataBean.BrandListBean> data) {
        super(context, data);
        this.context = context;
        this.data = data;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_home_brand;
    }

    @Override
    protected void bindData(BanBean.DataBean.BrandListBean data, VH vh) {
        iamgeBrand= vh.itemView.findViewById(R.id.iamge_brand);
        nameBrand= vh.itemView.findViewById(R.id.name_brand);
        priceBrand= vh.itemView.findViewById(R.id.price_brand);
        Glide.with(context).load(data.getNew_pic_url()).into(iamgeBrand);
        nameBrand.setText(data.getName());
        priceBrand.setText(data.getFloor_price());
    }
}
