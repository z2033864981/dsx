package com.client.presenter.home;

import com.client.base.BasePresenter;
import com.client.interfaces.Callback;
import com.client.interfaces.home.ICategory;
import com.client.model.home.CategoryBean;
import com.client.model.home.CategoryGoodBean;
import com.client.model.home.CategoryModel;

public class CategoryPresenter extends BasePresenter<ICategory.View> implements ICategory.Presenter {

    ICategory.Model model;

    public CategoryPresenter(){
        model = new CategoryModel();
    }

    @Override
    public void getCategoryTab(int categoryId) {
        model.getCategory(categoryId, new Callback<CategoryBean>() {
            @Override
            public void success(CategoryBean data) {
                if (mView != null){
                    mView.getCategoryTabReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getCategoryGood(int categoryId, int page, int size) {
        model.getCategoryGood(categoryId, page, size, new Callback<CategoryGoodBean>() {
            @Override
            public void success(CategoryGoodBean data) {
                if(mView != null){
                    mView.getCategoryGoodReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }


}
