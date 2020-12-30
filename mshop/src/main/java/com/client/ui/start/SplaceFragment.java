package com.client.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.client.MainActivity;
import com.client.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用来显示启动页的效果
 */
public class SplaceFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.txt_time)
    TextView txtTime;
    @BindView(R.id.txt_comein)
    TextView txtComeIn;

    private static int time = 15; //15-10区间不能跳过 < 10 可以跳过

    private int index; //当前页面的下标
    private boolean live; //当前页面是显示状态

    public static SplaceFragment getInstance(int index){
        SplaceFragment fragment = new SplaceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index",index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        live = isVisibleToUser;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splace,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initListener();
        time = 15;
        index = getArguments().getInt("index");
        txtComeIn.setVisibility(View.GONE);
        int resId=0;
        if(index == 1){
            resId = R.mipmap.start_one;
        }else if(index == 2){
            resId = R.mipmap.start_two;
        }else if(index == 3){
            resId = R.mipmap.start_three;
        }
        img.setImageResource(resId);
        if(index == 3){
            txtTime.setVisibility(View.VISIBLE);
            //开启倒计时
            startTime();
        }else{
            txtTime.setVisibility(View.GONE);
        }
    }

    private void initListener(){
        txtComeIn.setOnClickListener(this);
        txtTime.setOnClickListener(this);
    }



    private MyHandler myHndler = new MyHandler(this);

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("TAG",Thread.currentThread().getName());
            time--;
            if(time < 0){
                //调整到主页
                goMain();
            }else{
                Message msg = new Message();
                msg.what = 1;
                if(time < 10){
                    // 倒计时 跳过+time 立即体验按钮显示
                    msg.obj = "跳过"+time;
                    msg.arg1 = 10;
                }else{
                    //time
                    msg.obj = time;
                }
                myHndler.sendMessage(msg);
                //只有页面在显示的情况下继续倒计时
                if(live){
                    myHndler.postDelayed(this,1000);
                }
            }
        }
    };

    private void startTime(){
        myHndler.postDelayed(runnable,1000);
    }

    private void goMain(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * 设置倒计时的显示
     * @param str
     */
    private void setTime(String str){
        txtTime.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_comein:
                goMain();
                break;
            case R.id.txt_time:
                goMain();
                break;
        }
    }


    /**
     * 用来解决handler引起的内存泄露问题 原因：内部类默认持有外部类的引用
     */
    static class MyHandler extends Handler{

        WeakReference<SplaceFragment> weakReference;
        SplaceFragment splaceFragment;
        public MyHandler(SplaceFragment fragment){
            weakReference = new WeakReference<SplaceFragment>(fragment);
            splaceFragment = weakReference.get();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    splaceFragment.setTime(String.valueOf(msg.obj));
                    if(msg.arg1 == 10){
                        splaceFragment.txtComeIn.setVisibility(View.VISIBLE);
                        splaceFragment.txtComeIn.setEnabled(true);
                        splaceFragment.txtTime.setEnabled(true);
                    }else{
                        splaceFragment.txtComeIn.setVisibility(View.GONE);
                        splaceFragment.txtComeIn.setEnabled(false);
                        splaceFragment.txtTime.setEnabled(false);
                    }
                    break;
            }
        }
    }

}
