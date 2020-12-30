package com.example.shopping.moudel.home;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.MakeBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

public class MakeMoudel extends BaseModel implements IHome.MakeMoudel {
    @Override
    public void getMakeData(Callback<MakeBean> callback) {
        HttpManager.getInstance()
                .getService()
                .getMakeData()
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<MakeBean>(callback) {
                    @Override
                    public void onNext(MakeBean makeBean) {
                        callback.success(makeBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }
}
