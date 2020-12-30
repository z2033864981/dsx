package com.client.interfaces.test;

import com.client.interfaces.Callback;
import com.client.interfaces.IBaseModel;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.IBaseView;
import com.client.model.test.TestBean;

public interface ITest {

    interface View extends IBaseView{
        void getListReturn(TestBean testBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void getList();
    }


    interface Model extends IBaseModel{
        void getList(Callback callback);
    }

}
