package com.example.shopping.pansenter.car;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.car.ICar;
import com.example.shopping.moudel.bean.LoginBean;
import com.example.shopping.moudel.car.LoginMoudel;
import com.example.shopping.ui.car.LoginActivity;

public class LoginPresenter extends BasePresenter<ICar.LoginView>implements ICar.LoginPansenter {
    ICar.LoginMoudel moudel;

    public LoginPresenter() {

        moudel=new LoginMoudel();
    }

    @Override
    public void getLogin(String userName, String password) {
        moudel.getLogin(userName, password, new Callback<LoginBean>() {
            @Override
            public void success(LoginBean data) {
                if (mView!=null&&data!=null){
                    mView.getLogin(data);
                }

            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
