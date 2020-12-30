package com.example.demo2.model;

import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.CarBean;
import com.example.demo2.bean.DeleteCarBean;
import com.example.demo2.bean.UpdateCarBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ICar;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

import java.util.Map;

public class CarModel extends BaseModel implements ICar.Model {
    @Override
    public void getCarList(Callback callback) {
        addDisposible(HttpManager.getInstance().getService().getCarList().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CarBean>(callback) {
                    @Override
                    public void onNext(CarBean carBean) {
                        callback.success(carBean);
                    }
                }));
    }

    /**
     * 更新购物车
     * @param map
     * @param callback
     */
    @Override
    public void updateCar(Map<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getService().updateCar(map).
                compose(RxUtils.rxScheduler())
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
        addDisposible(HttpManager.getInstance().getService().deleteCar(pIds).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                }));
    }
}
