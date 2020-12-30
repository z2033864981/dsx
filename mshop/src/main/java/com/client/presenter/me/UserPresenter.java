package com.client.presenter.me;

import com.client.base.BasePresenter;
import com.client.interfaces.Callback;
import com.client.interfaces.me.IUser;
import com.client.interfaces.me.IUser.Presenter;
import com.client.interfaces.shop.ICar;
import com.client.model.login.LoginBean;
import com.client.model.me.UserInfoBean;
import com.client.model.me.UserModel;

import java.util.Map;

public class UserPresenter extends BasePresenter<IUser.View> implements Presenter {

    IUser.Model model;

    public UserPresenter(){
        model = new UserModel();
    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
        model.updateUserInfo(map,new Callback<UserInfoBean>() {
            @Override
            public void success(UserInfoBean data) {
                if(mView != null){
                    mView.updateUserInfoReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}
