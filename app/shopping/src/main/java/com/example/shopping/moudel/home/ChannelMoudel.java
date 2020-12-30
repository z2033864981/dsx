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

public class ChannelMoudel extends BaseModel implements IHome.BanMoudel {
    @Override
    public void getBanData(Callback<BanBean> callback) {

    }

    @Override
    public void getChannelData(Callback<ChannelBean> callback, String id) {
        HttpManager.getInstance()
                .getService()
                .getChannelBean()
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<ChannelBean>(callback) {
                    @Override
                    public void onNext(ChannelBean channelBean) {
                        callback.success(channelBean);
                    }
                });
    }

    @Override
    public void getChannelDescData(String id, Callback<ChannelDescBean> callback) {
        HttpManager.getInstance()
                .getService()
                .getChannelDesc(id,1,"40x")
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<ChannelDescBean>(callback) {
                    @Override
                    public void onNext(ChannelDescBean channelDescBean) {
                        callback.success(channelDescBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        String s = t.toString();
                    }
                });

    }
}
