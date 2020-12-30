package com.example.demo2.ui.demo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.demo2.R;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.bean.ChannelBean;
import com.example.demo2.bean.ChannelTypeBean;
import com.example.demo2.interfaces.demo.IChannel;
import com.example.demo2.presenter.ChannelPresenter;
import com.example.demo2.utils.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelActivity extends BaseActivity<IChannel.Presenter> implements IChannel.View {


    @BindView(R.id.mTab_channel)
    TabLayout mTabChannel;
    @BindView(R.id.mVp_channel)
    CustomViewPager mVpChannel;

    private String url;
    private List<ChannelFragment> fragments;
    private List<ChannelBean.DataBean.CategoryListBean> list;
    private String name;

    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected IChannel.Presenter createPrenter() {
        return new ChannelPresenter(this);
    }

    @Override
    protected void initView() {
        url = (String) Myapp.getMap().get("url");
        name = (String) Myapp.getMap().get("name");
        Log.e("TAG",name+"");
        fragments = new ArrayList<>();
        list = new ArrayList<>();

        //禁止滑动
        mVpChannel.setScanScroll(false);
    }

    @Override
    protected void initData() {
        presenter.getChannel(url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getChannelReturn(ChannelBean result) {
        List<ChannelBean.DataBean.CategoryListBean> brotherCategory = result.getData().getCategoryList();
        list.addAll(brotherCategory);

        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getId();
            String name = list.get(i).getName();
            String front_desc = list.get(i).getFront_desc();
            ChannelFragment channelFragment = new ChannelFragment();
            channelFragment.getId(id+"");
            channelFragment.getName(name,front_desc);
            fragments.add(channelFragment);
        }

 
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        mVpChannel.setAdapter(vpAdapter);
        mTabChannel.setupWithViewPager(mVpChannel);
        for (int i = 0; i <list.size(); i++) {
            if(this.name.equals(list.get(i).getName())) {
                mVpChannel.setCurrentItem(i);
                return;
            }
        }
    }

    @Override
    public void getChannelTypeReturn(ChannelTypeBean result) {

    }

    class VpAdapter extends FragmentStatePagerAdapter {
        public VpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getName();
        }
    }
}