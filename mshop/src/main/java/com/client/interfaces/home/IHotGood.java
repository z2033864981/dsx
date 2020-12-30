package com.client.interfaces.home;

import com.client.interfaces.Callback;
import com.client.interfaces.IBaseModel;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.IBaseView;
import com.client.model.home.HomeBean;
import com.client.model.home.HotGoodListBean;

import java.util.HashMap;

public interface IHotGood {
    interface View extends IBaseView {
        void getHotGood(HotGoodListBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHotGood(HashMap<String,String> map);
    }

    interface Model extends IBaseModel {
        void getHotGood(HashMap<String,String> map,Callback callback);
    }
}
