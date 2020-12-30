package com.client.presenter.login;

import com.client.base.BasePresenter;
import com.client.interfaces.Callback;
import com.client.interfaces.login.ILogin;
import com.client.interfaces.shop.IShop;
import com.client.model.login.LoginBean;
import com.client.model.login.LoginModel;
import com.client.model.shop.GoodDetailBean;
import com.client.model.shop.ShopModel;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter {
    ILogin.Model model;
    public LoginPresenter(){
        model = new LoginModel();
    }
    @Override
    public void login(String username,String pw) {
        model.login(username,pw,new Callback<LoginBean>() {
            @Override
            public void success(LoginBean data) {
                if(mView != null){
                    mView.loginReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
