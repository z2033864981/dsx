package com.example.demo2.ui.demo;

import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo2.R;
import com.example.demo2.adapter.ChannelAdapter;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.bean.ChannelBean;
import com.example.demo2.bean.ChannelTypeBean;
import com.example.demo2.interfaces.demo.IChannel;
import com.example.demo2.presenter.ChannelPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelFragment extends BaseFragment<IChannel.Presenter> implements IChannel.View {
    @BindView(R.id.mRlv_channelType)
    RecyclerView mRlv;
    @BindView(R.id.tv_channel1_title)
    TextView tvChannel1Title;
    @BindView(R.id.tv_channel1_front_desc)
    TextView tvChannel1FrontDesc;
    private String id;
    List<ChannelTypeBean.DataBeanX.GoodsListBean> list;
    private ChannelAdapter channelAdapter;
    private String name;
    private String desc;

    @Override
    protected int getLayout() {
        return R.layout.fragment_channel;
    }

    @Override
    protected IChannel.Presenter createPrenter() {

        return new ChannelPresenter(this);
    }

    @Override
    protected void initView() {
        tvChannel1Title.setText(name);
        tvChannel1FrontDesc.setText(desc);

        mRlv.setLayoutManager(new GridLayoutManager(mContext, 2));
        list = new ArrayList<>();
        channelAdapter = new ChannelAdapter(mContext, list);
        mRlv.setAdapter(channelAdapter);
    }

    @Override
    protected void initData() {
        presenter.getChannelType(id);
    }

    public void getId(String id) {
        this.id = id;
    }

    public void getName(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public void getChannelReturn(ChannelBean result) {

    }

    @Override
    public void getChannelTypeReturn(ChannelTypeBean result) {
        List<ChannelTypeBean.DataBeanX.GoodsListBean> filterCategory = result.getData().getGoodsList();
        list.addAll(filterCategory);
        channelAdapter.notifyDataSetChanged();
    }
}
