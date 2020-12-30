package com.client.ui.home;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.R;
import com.client.base.BaseActivity;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.home.ICategory;
import com.client.model.home.CategoryBean;
import com.client.model.home.CategoryGoodBean;
import com.client.presenter.home.CategoryPresenter;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelListActivity extends BaseActivity<ICategory.Presenter> implements ICategory.View {

    @BindView(R.id.txt_channel_name)
    TextView txtChannelName;
    @BindView(R.id.txt_channel_des)
    TextView txtChannelDes;
    @BindView(R.id.recy_goods)
    RecyclerView recyGoods;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    List<CategoryGoodBean.DataBeanX.GoodsListBean> goods;
    CategoryGoodAdapter categoryGoodAdapter;

    List<CategoryBean.DataBean.BrotherCategoryBean> tabs;

    private int currentCategoryId;
    private  boolean isInit = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected CategoryPresenter createPrenter() {
        return new CategoryPresenter();
    }

    @Override
    protected void initView() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if(pos == 0 && !isInit){
                    //还没有初始化完
                }else{
                    currentCategoryId = pos;
                    if(pos < tabs.size()){
                        //获取列表数据
                        presenter.getCategoryGood(tabs.get(pos).getId(),1,100);
                    }else{
                        throw new RuntimeException("数据无效");
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {

        goods = new ArrayList<>();
        categoryGoodAdapter = new CategoryGoodAdapter(this,goods);
        recyGoods.setLayoutManager(new GridLayoutManager(this,2));
        recyGoods.setAdapter(categoryGoodAdapter);
        Intent intent = getIntent();
        if(intent != null){
            currentCategoryId = intent.getIntExtra("categoryid",0);
            presenter.getCategoryTab(currentCategoryId);
        }

    }

    @Override
    public void getCategoryTabReturn(CategoryBean result) {
        //init tab
        tabs = result.getData().getBrotherCategory();
        for(CategoryBean.DataBean.BrotherCategoryBean item:result.getData().getBrotherCategory()){
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(item.getName());
            tab.setTag(item.getId());
            tabLayout.addTab(tab);
            //通过一个界面传过来的categoryid判断哪个tab选中
            if(currentCategoryId == item.getId()){
                tabLayout.selectTab(tab);
            }
        }
        //记录上去页面初始化完成状态
        isInit = true;
    }

    @Override
    public void getCategoryGoodReturn(CategoryGoodBean result) {
        goods.clear();
        goods.addAll(result.getData().getGoodsList());
        categoryGoodAdapter.notifyDataSetChanged();
    }
}
