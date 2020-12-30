package com.client.ui.test;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.client.base.BaseAdapter;
import com.client.model.test.TestBean;

import java.util.HashMap;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private HashMap<String, TestBean.DataBean> map = new HashMap<>();

    public MyAdapter(Context context, List<TestBean.DataBean> list){
        super(context,list);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TestBean.DataBean item = (TestBean.DataBean) data;
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        txtName.setText(item.getName());
        ImageView img = (ImageView) vh.getViewById(R.id.img);
        img.setTag(item);
        updateImg(img,item.currentSelect);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestBean.DataBean bean = (TestBean.DataBean) v.getTag();
                bean.currentSelect = !bean.currentSelect; //取反
                updateImg(img,bean.currentSelect);
                //把当前有修改的数据存下来
                if(map.containsKey(bean.getName())){
                    map.remove(bean.getName());
                }else{
                    map.put(bean.getName(),bean);
                }
            }
        });
    }

    public HashMap<String, TestBean.DataBean> getMap(){
        return map;
    }

    private void updateImg(ImageView img,boolean bool){
        if(bool){
            img.setImageResource(R.mipmap.check_box_select);
        }else{
            img.setImageResource(R.mipmap.check_box_normal);
        }
    }
}
