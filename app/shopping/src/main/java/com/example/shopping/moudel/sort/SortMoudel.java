package com.example.shopping.moudel.sort;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.sort.ISort;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

public class SortMoudel extends BaseModel implements ISort.SortMoudel {
    @Override
    public void getTabData(Callback<SortTabBean> callback) {
        HttpManager.getInstance()
                .sortApi()
                .getSortTabData()
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<SortTabBean>(callback) {
                    @Override
                    public void onNext(SortTabBean sortTabBean) {
                        callback.success(sortTabBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        String s = t.toString();
                    }
                });

    }

    @Override
    public void getSortDescData(int id, Callback<SortDescBean> callback) {
    HttpManager.getInstance()
            .sortApi()
            .getSortDescData(id)
            .compose(RxUtils.rxScheduler())
            .subscribe(new CommonSubscriber<SortDescBean>(callback) {
                @Override
                public void onNext(SortDescBean sortDescBean) {
                    callback.success(sortDescBean);
                }
            });
    }
}
