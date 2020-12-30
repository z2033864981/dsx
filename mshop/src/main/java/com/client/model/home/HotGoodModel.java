package com.client.model.home;

import com.client.base.BaseModel;
import com.client.interfaces.Callback;
import com.client.interfaces.home.IHotGood;
import com.client.net.CommonSubscriber;
import com.client.net.HttpManager;
import com.client.utils.RxUtils;

import java.util.HashMap;

public class HotGoodModel extends BaseModel implements IHotGood.Model {
    @Override
    public void getHotGood(HashMap<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getHotGoodList(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotGoodListBean>(callback) {
                    @Override
                    public void onNext(HotGoodListBean hotGoodListBean) {
                        callback.success(hotGoodListBean);
                    }
                }));
    }
}
