package com.example.shopping.ui.home;

import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.adapter.ChannelAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.ChannelBean;
import com.example.shopping.moudel.bean.ChannelDescBean;
import com.example.shopping.pansenter.home.ChannelPansenter;

import java.util.List;

import butterknife.BindView;


public class ChannelFragment extends BaseFragment<ChannelPansenter> implements IHome.ChannelView {

    ChannelPansenter channelPansenter;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.desc)
    TextView desc;
    private int id;
    private int id1;

    public ChannelFragment(int id) {
        this.id = id;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_blank;
    }

    @Override
    protected ChannelPansenter createPrenter() {
        channelPansenter = new ChannelPansenter(this);
        return channelPansenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        channelPansenter.getChannelDescBean(id + "");
        channelPansenter.getChannelData(1005007+"");
    }

    @Override
    public void callChannelDesc(ChannelDescBean channelDescBean) {
        ChannelDescBean.DataBeanX data = channelDescBean.getData();
        List<ChannelDescBean.DataBeanX.DataBean> data1 = data.getData();
        ChannelAdapter channelAdapter = new ChannelAdapter(getContext(), data1);
        rec.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rec.setAdapter(channelAdapter);
    }

    @Override
    public void callChannelView(ChannelBean channelBean) {
        ChannelBean.DataBean data = channelBean.getData();
        List<ChannelBean.DataBean.BrotherCategoryBean> brotherCategory = data.getBrotherCategory();
            for (int i = 0; i < brotherCategory.size(); i++) {
                int id2 = brotherCategory.get(i).getId();
                if (this.id ==id2){
                    if (brotherCategory!=null){
                        name.setText(brotherCategory.get(i).getName());
                        desc.setText(brotherCategory.get(i).getFront_desc());
                    }

                }

        }



    }
}