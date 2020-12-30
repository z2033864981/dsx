package com.example.demo2.model;

import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.SortNavBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ISortNav;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

public class SortNavModel extends BaseModel implements ISortNav.Model {
    @Override
    public void getSortNav(Callback callBack) {
        addDisposible(HttpManager.getInstance().getService().getSortNav().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortNavBean>(callBack) {
                    @Override
                    public void onNext(SortNavBean sortNavBean) {
                        callBack.success(sortNavBean);
                    }
                }));
    }
}
