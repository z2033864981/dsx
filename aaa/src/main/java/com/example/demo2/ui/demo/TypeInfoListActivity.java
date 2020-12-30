package com.example.demo2.ui.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.demo2.R;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.bean.SortDataBean;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.utils.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TypeInfoListActivity extends BaseActivity {

    @BindView(R.id.mTab_channel)
    TabLayout mTab;
    @BindView(R.id.mVp_channel)
    CustomViewPager mVp;
    private List<SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list;
    private ArrayList<TypeInfoListFragment> fragments;
    private String name;


    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        list = (List<SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean>) Myapp.getMap().get("typelist");
        fragments = new ArrayList<>();

        //禁止滑动
        mVp.setScanScroll(false);

        name = (String) Myapp.getMap().get("name");
    }


    @Override
    protected void initData() {
        for (int i = 0; i < this.list.size() ; i++) {
            int id = this.list.get(i).getId();
            String name = list.get(i).getName();
            String front_name = list.get(i).getFront_name();

            TypeInfoListFragment typeInfoListFragment = new TypeInfoListFragment();
            typeInfoListFragment.getId(String.valueOf(id));
            typeInfoListFragment.getName(name,front_name);

            fragments.add(typeInfoListFragment);
        }

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);

        for (int i = 0; i < list.size(); i++) {
            if (this.name.equals(list.get(i).getName())) {
                mVp.setCurrentItem(i);
                return;
            }
        }
    }

   public class VpAdapter extends FragmentStatePagerAdapter {
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