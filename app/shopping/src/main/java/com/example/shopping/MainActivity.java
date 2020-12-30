package com.example.shopping;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping.ui.car.CarFragment;
import com.example.shopping.ui.home.HomeFragment;
import com.example.shopping.ui.me.MeFragment;
import com.example.shopping.ui.shop.ShopFragment;
import com.example.shopping.ui.sort.SortFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv;
    private Disposable subscribe;
    private LinearLayout lin;
    private PopupWindow popupWindow;
    private ViewPager vp;
    private TabLayout tab;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        initView();

 /*       BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);
//        navView.setTe
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_car,R.id.navigation_me)
                .build();
        NavController navController;

             navController = Navigation.findNavController(this, R.id.nav_host_fragment);



        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        */
//        navigation.setItemIconTintList(null);

    }
    //onWindowFocusChanged
    Handler mHandler=new Handler();
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
            if (type==false){
//                setPopw();
                type=true;
            }




    }

    private void setPopw() {


        View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.popw_flash, null);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(lin, Gravity.CENTER,0,0);
        tv = inflate.findViewById(R.id.tv);
        btn = inflate.findViewById(R.id.btn);
        ViewPager vp = inflate.findViewById(R.id.vp);
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View inflate1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.popw_flash_item, null);

                TextView tv2 = inflate1.findViewById(R.id.tv2);
                tv2.setText("第" + (position + 1) + "页");
                container.addView(inflate1);
                return inflate1;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    tv.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                    setTv();
                    type=true;
                } else {
                    tv.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);
                    if (subscribe!=null) {
                        subscribe.dispose();

                    }
                }
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    boolean type=false;
    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setTv() {
        subscribe = Observable.interval(0, 1, TimeUnit.SECONDS).take(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        long num = 3 - aLong;
                        tv.setText(num+"");
                        if (num == 0) {
                            popupWindow.dismiss();
                            subscribe.dispose();
                        }
                    }
                });
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        container = (LinearLayout) findViewById(R.id.container);
        lin = findViewById(R.id.lin);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShopFragment());
        fragments.add(new SortFragment());
        fragments.add(new CarFragment());
        fragments.add(new MeFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.select_home);
        tab.getTabAt(1).setText("专题").setIcon(R.drawable.select_sort);
        tab.getTabAt(2).setText("分类").setIcon(R.drawable.select_car);
        tab.getTabAt(3).setText("购物车").setIcon(R.drawable.select_shoping);
        tab.getTabAt(4).setText("我的").setIcon(R.drawable.select_shop);
//        setPopw();

    }

}
