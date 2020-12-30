package com.client.interfaces.me;

import com.client.interfaces.Callback;
import com.client.interfaces.IBaseModel;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.IBaseView;
import com.client.interfaces.shop.ICar;
import com.client.model.me.UserInfoBean;
import com.client.model.shop.CarBean;
import com.client.model.shop.DeleteCarBean;
import com.client.model.shop.UpdateCarBean;

import java.util.Map;

public interface IUser {
    interface View extends IBaseView {
        void updateUserInfoReturn(UserInfoBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void updateUserInfo(Map<String,String> map);
    }


    interface Model extends IBaseModel {
        void updateUserInfo(Map<String,String> map,Callback callback);
    }
}
