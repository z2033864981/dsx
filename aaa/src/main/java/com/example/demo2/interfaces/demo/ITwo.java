package com.example.demo2.interfaces.demo;

import com.example.demo2.bean.TwoBean;
import com.example.demo2.bean.TwooBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

public interface ITwo {
    interface View extends IBaseView{
        void getTwoReturn(TwoBean twoBean);
        void getTwooReturn(TwooBean twooBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void getTwo();
        void getTwoo();
    }

    interface Model extends IBaseModel{
        void getTwo(Callback callback);
        void getTwoo(Callback callback);

    }
}
