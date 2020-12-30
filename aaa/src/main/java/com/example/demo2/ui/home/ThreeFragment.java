package com.example.demo2.ui.home;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.demo2.R;
import com.example.demo2.adapter.FragTabAdapter;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.bean.SortNavBean;
import com.example.demo2.interfaces.demo.ISortNav;
import com.example.demo2.presenter.SortNavPresenter;
import com.example.demo2.ui.demo.CategoryTabFragment;
import com.example.demo2.utils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class ThreeFragment extends BaseFragment<ISortNav.Presenter> implements ISortNav.View {
    @BindView(R.id.vTab)
    VerticalTabLayout vTab;
    @BindView(R.id.vp_sort)
    CustomViewPager vpSort;


    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected ISortNav.Presenter createPrenter() {
        return new SortNavPresenter();
    }

    @Override
    protected void initView() {
        vpSort.setScanScroll(false);
    }

    @Override
    protected void initData() {
        presenter.getSortNav();
    }

    @Override
    public void getSortNavReturn(SortNavBean sortNavBean) {
        List<SortNavBean.DataBean.CategoryListBean> categoryList = sortNavBean.getData().getCategoryList();
        final ArrayList<CategoryTabFragment> fs = new ArrayList<>();
        for (int i = 0; i <categoryList.size(); i++) {
            int id = categoryList.get(i).getId();
            CategoryTabFragment f = new CategoryTabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            Myapp.getMap().put("id",id);
            f.setArguments(bundle);
            fs.add(f);
        }

        FragTabAdapter fragTabAdapter = new FragTabAdapter(getChildFragmentManager(),fs,categoryList);
        vpSort.setAdapter(fragTabAdapter);
        vTab.setupWithViewPager(vpSort);
    }
}
