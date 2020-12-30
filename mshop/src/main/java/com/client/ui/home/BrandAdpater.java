package com.client.ui.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.base.BaseAdapter;
import com.client.model.home.HomeBean;

import java.util.List;
import com.client.R;
import com.client.utils.ImageLoader;
import com.client.utils.TxtUtils;

public class BrandAdpater extends BaseAdapter<HomeBean.DataBean.BrandListBean> {

    public BrandAdpater(Context context,List<HomeBean.DataBean.BrandListBean> list){
        super(context,list);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_brand_item;
    }

    @Override
    protected void bindData(HomeBean.DataBean.BrandListBean data, VH vh) {
        ImageView imgBrand = (ImageView) vh.getViewById(R.id.img_brand);
        ImageLoader.loadImage(data.getNew_pic_url(),imgBrand);
        TextView txtName = (TextView)vh.getViewById(R.id.txt_brand_name);
        TxtUtils.setTextView(txtName,data.getName());
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_brand_price);
        String price = data.getFloor_price()+"元起";
        TxtUtils.setTextView(txtPrice,price);
    }
}
