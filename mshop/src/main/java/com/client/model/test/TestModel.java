package com.client.model.test;

import com.client.base.BaseModel;
import com.client.interfaces.Callback;
import com.client.interfaces.test.ITest;
import com.client.net.CommonSubscriber;
import com.client.net.HttpManager;
import com.client.utils.RxUtils;

public class TestModel extends BaseModel implements ITest.Model {

    @Override
    public void getList(Callback callback) {
        addDisposible(HttpManager.getInstance().getService().getList()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TestBean>(callback) {
            @Override
            public void onNext(TestBean testBean) {
                callback.success(testBean);
            }
        }));
    }
}
