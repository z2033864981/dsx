package com.example.demo2.interfaces.demo;



import com.example.demo2.bean.HotGoodListBean;
import com.example.demo2.bean.NewGoodsBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

import java.util.HashMap;

public interface IHotGood {
    interface View extends IBaseView {
        void getHotGood(HotGoodListBean result);
        void getNewGood(NewGoodsBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHotGood(HashMap<String, String> map);
        void getNewGood();
    }

    interface Model extends IBaseModel {
        void getHotGood(HashMap<String, String> map, Callback callback);
        void getNewGood(Callback<NewGoodsBean> callback);
    }
}
