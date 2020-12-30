package com.client.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.R;
import com.client.base.BaseFragment;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.home.IHome;
import com.client.model.home.HomeBean;
import com.client.presenter.home.HomePresenter;
import com.client.utils.ImageLoader;
import com.client.utils.TxtUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    @BindView(R.id.recy_brand)
    RecyclerView recyBrand;
    @BindView(R.id.recy_newgood)
    RecyclerView recyNewGood;
    @BindView(R.id.recy_hotgood)
    RecyclerView recyHotGood;
    @BindView(R.id.recy_topic)
    RecyclerView recyTopic;
    @BindView(R.id.recy_category)
    RecyclerView recyCategory;
    @BindView(R.id.txt_newgood_title)
    TextView txtNewGoodTitle;

    //品牌直供
    BrandAdpater brandAdpater;
    List<HomeBean.DataBean.BrandListBean> brandList;

    //新品首发
    NewGoodAdapter newGoodAdapter;
    List<HomeBean.DataBean.NewGoodsListBean> newGoodList;

    //人气推荐
    HotGoodAdapter hotGoodAdapter;
    List<HomeBean.DataBean.HotGoodsListBean> hotGoodList;

    //专题精选
    TopicGoodAdapter topicGoodAdapter;
    List<HomeBean.DataBean.TopicListBean> topicGoodList;

    //category商品
    CategoryAdapter categoryAdapter;
    List<HomeBean.DataBean.CategoryListBean> categoryList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPrenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getHome(); //初始化加载数据
    }

    @Override
    public void getHomeReturn(HomeBean result) {
        if(result.getData() != null){
            initBanner(result.getData().getBanner());
            initChannel(result.getData().getChannel());
            initBrand(result.getData().getBrandList());
            initNewGood(result.getData().getNewGoodsList());
            initHotGood(result.getData().getHotGoodsList());
            initTopicGood(result.getData().getTopicList());
            initCategoryList(result.getData().getCategoryList());
        }
    }

    /**
     * 初始化banner
     * @param list
     */
    private void initBanner(List<HomeBean.DataBean.BannerBean> list){
        banner.setImages(list);
        banner.setImageLoader(new MyImageLoader());
        banner.start();
    }

    /**
     * 初始化channel
     */
    private void initChannel(List<HomeBean.DataBean.ChannelBean> list){
        layoutTab.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        for(HomeBean.DataBean.ChannelBean item:list){
            View channel = LayoutInflater.from(getContext()).inflate(R.layout.layout_channel_item,layoutTab,false);
            ImageView img = channel.findViewById(R.id.img_channel);
            TextView txtChannel = channel.findViewById(R.id.txt_channel);
            ImageLoader.loadImage(item.getIcon_url(),img);
            TxtUtils.setTextView(txtChannel,item.getName());
            txtChannel.setGravity(Gravity.CENTER);
            channel.setLayoutParams(params);
            layoutTab.addView(channel);
            channel.setTag(item);
            channel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curId = ((HomeBean.DataBean.ChannelBean)v.getTag()).getCategoryid();
                    Intent intent = new Intent(mContext,ChannelListActivity.class);
                    intent.putExtra("categoryid",curId);
                    startActivity(intent);
                }
            });

        }
    }

    /**
     * 品牌制作商
     */
    private void initBrand(List<HomeBean.DataBean.BrandListBean> list){
        brandList = list;
        brandAdpater = new BrandAdpater(mContext,brandList);
        recyBrand.setLayoutManager(new GridLayoutManager(mContext,2));
        //item的分割线
        recyBrand.addItemDecoration(new HotItemDecoraction(mContext,LinearLayoutManager.HORIZONTAL));
        recyBrand.setAdapter(brandAdpater);
    }

    /**
     * 初始化新品发布商品
     * @param list
     */
    private void initNewGood(List<HomeBean.DataBean.NewGoodsListBean> list){
        newGoodList = list;
        newGoodAdapter = new NewGoodAdapter(mContext,newGoodList);
        recyNewGood.setLayoutManager(new GridLayoutManager(mContext,2));
        recyNewGood.setAdapter(newGoodAdapter);
    }

    /**
     * 初始化热门商品
     * @param list
     */
    private void initHotGood(List<HomeBean.DataBean.HotGoodsListBean> list){
        hotGoodList = list;
        hotGoodAdapter = new HotGoodAdapter(mContext,hotGoodList);
        recyHotGood.setLayoutManager(new LinearLayoutManager(mContext));
        recyHotGood.addItemDecoration(new HotItemDecoraction(mContext,LinearLayoutManager.HORIZONTAL));
        recyHotGood.setAdapter(hotGoodAdapter);
    }

    /**
     * 初始化专题精选
     * @param list
     */
    private void initTopicGood(List<HomeBean.DataBean.TopicListBean> list){
        topicGoodList = list;
        topicGoodAdapter = new TopicGoodAdapter(mContext,topicGoodList);
        recyTopic.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));

        recyTopic.setAdapter(topicGoodAdapter);
    }

    /**
     * 初始化商品列表
     * @param list
     */
    private void initCategoryList(List<HomeBean.DataBean.CategoryListBean> list){
        categoryList = list;
        categoryAdapter = new CategoryAdapter(mContext,categoryList,getActivity());
        recyCategory.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyCategory.setAdapter(categoryAdapter);
    }


    @OnClick(R.id.txt_newgood_title)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.txt_newgood_title:
                openHotGoodList();
                break;
        }
    }

    private void openHotGoodList(){
        Intent intent = new Intent(mContext,HotGoodActivity.class);
        startActivity(intent);
    }

    /**
     * banner image加载
     */
    class MyImageLoader extends com.youth.banner.loader.ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            HomeBean.DataBean.BannerBean bean = (HomeBean.DataBean.BannerBean) path;
            ImageLoader.loadImage(bean.getImage_url(),imageView);
        }
    }
}
