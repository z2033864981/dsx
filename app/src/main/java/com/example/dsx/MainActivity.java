package com.example.dsx;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rec;
    private ArrayList<Bean.DataBean> list;
    private RecAdapter recAdapter;
    private Button btn;
    private ArrayList<Bean.DataBean> dataBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    private void getData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<Bean.DataBean> data = bean.getData();
                        list.addAll(data);
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setmSelect(list.get(i).isSelect());
                        }
                        recAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = e.toString();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    boolean aa;
    private void initView() {
        rec = (RecyclerView) findViewById(R.id.rec);
        rec.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        dataBeans = new ArrayList<>();
        recAdapter = new RecAdapter(list, this);
        rec.setAdapter(recAdapter);
        recAdapter.setClick(new RecAdapter.Click() {
            @Override
            public void clickItem(int pos, boolean b) {
                boolean b2 = list.get(pos).getmSelect();
                if (b2){
                    list.get(pos).setmSelect(false);
                }else {
                    list.get(pos).setmSelect(true);
                }
            }
        });
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }
    boolean b1=true;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                isData();
                break;
        }
    }

    private void isData() {
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isSelect()==list.get(i).getmSelect()){
                Log.i("TAG", "isData: "+list.get(i).getName());
            }

        }
    }

}