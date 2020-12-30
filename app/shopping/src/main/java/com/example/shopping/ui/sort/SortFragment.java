package com.example.shopping.ui.sort;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.sort.ISort;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabBean;
import com.example.shopping.pansenter.sort.SortPansenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

public class SortFragment extends BaseFragment<SortPansenter> implements ISort.SortView {

    SortPansenter sortPansenter;
    @BindView(R.id.vTab)
    VerticalTabLayout vTab;
    @BindView(R.id.vp_sort)
    ViewPager vpSort;
    ArrayList<Fragment>fragments;
    private ArrayList<String> names;

    @Override
    protected int getLayout() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected SortPansenter createPrenter() {
        if (sortPansenter == null) {
            sortPansenter = new SortPansenter(this);
        }
        return sortPansenter;
    }

    @Override
    protected void initView() {
        fragments=new ArrayList<>();
        names = new ArrayList<>();
    }

    @Override
    protected void initData() {
        sortPansenter.getTabData();
    }

    @Override
    public void getTabData(SortTabBean sortTabBean) {
        if (sortTabBean!=null){
            List<SortTabBean.DataBean.CategoryListBean> categoryList = sortTabBean.getData().getCategoryList();
            for (int i = 0; i < categoryList.size(); i++) {
                SortTabBean.DataBean.CategoryListBean categoryListBean = categoryList.get(i);
                SortChannlFragment sortChannlFragment = new SortChannlFragment();
                fragments.add(sortChannlFragment);
                Bundle bundle = new Bundle();
                bundle.putInt("id",categoryListBean.getId());
                sortChannlFragment.setArguments(bundle);
                names.add(categoryList.get(i).getName());
            }
            if (vpSort!=null){
                vpSort.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
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
                        return names.get(position);
                    }
                });
                vTab.setupWithViewPager(vpSort);
                vTab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabView tab, int position) {

                    }

                    @Override
                    public void onTabReselected(TabView tab, int position) {

                    }
                });
            }

        }

    }

    @Override
    public void getSortDescData(SortDescBean sortDescBean) {

    }
}
