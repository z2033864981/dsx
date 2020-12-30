package com.example.demo2.presenter;


import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.LoginBean;
import com.example.demo2.bean.RegisterBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.ILogin;
import com.example.demo2.model.LoginModel;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter {
    ILogin.Model model;
    public LoginPresenter(){
        model = new LoginModel();
    }

    @Override
    public void login(String username, String pw) {
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

    @Override
    public void getRegister(String username, String pw) {
        model.getRegister(username,pw,new Callback<RegisterBean>() {
            @Override
            public void success(RegisterBean data) {
                if(mView != null){
                    mView.getRegister(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
