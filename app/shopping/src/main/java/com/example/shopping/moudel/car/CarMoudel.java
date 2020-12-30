package com.example.shopping.moudel.car;

import android.util.Log;

import com.example.shopping.api.ICar;
import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.moudel.bean.CarBean;
import com.example.shopping.moudel.bean.DeleteCarBean;
import com.example.shopping.moudel.bean.UpdateCarBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

import java.util.Map;

public class CarMoudel extends BaseModel implements ICar.CarMoudel {
    /**
     * 更新购物车
     * @param map
     * @param callback
     */
    @Override
    public void updateCar(Map<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance()
                .carApi()
                .updateCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateCarBean>(callback) {
                    @Override
                    public void onNext(UpdateCarBean updateCarBean) {
                        callback.success(updateCarBean);
                    }
                }));
    }

    //删除购物车列表
    @Override
    public void deleteCar(String pIds, Callback callback) {
        addDisposible(HttpManager.getInstance()
                .carApi()
                .deleteCar(pIds).
                        compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                }));
    }
    @Override
    public void getCars(Callback<CarBean> carBeanCallback) {
        HttpManager.getInstance().carApi().getCars()
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<CarBean>(carBeanCallback) {
                    @Override
                    public void onNext(CarBean carBean) {
                        carBeanCallback.success(carBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.i("TAG", "onError: "+t.toString());
                    }
                });
    }


}
