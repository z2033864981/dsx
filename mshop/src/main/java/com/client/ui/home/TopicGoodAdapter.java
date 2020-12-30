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

public class TopicGoodAdapter extends BaseAdapter<HomeBean.DataBean.TopicListBean> {
    public TopicGoodAdapter(Context context, List<HomeBean.DataBean.TopicListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_topic_item;
    }

    @Override
    protected void bindData(HomeBean.DataBean.TopicListBean data, VH vh) {
        ImageView imgTopic = (ImageView) vh.getViewById(R.id.img_topic);
        ImageLoader.loadImage(data.getItem_pic_url(),imgTopic);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_topic_name);
        String name = data.getTitle() + "   ￥" + data.getPrice_info()+"元起";
        TxtUtils.setTextView(txtName,name);
        TextView txtDes = (TextView) vh.getViewById(R.id.txt_topic_des);
        TxtUtils.setTextView(txtDes,data.getSubtitle());
    }
}
