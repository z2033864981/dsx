package com.example.demo2.model;


import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.LoginBean;
import com.example.demo2.bean.RegisterBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ILogin;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {

    @Override
    public void login(String username, String pw, Callback callback) {
        addDisposible(HttpManager.getInstance().getService().login(username,pw).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }

    @Override
    public void getRegister(String username, String pw, Callback callback) {
        addDisposible(HttpManager.getInstance().getService()
                .getreister(username,pw)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(callback) {
                    @Override
                    public void onNext(RegisterBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }
}
