package com.example.shopping.moudel.home;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.BanBean;
import com.example.shopping.moudel.bean.ChannelBean;
import com.example.shopping.moudel.bean.ChannelDescBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

public class HomeMoudel extends BaseModel implements IHome.BanMoudel {
    @Override
    public void getBanData(Callback<BanBean> callback) {
        HttpManager.getInstance()
                .getService()
                .getBanData()
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<BanBean>(callback) {
                    @Override
                    public void onNext(BanBean banBean) {
                        callback.success(banBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        String message = t.getMessage();
                    }
                });
    }

    @Override
    public void getChannelData(Callback<ChannelBean> callback, String id) {

    }

    @Override
    public void getChannelDescData(String id, Callback<ChannelDescBean> callback) {

    }
}
