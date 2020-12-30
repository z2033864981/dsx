package com.example.demo2.model;

import com.example.demo2.api.ServiceApi;
import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.TwoBean;
import com.example.demo2.bean.TwooBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ITest;
import com.example.demo2.interfaces.demo.ITwo;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

public class TwoModel extends BaseModel implements ITwo.Model {
    private ServiceApi api;

    public TwoModel() {
        api= HttpManager.getInstance().getService();
    }

    @Override
    public void getTwo(Callback callback) {
        addDisposible(api.getTwo().compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<TwoBean>(callback) {
            @Override
            public void onNext(TwoBean twoBean) {
                callback.success(twoBean);
            }
        }));
    }

    @Override
    public void getTwoo(Callback callback) {
        addDisposible(api.getTwoo().compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<TwooBean>(callback) {
            @Override
            public void onNext(TwooBean twooBean) {
                callback.success(twooBean);
            }
        }));
    }
}
