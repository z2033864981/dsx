package com.example.shopping.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.adapter.CategoryListAdapter;
import com.example.shopping.adapter.ManufacturerAdapter;
import com.example.shopping.adapter.NewGoodAdapter;
import com.example.shopping.adapter.RecommendAdapter;
import com.example.shopping.adapter.TopAdapter;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.BanBean;
import com.example.shopping.pansenter.home.HomePansenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import scut.carson_ho.searchview.SearchView;

public class HomeFragment extends BaseFragment<HomePansenter> implements IHome.BanView, View.OnClickListener {

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.ban)
    Banner ban;
    @BindView(R.id.li)
    LinearLayout li;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.rec1)
    RecyclerView rec1;
    @BindView(R.id.rec2)
    RecyclerView rec2;
    @BindView(R.id.rec3)
    RecyclerView rec3;
    @BindView(R.id.rec4)
    RecyclerView rec4;
    @BindView(R.id.li2)
    LinearLayout li2;
    @BindView(R.id.make)
    TextView make;
    @BindView(R.id.tv_newgoods)
    TextView tvNewgoods;
    private ArrayList<BanBean.DataBean.NewGoodsListBean> newGoodsListBeans;
    private ManufacturerAdapter manufacturerAdapter;
    public static List<BanBean.DataBean.ChannelBean> channel;
    private int id;


    @Override
    protected int getLayout() {

        return R.layout.fragment_home;
    }

    HomePansenter homePansenter;

    @Override
    protected HomePansenter createPrenter() {
        if (homePansenter == null) {
            homePansenter = new HomePansenter(this);
        }
        return homePansenter;
    }

    @Override
    protected void initView() {
        make.setOnClickListener(this);
        tvNewgoods.setOnClickListener(this);


    }

    private void initBanner(BanBean.DataBean banBean) {
        if (banBean!=null){
            List<BanBean.DataBean.BannerBean> banner = banBean.getBanner();
            if (ban!=null){
                ban.setImages(banner).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        BanBean.DataBean.BannerBean bannerBean = (BanBean.DataBean.BannerBean) path;
                        String image_url = bannerBean.getImage_url();
                        Glide.with(context).load(image_url).into(imageView);
                    }
                }).start();
            }

        }
//     View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);




    }

    @Override
    protected void initData() {
        homePansenter.getBanData();
    }

    @Override
    public void callBanData(BanBean banBean) {
        BanBean.DataBean data = banBean.getData();
        List<BanBean.DataBean.NewGoodsListBean> newGoodsList = data.getNewGoodsList();
        List<BanBean.DataBean.BrandListBean> brandList = data.getBrandList();
        List<BanBean.DataBean.HotGoodsListBean> hotGoodsList = data.getHotGoodsList();
        List<BanBean.DataBean.TopicListBean> topicList = data.getTopicList();
        List<BanBean.DataBean.CategoryListBean> categoryList = data.getCategoryList();
        initBanner(data);
        initLi(data);
        initManufacturer(brandList);
        initnewGood(newGoodsList);
        initRecommend(hotGoodsList);
        initTop(topicList);
        initCategoryList(categoryList);
    }

    private void initCategoryList(List<BanBean.DataBean.CategoryListBean> goodsList) {
        for (int i = 0; i < goodsList.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_item_sss, null);
            TextView text = view.findViewById(R.id.txt_home_title);
            text.setText(goodsList.get(i).getName());
            RecyclerView recy_home = view.findViewById(R.id.recy_home);
            List<BanBean.DataBean.CategoryListBean.GoodsListBean> goodsList1 = goodsList.get(i).getGoodsList();
            recy_home.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            CategoryListAdapter categoryListAdapter = new CategoryListAdapter(getActivity(), goodsList1);

            recy_home.setAdapter(categoryListAdapter);
            categoryListAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    BanBean.DataBean.CategoryListBean.GoodsListBean goodsListBean = goodsList1.get(pos);
                    id = goodsListBean.getId();
                    startActivity();
                }
            });
            li2.addView(view);
        }
    }
    void startActivity(){
        Intent intent = new Intent(getContext(), GoodDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    @Override
    public void onDetach() {
        super.onDetach();
//        li2.removeAllViews();
//        li.removeAllViews();
    }

    private void initTop(List<BanBean.DataBean.TopicListBean> topicList) {
        TopAdapter topAdapter = new TopAdapter(getContext(), topicList);
        rec4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        rec3.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rec4.setAdapter(topAdapter);
    }

    private void initRecommend(List<BanBean.DataBean.HotGoodsListBean> hotGoodsList) {
        RecommendAdapter recommendAdapter = new RecommendAdapter(getContext(), hotGoodsList);
        rec3.setLayoutManager(new LinearLayoutManager(getContext()));
        rec3.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rec3.setAdapter(recommendAdapter);
        recommendAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                id=hotGoodsList.get(pos).getId();
                startActivity();
            }
        });
    }

    private void initnewGood(List<BanBean.DataBean.NewGoodsListBean> newGoodsList) {
        NewGoodAdapter newGoodAdapter = new NewGoodAdapter(getContext(), newGoodsList);
        rec2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rec2.setAdapter(newGoodAdapter);
        newGoodAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                 id = newGoodsList.get(pos).getId();
                 startActivity();
            }
        });
    }

    private void initManufacturer(List<BanBean.DataBean.BrandListBean> brandList) {
        manufacturerAdapter = new ManufacturerAdapter(getContext(), brandList);
        rec1.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rec1.setAdapter(manufacturerAdapter);
    }

    Context mContext = getContext();

    private void initLi(BanBean.DataBean data) {
        channel = data.getChannel();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        for (int i = 0; i < channel.size(); i++) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item, null);
            TextView tv = inflate.findViewById(R.id.tv);
            ImageView img = inflate.findViewById(R.id.img);
            tv.setText(channel.get(i).getName());
            LinearLayout li1 = inflate.findViewById(R.id.li1);
            li1.setTag(i);
            li1.setPadding(2, 2, 2, 2);
            li1.setLayoutParams(layoutParams);
            int finalI = i;
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ChannelActivity.class);
                    String url = channel.get(finalI).getUrl();
                    int end = url.indexOf("=") + 1;
                    String substring = url.substring(end);
                    intent.putExtra("id", substring);
                    intent.putExtra("type",true);
                    getContext().startActivity(intent);
                }
            });
            Glide.with(getContext()).load(channel.get(i).getIcon_url()).into(img);
            li.addView(inflate);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.make:
                Intent intent = new Intent(getContext(), MakeActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_newgoods:
                Intent intent1 = new Intent(getContext(), NewGoodActicity.class);
                getActivity().startActivity(intent1);
                break;
        }
    }
}
