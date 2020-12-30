package com.example.shopping.interfaces.me;

import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.IBaseModel;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.shop.IShop;
import com.example.shopping.moudel.bean.ShopBean;

public interface IMe {
    interface MeView extends IBaseView {
        void getShopData(ShopBean shopBean);
    }
    interface MePresenter extends IBasePresenter<IShop.ShopView> {
        void getShopData(int size,int page);
    }
    interface MeMoudel extends IBaseModel {
        void getShopData(int size, int page, Callback<ShopBean> shopBeanCallback);
    }
}
