package com.example.shopping.moudel.sort;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.sort.ISort;
import com.example.shopping.moudel.bean.SortBean;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabDescBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

public class SortDetailMoudel extends BaseModel implements ISort.SortDetailMoudel {
    @Override
    public void getSrt(int id, Callback<SortTabDescBean> callback) {
        HttpManager.getInstance()
                .sortApi()
                .getTabBean(id)
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<SortTabDescBean>(callback) {
                    @Override
                    public void onNext(SortTabDescBean sortTabDescBean) {
                        callback.success(sortTabDescBean);
                    }
                });
    }

    @Override
    public void getSortPansenterData(int id, Callback<SortBean> callback) {
    HttpManager.getInstance()
            .sortApi()
            .getSortBean(id,1,100)
            .compose(RxUtils.rxScheduler())
            .subscribe(new CommonSubscriber<SortBean>(callback) {
                @Override
                public void onNext(SortBean sortBean) {
                    callback.success(sortBean);
                }
            });
    }
}
