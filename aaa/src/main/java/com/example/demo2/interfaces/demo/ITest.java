package com.example.demo2.interfaces.demo;


import com.example.demo2.bean.OneBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

public interface ITest {

    interface View extends IBaseView {
        void getListReturn(OneBean oneBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getList();
    }


    interface Model extends IBaseModel {
        void getList(Callback callback);
    }

}
