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

public class NewGoodAdapter extends BaseAdapter<HomeBean.DataBean.NewGoodsListBean> {

    public NewGoodAdapter(Context context, List<HomeBean.DataBean.NewGoodsListBean> list){
        super(context,list);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_newgood_item;
    }

    @Override
    protected void bindData(HomeBean.DataBean.NewGoodsListBean data, VH vh) {
        ImageView imgNewGood = (ImageView) vh.getViewById(R.id.img_newgood);
        ImageLoader.loadImage(data.getList_pic_url(),imgNewGood);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_newgood_name);
        TxtUtils.setTextView(txtName,data.getName());
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_newgood_price);
        String price = "$"+data.getRetail_price();
        TxtUtils.setTextView(txtPrice,price);
    }
}
