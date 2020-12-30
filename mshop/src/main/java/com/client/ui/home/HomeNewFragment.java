package com.client.ui.home;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.client.R;
import com.client.base.BaseFragment;
import com.client.interfaces.home.IHome;
import com.client.model.home.HomeBean;
import com.client.presenter.home.HomePresenter;

import butterknife.BindView;

public class HomeNewFragment extends BaseFragment<IHome.Presenter> implements IHome.View {
    @BindView(R.id.recy_home)
    RecyclerView recyclerView;

    DelegateAdapter delegateAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_new;
    }

    @Override
    protected HomePresenter createPrenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        //设置layout管理对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        //设置回收复用池大小
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,20);

        delegateAdapter = new DelegateAdapter(layoutManager,true);
        recyclerView.setAdapter(delegateAdapter);

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);

    }

    @Override
    protected void initData() {
        presenter.getHome();
    }

    @Override
    public void getHomeReturn(HomeBean result) {

    }
}
