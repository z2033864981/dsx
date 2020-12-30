package com.example.shopping.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;

import java.util.List;

public class MAdapter extends BaseAdapter<String> {
    private Context context;

    public MAdapter(Context context, List<String> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.imgs;
    }

    @Override
    protected void bindData(String data, VH vh) {
        ImageView img=vh.itemView.findViewById(R.id.img);
        Glide.with(context).load(data).into(img);
    }
}
