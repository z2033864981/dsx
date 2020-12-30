package com.example.demo2.interfaces.demo;

import com.example.demo2.bean.ChannelBean;
import com.example.demo2.bean.ChannelTypeBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

public interface IChannel { //Channel业务下的View
    interface View extends IBaseView {
        void getChannelReturn(ChannelBean result);
        void getChannelTypeReturn(ChannelTypeBean result);
    }
    //Channel业务下的Presenter
    interface Presenter extends IBasePresenter<View> {
        void getChannel(String id);
        void getChannelType(String id);
    }

    //Channel业务下的Model
    interface BaseModel extends IBaseModel {
        void getChannel(String id, Callback callback);
        void getChannelType(String id, Callback callback);
    }
}
