package com.example.demo2.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.demo2.R;
import com.example.demo2.adapter.BrandListAdapter;
import com.example.demo2.adapter.CategroyListAdapter;
import com.example.demo2.adapter.HotGoodsListAdapter;
import com.example.demo2.adapter.NewGoodsListAdapter;
import com.example.demo2.adapter.TopicListAdapter;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseAdapter;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.bean.OneBean;
import com.example.demo2.interfaces.demo.ITest;
import com.example.demo2.presenter.TestPresenter;
import com.example.demo2.ui.demo.BrandActivity;
import com.example.demo2.ui.demo.CarActivity;
import com.example.demo2.ui.demo.ChannelActivity;
import com.example.demo2.ui.demo.HotGoodActivity;
import com.example.demo2.ui.demo.TvBrandActivity;
import com.example.demo2.utils.TxtUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;

public class OneFragment extends BaseFragment<TestPresenter> implements ITest.View {


    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    @BindView(R.id.brandList_recyclerView)
    RecyclerView brandListRecyclerView;
    @BindView(R.id.newGoodsList_recyclerView)
    RecyclerView newGoodsListRecyclerView;
    @BindView(R.id.hotGoodsList_recyclerView)
    RecyclerView hotGoodsListRecyclerView;
    @BindView(R.id.topicList_recyclerView)
    RecyclerView topicListRecyclerView;
    @BindView(R.id.linesr)
    LinearLayout linesr;
    @BindView(R.id.tv_brand)
    TextView tvBrand;
    @BindView(R.id.tv_newgoods)
    TextView tvNewgoods;

    private BrandListAdapter brandListAdapter;
    private NewGoodsListAdapter newGoodsListAdapter;
    private HotGoodsListAdapter hotGoodsListAdapter;
    private TopicListAdapter topicListAdapter;
    private CategroyListAdapter categroyListAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected TestPresenter createPrenter() {
        return new TestPresenter(this);
    }

    @Override
    protected void initView() {

        //点击品牌制造商直供跳转详情
        tvBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TvBrandActivity.class));
            }
        });

        //点击周一周四新品首发跳转详情
        tvNewgoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HotGoodActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        presenter.getList();
    }


    @Override
    public void getListReturn(OneBean oneBean) {
        if (oneBean.getData() != null) {
            //初始化Banner
            initBanner(oneBean.getData().getBanner());
            //动态栏
            initChannel(oneBean.getData().getChannel());
            //新品首发
            initNewGoods(oneBean.getData().getNewGoodsList());
            //人气推荐
            initHotGoods(oneBean.getData().getHotGoodsList());
            //品牌制造商直供
            initBrand(oneBean.getData().getBrandList());
            //专题精选
            initTopic(oneBean.getData().getTopicList());
            //居家
            initCategory(oneBean.getData().getCategoryList());
        }

    }


    //居家
    private void initCategory(List<OneBean.DataBean.CategoryListBean> categoryList) {
        //循环放入布局
        for (int i = 0; i < categoryList.size(); i++) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.home_item, null);
            TextView texthometitle = inflate.findViewById(R.id.txt_home_title);
            RecyclerView recyhome = inflate.findViewById(R.id.recy_home);
            texthometitle.setText(categoryList.get(i).getName());
            List<OneBean.DataBean.CategoryListBean.GoodsListBean> goodsList = categoryList.get(i).getGoodsList();

            recyhome.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            categroyListAdapter = new CategroyListAdapter(getContext(), goodsList);
            recyhome.setAdapter(categroyListAdapter);

            linesr.addView(inflate);

            categroyListAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    int pos_= pos;
                    Intent intent = new Intent(getContext(), CarActivity.class);
                    intent.putExtra("goodid",goodsList.get(pos).getId());
                    intent.putExtra("pos_",pos_);
                    getContext().startActivity(intent);
                }
            });
        }

    }

    //专属精选
    private void initTopic(List<OneBean.DataBean.TopicListBean> topicList) {
        topicListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        topicListAdapter = new TopicListAdapter(getContext(), topicList);
        topicListRecyclerView.setAdapter(topicListAdapter);
    }

    //人气推荐
    private void initHotGoods(List<OneBean.DataBean.HotGoodsListBean> hotGoodsList) {
        hotGoodsListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hotGoodsListRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        hotGoodsListAdapter = new HotGoodsListAdapter(getContext(), hotGoodsList);
        hotGoodsListRecyclerView.setAdapter(hotGoodsListAdapter);
    }

    //周一周四新品首发
    private void initNewGoods(List<OneBean.DataBean.NewGoodsListBean> newGoodsList) {
        newGoodsListRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        newGoodsListAdapter = new NewGoodsListAdapter(getContext(), newGoodsList);
        newGoodsListRecyclerView.setAdapter(newGoodsListAdapter);

    }

    //品牌制造商直供
    private void initBrand(List<OneBean.DataBean.BrandListBean> brandList) {
        brandListRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        brandListAdapter = new BrandListAdapter(getContext(), brandList);
        brandListRecyclerView.setAdapter(brandListAdapter);

        //点击条目进入详情页面
        brandListAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getContext(), BrandActivity.class);
                intent.putExtra("name", brandList.get(pos).getName());
                intent.putExtra("simple", brandList.get(pos).getSimple_desc());
                intent.putExtra("pic", brandList.get(pos).getList_pic_url());
                startActivity(intent);
            }
        });

    }

    //动态栏
    private void initChannel(List<OneBean.DataBean.ChannelBean> channel) {
        layoutTab.removeAllViews();//清空所有视图
        //设置宽高
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        for (OneBean.DataBean.ChannelBean item : channel) {
            View channe11 = LayoutInflater.from(getContext()).inflate(R.layout.layout_channel_item, layoutTab, false);
            ImageView img = channe11.findViewById(R.id.img_channel);
            TextView txtChannel = channe11.findViewById(R.id.txt_channel);
            com.example.demo2.utils.ImageLoader.loadImage(item.getIcon_url(), img);
            TxtUtils.setTextView(txtChannel, item.getName());
            txtChannel.setGravity(Gravity.CENTER);
            channe11.setLayoutParams(params);
            layoutTab.addView(channe11);

            //点击进入详情页面
            channe11.setTag(item);
            channe11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ChannelActivity.class);
                    String url = ((OneBean.DataBean.ChannelBean) view.getTag()).getIcon_url();
                    String name = ((OneBean.DataBean.ChannelBean) view.getTag()).getName();
                    Myapp.getMap().put("url", url);
                    Myapp.getMap().put("name", name);

                    startActivity(intent);
                }
            });

        }
    }


    private void initBanner(List<OneBean.DataBean.BannerBean> result) {
        //轮播图
        banner.setImages(result).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                OneBean.DataBean.BannerBean bean = (OneBean.DataBean.BannerBean) path;
                Glide.with(context).load(bean.getImage_url()).into(imageView);
            }
        }).start();
    }
}