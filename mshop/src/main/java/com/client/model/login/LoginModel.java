package com.client.model.login;

import com.client.base.BaseModel;
import com.client.interfaces.Callback;
import com.client.interfaces.login.ILogin;
import com.client.interfaces.shop.IShop;
import com.client.model.shop.GoodDetailBean;
import com.client.net.CommonSubscriber;
import com.client.net.HttpManager;
import com.client.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {
    @Override
    public void login(String username,String pw, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().login(username,pw).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }
}
