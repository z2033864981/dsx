package com.example.shopping.interfaces.car;

import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.IBaseModel;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.moudel.bean.LoginBean;
import com.example.shopping.moudel.bean.SortBean;
import com.example.shopping.moudel.bean.SortDescBean;
import com.example.shopping.moudel.bean.SortTabBean;
import com.example.shopping.moudel.bean.SortTabDescBean;

public interface ICar {
    interface LoginView extends IBaseView{
        void getLogin(LoginBean loginBean);
    }
    interface LoginPansenter extends IBasePresenter<LoginView>{
        void getLogin(String userName,String password);
    }

   interface LoginMoudel extends IBaseModel{
        void getLogin(String userName, String password, Callback<LoginBean> callback);
   }



}
