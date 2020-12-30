package com.example.demo2.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.demo2.R;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.bean.LoginBean;
import com.example.demo2.bean.RegisterBean;
import com.example.demo2.interfaces.demo.ILogin;
import com.example.demo2.presenter.LoginPresenter;
import com.example.demo2.utils.SpUtils;
import com.example.demo2.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View {
    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_login_zc)
    TextView tvLoginZc;
    @BindView(R.id.tv_login_wj)
    TextView tvLoginWj;
    private String register_token;
    private String register_username;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected ILogin.Presenter createPrenter() {
        return new LoginPresenter();
    }


    @Override
    protected void initView() {
        imgPw.setTag(1);

        Intent intent = getIntent();
        register_token = intent.getStringExtra("register_token");
        register_username = intent.getStringExtra("register_username");

        tvLoginZc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        //presenter.login();
    }


    @OnClick({R.id.btn_login, R.id.img_pw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.img_pw:
                int tag = (int) imgPw.getTag();
                if (tag == 1) {
                    imgPw.setImageResource(R.mipmap.ic_pw_open);
                    imgPw.setTag(2);
                    inputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgPw.setImageResource(R.mipmap.ic_pw_close);
                    imgPw.setTag(1);
                    inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }

    private void login() {
        String username = inputUsername.getText().toString();
        String pw = inputPw.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)) {
            presenter.login(username, pw);
            ToastUtils.s(this, getString(R.string.tips_logins));
        } else {
            ToastUtils.s(this, getString(R.string.tips_login));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void loginReturn(LoginBean loginBean) {
        if (!TextUtils.isEmpty(loginBean.getData().getToken())) {
            SpUtils.getInstance().setValue("token", loginBean.getData().getToken());
            SpUtils.getInstance().setValue("uid", loginBean.getData().getUserInfo().getUid());
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 100){
            String regist_name = data.getStringExtra("name");
            String regist_pw = data.getStringExtra("pw");
            inputUsername.setText(regist_name);
            inputPw.setText(regist_pw);
        }
    }

    @Override
    public void getRegister(RegisterBean registerBean) {

    }


}
