package com.example.shopping.pansenter.shop;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.interfaces.Callback;
import com.example.shopping.interfaces.shop.IShop;
import com.example.shopping.moudel.bean.ShopBean;
import com.example.shopping.moudel.shop.ShopMoudel;
import com.example.shopping.ui.shop.ShopFragment;

public class ShopPansenter extends BasePresenter<IShop.ShopView>implements IShop.ShopPansenter {
    IShop.ShopView shopView;
    IShop.ShopMoudel shopMoudel;
    public ShopPansenter(ShopFragment shopFragment) {
        shopView=shopFragment;
        shopMoudel=new ShopMoudel();
    }

    @Override
    public void getShopData(int size, int page) {
        shopMoudel.getShopData(size, page, new Callback<ShopBean>() {
            @Override
            public void success(ShopBean data) {
                shopView.getShopData(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
