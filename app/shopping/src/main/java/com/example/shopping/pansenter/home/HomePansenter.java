package com.example.shopping.pansenter.home;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.BanBean;
import com.example.shopping.moudel.home.HomeMoudel;
import com.example.shopping.ui.home.HomeFragment;

public class HomePansenter extends BasePresenter<IHome.BanView> implements IHome.BanParsenter {
    IHome.BanView banView;
    IHome.ChannelView channelView;
    private final HomeMoudel homeMoudel;

    public HomePansenter(HomeFragment homeFragment) {
        banView=homeFragment;
        homeMoudel = new HomeMoudel();
    }

    @Override
    public void getBanData() {
        homeMoudel.getBanData(new Callback<BanBean>() {
            @Override
            public void success(BanBean data) {
                banView.callBanData(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }


}
