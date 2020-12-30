package com.client.ui.home;

import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.client.R;
import com.client.base.BaseFragment;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.home.IHome;
import com.client.model.home.HomeBean;
import com.client.presenter.home.HomePresenter;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View {

    @BindView(R.id.layout_tab)
    ConstraintLayout layoutTab;

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
        }
    }

    /**
     * 初始化banner
     * @param list
     */
    private void initBanner(List<HomeBean.DataBean.BannerBean> list){

    }

    /**
     * 初始化channel
     */
    private void initChannel(List<HomeBean.DataBean.ChannelBean> list){
        layoutTab.removeAllViews();
        for(HomeBean.DataBean.ChannelBean item:list){
            View channel = LayoutInflater.from(getContext()).inflate(R.layout.layout_channel_item,null);
            layoutTab.addView(channel);
        }
    }
}
