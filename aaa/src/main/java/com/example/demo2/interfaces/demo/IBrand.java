package com.example.demo2.interfaces.demo;

import com.example.demo2.bean.BrandBean;
import com.example.demo2.bean.BrandDetail1;
import com.example.demo2.bean.BrandDetail2;
import com.example.demo2.bean.ChannelBean;
import com.example.demo2.bean.ChannelTypeBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

public interface IBrand {
    interface View extends IBaseView {
        void getBrandReturn(BrandBean result);
        void getBrandDetail1Return(BrandDetail1 result);
        void getBrandDetail2Return(BrandDetail2 result);
    }
    //Channel业务下的Presenter
    interface Presenter extends IBasePresenter<View> {
        void getBrand();
        void getBrandDetail1(int id);
        void getBrandDetail2(int brandId,String page,String size);
    }

    //Channel业务下的Model
    interface Model extends IBaseModel {
        void getBrand(Callback callback);
        void getBrandDetail1(int id,Callback callback);
        void getBrandDetail2(int brandId,String page,String size,Callback callback);
    }
}
