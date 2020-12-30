package com.client.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.R;
import com.client.base.BaseAdapter;
import com.client.model.home.CategoryGoodBean;
import com.client.model.home.HomeBean;
import com.client.ui.shop.CarActivity;
import com.client.utils.TxtUtils;

import java.util.List;

public class CategoryAdapter extends BaseAdapter<HomeBean.DataBean.CategoryListBean> {
    public CategoryAdapter(Context context, List<HomeBean.DataBean.CategoryListBean> data, Activity activity) {
        super(context, data,activity);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_item;
    }

    @Override
    protected void bindData(HomeBean.DataBean.CategoryListBean data, VH vh) {
        TextView txtTitle = (TextView) vh.getViewById(R.id.txt_category_title);
        TxtUtils.setTextView(txtTitle,data.getName());
        RecyclerView recyclerView = (RecyclerView) vh.getViewById(R.id.recy_category);
        GoodAdapter goodAdapter = new GoodAdapter(context,data.getGoodsList());
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(goodAdapter);

        goodAdapter.addListClick(new IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(activity, CarActivity.class);
                intent.putExtra("goodid", data.getGoodsList().get(pos).getId());
                activity.startActivityForResult(intent,100);
            }
        });
    }

}
