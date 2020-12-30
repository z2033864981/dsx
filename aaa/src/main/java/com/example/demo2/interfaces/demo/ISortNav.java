package com.example.demo2.interfaces.demo;

import com.example.demo2.bean.SortNavBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

public interface ISortNav {
    interface View extends IBaseView {
        void getSortNavReturn(SortNavBean sortNavBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getSortNav();
    }

    interface Model extends IBaseModel {
        void getSortNav(Callback callBack);
    }
}
