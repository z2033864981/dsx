package com.example.demo2.model;

import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.AddCarBean;
import com.example.demo2.bean.CategoryBottomInfoBean;
import com.example.demo2.bean.GoodDetailBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IShop;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

import java.util.Map;

public class ShopModel extends BaseModel implements IShop.Model {
    @Override
    public void getGoodDetail(int id, Callback callback) {
        addDisposible(HttpManager.getInstance().getService().getGoodDetail(id).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodDetailBean>(callback) {
                    @Override
                    public void onNext(GoodDetailBean goodDetailBean) {
                        callback.success(goodDetailBean);
                    }
                }));
    }

    @Override
    public void getCategoryBottomInfo(int id, Callback callback) {
        //商品 详情购买页 底部数据列表

        addDisposible(
                HttpManager.getInstance().getService().getCategoryBottomInfo(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<CategoryBottomInfoBean>(callback) {
                            @Override
                            public void onNext(CategoryBottomInfoBean categoryBottomInfoBean) {
                                callback.success(categoryBottomInfoBean);
                            }
                        }));
    }

    // 添加进购物车
    @Override
    public void addGoodCar(Map<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getService().addCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        callback.success(addCarBean);
                    }
                }));
    }
}
