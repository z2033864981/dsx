package com.client.model.home;

import com.client.base.BaseModel;
import com.client.interfaces.Callback;
import com.client.interfaces.home.IHome;
import com.client.net.CommonSubscriber;
import com.client.net.HttpManager;
import com.client.presenter.home.HomePresenter;
import com.client.utils.RxUtils;

public class HomeModel extends BaseModel implements IHome.Model {


    @Override
    public void getHome(Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getHome()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<HomeBean>(callback) {
            @Override
            public void onNext(HomeBean homeBean) {
                callback.success(homeBean);
            }
        }));
    }
}
