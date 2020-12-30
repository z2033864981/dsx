package com.client.ui.test;

import com.client.R;
import com.client.base.BaseActivity;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.test.ITest;
import com.client.model.test.TestBean;
import com.client.presenter.test.TestPresenter;

public class TestActivity extends BaseActivity<ITest.Presenter> implements ITest.View {
    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected TestPresenter createPrenter() {
        return new TestPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getList();
    }

    @Override
    public void getListReturn(TestBean result) {

    }
}
