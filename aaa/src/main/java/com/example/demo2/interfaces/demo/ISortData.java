package com.example.demo2.interfaces.demo;

import com.example.demo2.bean.SortDataBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

public interface ISortData {
    interface View extends IBaseView {
        void getSortDataReturn(SortDataBean sortDataBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getSortData(int id);
    }

    interface Model extends IBaseModel {
        void getSortData(int id, Callback callBack);
    }
}
