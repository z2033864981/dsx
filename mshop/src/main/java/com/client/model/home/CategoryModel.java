package com.client.model.home;

import com.client.base.BaseModel;
import com.client.interfaces.Callback;
import com.client.interfaces.home.ICategory;
import com.client.net.CommonSubscriber;
import com.client.net.HttpManager;
import com.client.utils.RxUtils;

public class CategoryModel extends BaseModel implements ICategory.Model {
    @Override
    public void getCategory(int categoryid,Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getCategory(categoryid).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryBean>(callback) {
                    @Override
                    public void onNext(CategoryBean categoryBean) {
                        callback.success(categoryBean);
                    }
                }));
    }

    @Override
    public void getCategoryGood(int categoryId, int page, int size, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getGoodList(categoryId,page,size).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryGoodBean>(callback) {
                    @Override
                    public void onNext(CategoryGoodBean categoryBean) {
                        callback.success(categoryBean);
                    }
                }));
    }
}
