package com.example.demo2.model;

import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.SortDataBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ISortData;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;


public class SortDataModel extends BaseModel implements ISortData.Model {
    @Override
    public void getSortData(int id, Callback callBack) {
        addDisposible(HttpManager.getInstance().getService().getSortData(id).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortDataBean>(callBack) {
                    @Override
                    public void onNext(SortDataBean sortDataBean) {
                        callBack.success(sortDataBean);
                    }
                }));
    }
}
