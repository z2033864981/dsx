package com.example.demo2.model;

import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.HotGoodListBean;
import com.example.demo2.bean.NewGoodsBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IHotGood;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

import java.util.HashMap;

public class HotGoodModel extends BaseModel implements IHotGood.Model {
    @Override
    public void getHotGood(HashMap<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getService().getHotGoodList(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotGoodListBean>(callback) {
                    @Override
                    public void onNext(HotGoodListBean hotGoodListBean) {
                        callback.success(hotGoodListBean);
                    }
                }));
    }

    @Override
    public void getNewGood(Callback callback) {
        addDisposible(HttpManager.getInstance().getService().getNewGoods()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<NewGoodsBean>(callback) {
                    @Override
                    public void onNext(NewGoodsBean newGoodsBean) {
                        callback.success(newGoodsBean);
                    }
                }));
    }
}
