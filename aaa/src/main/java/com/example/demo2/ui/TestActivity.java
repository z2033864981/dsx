package com.example.demo2.ui;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demo2.R;
import com.example.demo2.ui.home.FiveFragment;
import com.example.demo2.ui.home.FourFragment;
import com.example.demo2.ui.home.OneFragment;
import com.example.demo2.ui.demo.CategoryTabFragment;
import com.example.demo2.ui.home.ThreeFragment;
import com.example.demo2.ui.home.TwoFragment;
import com.google.android.material.tabs.TabLayout;

public class TestActivity extends AppCompatActivity {

    private TabLayout mTable;
    private OneFragment oneFragment;
    private FragmentManager supportFragmentManager;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private FiveFragment fiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        initFragment();
        initTab();
    }

    private void initTab() {
        //添加Tab页
        mTable.addTab(mTable.newTab().setText("首页").setIcon(R.drawable.tab_shop1));
        mTable.addTab(mTable.newTab().setText("专题").setIcon(R.drawable.tab_shop2));
        mTable.addTab(mTable.newTab().setText("分类").setIcon(R.drawable.tab_shop3));
        mTable.addTab(mTable.newTab().setText("购物车").setIcon(R.drawable.tab_shop4));
        mTable.addTab(mTable.newTab().setText("我的").setIcon(R.drawable.tab_shop5));
        //通过tablayout的监听器，实现和fragment的联动
        mTable.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //开启事务
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                if (tab.getPosition()==0){
                    fragmentTransaction.show(oneFragment).hide(twoFragment).hide(threeFragment).hide(fourFragment).hide(fiveFragment);
                }else if (tab.getPosition()==1){
                    fragmentTransaction.show(twoFragment).hide(oneFragment).hide(threeFragment).hide(fourFragment).hide(fiveFragment);
                }else if (tab.getPosition()==2){
                    fragmentTransaction.show(threeFragment).hide(oneFragment).hide(twoFragment).hide(fourFragment).hide(fiveFragment);
                }else if (tab.getPosition()==3){
                    fragmentTransaction.show(fourFragment).hide(oneFragment).hide(twoFragment).hide(threeFragment).hide(fiveFragment);
                }else {
                    fragmentTransaction.show(fiveFragment).hide(oneFragment).hide(twoFragment).hide(fourFragment).hide(threeFragment);
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initFragment() {
        //准备一个fragment 分别调用三次 ，创建三个对象来进行打开福利接口，匹配Tab页面
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();
        fiveFragment = new FiveFragment();


        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()//放入布局管理器
        .add(R.id.rl_main,oneFragment)
        .add(R.id.rl_main,twoFragment)
        .add(R.id.rl_main,threeFragment)
        .add(R.id.rl_main,fourFragment)
        .add(R.id.rl_main,fiveFragment)
                .hide(twoFragment)//隐藏
                .hide(threeFragment)//隐藏
                .hide(fourFragment)//隐藏
                .hide(fiveFragment)//隐藏
                .commit();//提交事物

    }

    private void initView() {
        mTable = (TabLayout) findViewById(R.id.table);
    }

}
