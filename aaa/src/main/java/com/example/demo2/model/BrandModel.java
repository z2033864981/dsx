package com.example.demo2.model;

import com.example.demo2.api.ServiceApi;
import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.BrandBean;
import com.example.demo2.bean.BrandDetail1;
import com.example.demo2.bean.BrandDetail2;
import com.example.demo2.bean.TwoBean;
import com.example.demo2.bean.TwooBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.demo.IBrand;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

public class BrandModel extends BaseModel implements IBrand.Model {
    private ServiceApi api;

    public BrandModel() {
        api= HttpManager.getInstance().getService();
    }

    @Override
    public void getBrand(Callback callback) {
        addDisposible(api.getBrand().compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<BrandBean>(callback) {
            @Override
            public void onNext(BrandBean brandBean) {
                callback.success(brandBean);
            }
        }));
    }

    @Override
    public void getBrandDetail1(int id, Callback callback) {
        addDisposible(api.getBrandDetail1(id).compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<BrandDetail1>(callback) {
            @Override
            public void onNext(BrandDetail1 brandDetail1) {
                callback.success(brandDetail1);
            }
        }));
    }

    @Override
    public void getBrandDetail2(int brandId, String page, String size, Callback callback) {
        addDisposible(api.getBrandDetail2(brandId,page,size).compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<BrandDetail2>(callback) {
            @Override
            public void onNext(BrandDetail2 brandDetail2) {
                callback.success(brandDetail2);
            }
        }));
    }


}
