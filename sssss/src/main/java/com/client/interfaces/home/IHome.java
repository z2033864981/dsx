package com.client.interfaces.home;

import com.client.interfaces.Callback;
import com.client.interfaces.IBaseModel;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.IBaseView;
import com.client.model.home.HomeBean;

public interface IHome {

    interface View extends IBaseView{
        void getHomeReturn(HomeBean result);
    }

    interface Presenter extends IBasePresenter<View>{
        void getHome();
    }

    interface Model extends IBaseModel{
        void getHome(Callback callback);
    }

}
