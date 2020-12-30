package com.example.shopping.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping.R;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.ChannelBean;
import com.example.shopping.moudel.bean.ChannelDescBean;
import com.example.shopping.pansenter.home.ChannelPansenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelActivity extends BaseActivity<ChannelPansenter> implements IHome.ChannelView {
    ChannelPansenter channelPansenter;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private String id;
    private ChannelBean.DataBean data;


    @Override
    protected int getLayout() {
        return R.layout.channel_activicy;
    }

    @Override
    protected ChannelPansenter createPrenter() {
        channelPansenter = new ChannelPansenter(this);
        return channelPansenter;
    }


    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

    }

    @Override
    protected void initData() {
        channelPansenter.getChannelDescBean(id);

    }

    @Override
    public void callChannelDesc(ChannelDescBean channelDescBean) {
        ChannelDescBean.DataBeanX data = channelDescBean.getData();
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        List<ChannelDescBean.DataBeanX.FilterCategoryBean> filterCategory = data.getFilterCategory();
        filterCategory.remove(0);

        for (int i = 0; i <filterCategory.size() ; i++) {
            String name = filterCategory.get(i).getName();
            strings.add(name);
        }

        for (int i = 0; i <filterCategory.size() ; i++) {

            fragments.add(new ChannelFragment(filterCategory.get(i).getId()));
        }
        vp.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return strings.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        });
        tab.setupWithViewPager(vp);
    }

    @Override
    public void callChannelView(ChannelBean channelBean) {
        data = channelBean.getData();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
