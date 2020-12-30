package com.example.shopping.moudel.home;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.home.IHome;
import com.example.shopping.moudel.bean.GoosHotBean;
import com.example.shopping.moudel.bean.GoosListBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

import java.util.Map;

public class NewGoodsMoudel extends BaseModel implements IHome.NewGoodsMoudel {
    @Override
    public void getGoodsListData(Map map, Callback<GoosListBean> callback) {
        HttpManager.getInstance()
                .getService()
                .getGoosList(map)
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<GoosListBean>(callback) {
                    @Override
                    public void onNext(GoosListBean goosListBean) {
                        callback.success(goosListBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        String s = t.toString();
                    }
                });
    }

    @Override
    public void getGoodsHotData(Callback<GoosHotBean> callback) {
        HttpManager.getInstance()
                .getService()
                .getGoosHotData()
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<GoosHotBean>(callback) {
                    @Override
                    public void onNext(GoosHotBean goosHotBean) {
                        callback.success(goosHotBean);
                    }
                });
    }
}
