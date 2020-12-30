package com.example.shopping.interfaces.shop;

import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.IBaseModel;
import com.example.shopping.interfaces.IBasePresenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.moudel.bean.ShopBean;

public interface IShop {
    interface ShopView extends IBaseView{
        void getShopData(ShopBean shopBean);
    }
    interface ShopPansenter extends IBasePresenter<ShopView> {
        void getShopData(int size,int page);
    }
    interface ShopMoudel extends IBaseModel{
        void getShopData(int size, int page, Callback<ShopBean>shopBeanCallback);
    }
}
