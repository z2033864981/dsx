package com.client.interfaces.login;

import com.client.interfaces.Callback;
import com.client.interfaces.IBaseModel;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.IBaseView;
import com.client.interfaces.shop.IShop;
import com.client.model.login.LoginBean;
import com.client.model.shop.GoodDetailBean;

public interface ILogin {
    interface View extends IBaseView {
        void loginReturn(LoginBean loginBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void login(String username,String pw);
    }


    interface Model extends IBaseModel {
        void login(String username,String pw, Callback callback);
    }
}
