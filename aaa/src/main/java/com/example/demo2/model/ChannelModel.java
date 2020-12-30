package com.example.demo2.model;

import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.ChannelBean;
import com.example.demo2.bean.ChannelTypeBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IChannel;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

public class ChannelModel extends BaseModel implements IChannel.BaseModel {

    @Override
    public void getChannel(String id, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getService().getChannel(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<ChannelBean>(callback) {
                            @Override
                            public void onNext(ChannelBean channelBean) {
                                callback.success(channelBean);
                            }
                        })
        );
    }

    @Override
    public void getChannelType(String id,Callback callback) {
        addDisposible(
                HttpManager.getInstance().getService().getChannelType(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ChannelTypeBean>(callback) {
                    @Override
                    public void onNext(ChannelTypeBean channelTypeBean) {
                        callback.success(channelTypeBean);
                    }
                })
        );
    }
}
