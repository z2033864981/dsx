package com.example.demo2.interfaces.demo;


import com.example.demo2.bean.LoginBean;
import com.example.demo2.bean.RegisterBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

public interface ILogin {
    interface View extends IBaseView {
        void loginReturn(LoginBean loginBean);
        void getRegister(RegisterBean registerBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void login(String username,String pw);
        void getRegister(String username, String pw);
    }


    interface Model extends IBaseModel {
        void login(String username,String pw, Callback callback);
        void getRegister(String username, String pw, Callback callback);
    }
}
