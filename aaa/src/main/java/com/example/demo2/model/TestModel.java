package com.example.demo2.model;


import com.example.demo2.api.ServiceApi;
import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.OneBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ITest;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

public class TestModel extends BaseModel implements ITest.Model {

    private ServiceApi api;

    public TestModel() {
        api=HttpManager.getInstance().getService();
    }


    @Override
    public void getList(Callback callback) {
        addDisposible(api.getList().compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<OneBean>(callback) {
            @Override
            public void onNext(OneBean oneBean) {
                callback.success(oneBean);
            }
        }));
    }

    
}
