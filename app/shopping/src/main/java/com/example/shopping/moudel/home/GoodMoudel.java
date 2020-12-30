package com.example.shopping.moudel.home;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.good.IGood;
import com.example.shopping.moudel.bean.AddCarBean;
import com.example.shopping.moudel.bean.CategoryBottomInfoBean;
import com.example.shopping.moudel.bean.GoodDetailBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

import java.util.Map;

public class GoodMoudel extends BaseModel implements IGood.Model {
    @Override
    public void getGoodDetail(int id, Callback callback) {
        HttpManager.getInstance().getGoodApi().getGoodData(id)
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<GoodDetailBean>(callback) {
                    @Override
                    public void onNext(GoodDetailBean goodDetailBean) {
                        callback.success(goodDetailBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }
    @Override
    public void getCategoryBottomInfo(int id, Callback callback) {
        //商品 详情购买页 底部数据列表

        addDisposible(
                HttpManager.getInstance().getGoodApi().getCategoryBottomInfo(id)
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
        addDisposible(HttpManager.getInstance().getGoodApi().addCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        callback.success(addCarBean);
                    }
                }));
    }
}
