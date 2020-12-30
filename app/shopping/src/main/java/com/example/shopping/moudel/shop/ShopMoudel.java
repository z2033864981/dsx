package com.example.shopping.moudel.shop;

import android.util.Log;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.shop.IShop;
import com.example.shopping.moudel.bean.ShopBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

public class ShopMoudel extends BaseModel implements IShop.ShopMoudel {
    @Override
    public void getShopData(int size, int page, Callback<ShopBean>callback) {
        HttpManager.getInstance()
                .shopApi()
                .getShop(size,page)
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<ShopBean>(callback) {
                    @Override
                    public void onNext(ShopBean shopBean) {
                        callback.success(shopBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: "+t.toString() );
                    }
                });
    }
}
