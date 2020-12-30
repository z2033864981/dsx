package com.example.shopping.ui.sort;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping.R;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.sort.ISort;
import com.example.shopping.moudel.bean.ChannelDescBean;
import com.example.shopping.moudel.bean.SortBean;
import com.example.shopping.moudel.bean.SortTabDescBean;
import com.example.shopping.pansenter.sort.SortDerailPansenter;
import com.example.shopping.ui.home.ChannelFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SortDetailActivity extends BaseActivity<SortDerailPansenter> implements ISort.SortDetailView {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private int id;
    private ArrayList<Fragment> fragments;

    @Override
    protected int getLayout() {
        return R.layout.channel_activicy;
    }
    SortDerailPansenter sortDerailPansenter;
    @Override
    protected SortDerailPansenter createPrenter() {
        sortDerailPansenter=new SortDerailPansenter(this);
        return sortDerailPansenter;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        fragments = new ArrayList<>();
    }

    @Override
    protected void initData() {
        sortDerailPansenter.getSortData(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getSortData(SortTabDescBean sortTabDescBean) {
        SortTabDescBean.DataBean data = sortTabDescBean.getData();
        List<SortTabDescBean.DataBean.BrotherCategoryBean> brotherCategory = data.getBrotherCategory();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i <brotherCategory.size() ; i++) {
            String name = brotherCategory.get(i).getName();
            strings.add(name);
        }

        for (int i = 0; i <brotherCategory.size() ; i++) {

            fragments.add(new SortDerailChannelFragment(brotherCategory.get(i).getId()));
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
    public void getSortPansenterData(SortBean sortBean) {

    }
}
