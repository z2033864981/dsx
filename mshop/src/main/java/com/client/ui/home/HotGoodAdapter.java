package com.client.ui.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.client.base.BaseAdapter;
import com.client.model.home.HomeBean;
import com.client.utils.ImageLoader;
import com.client.utils.TxtUtils;

import java.util.List;

public class HotGoodAdapter extends BaseAdapter<HomeBean.DataBean.HotGoodsListBean> {

    public HotGoodAdapter(Context context, List<HomeBean.DataBean.HotGoodsListBean> list){
        super(context,list);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_hotgood_item;
    }

    @Override
    protected void bindData(HomeBean.DataBean.HotGoodsListBean data, VH vh) {
        ImageView imgHotGood = (ImageView) vh.getViewById(R.id.img_hotgood);
        ImageLoader.loadImage(data.getList_pic_url(),imgHotGood);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_hotgood_name);
        TxtUtils.setTextView(txtName,data.getName());
        TextView txtDes = (TextView) vh.getViewById(R.id.txt_hotgood_des);
        TxtUtils.setTextView(txtDes,data.getGoods_brief());
        String price = "$"+data.getRetail_price();
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_hotgood_price);
        TxtUtils.setTextView(txtPrice,price);

    }
}
