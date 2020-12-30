package com.example.shopping.moudel.car;

import com.example.shopping.base.BaseModel;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.car.ICar;
import com.example.shopping.moudel.bean.LoginBean;
import com.example.shopping.net.CommonSubscriber;
import com.example.shopping.net.HttpManager;
import com.example.shopping.utils.RxUtils;

public class LoginMoudel extends BaseModel implements ICar.LoginMoudel {
    @Override
    public void getLogin(String userName, String password, Callback<LoginBean> callback) {
        HttpManager.getInstance()
                .getGoodApi()
                .getLogin(userName,password)
                .compose(RxUtils.rxScheduler())
                .subscribe(new CommonSubscriber<LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success(loginBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });
    }
}
