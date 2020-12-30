package com.client.model.shop;

import com.client.base.BaseModel;
import com.client.interfaces.Callback;
import com.client.interfaces.shop.IShop;
import com.client.net.CommonSubscriber;
import com.client.net.HttpManager;
import com.client.utils.RxUtils;

import java.util.Map;

public class ShopModel extends BaseModel implements IShop.Model {
    @Override
    public void getGoodDetail(int id, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getGoodDetail(id).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodDetailBean>(callback) {
                    @Override
                    public void onNext(GoodDetailBean goodDetailBean) {
                        callback.success(goodDetailBean);
                    }
                }));
    }

    // 添加进购物车
    @Override
    public void addGoodCar(Map<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().addCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        callback.success(addCarBean);
                    }
                }));
    }
}
