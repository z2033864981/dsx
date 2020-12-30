package com.example.shopping.ui.car;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shopping.R;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.car.ICar;
import com.example.shopping.moudel.bean.LoginBean;
import com.example.shopping.pansenter.car.LoginPresenter;
import com.example.shopping.utils.PreferencesUtil;
import com.example.shopping.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ICar.LoginView, View.OnClickListener {
    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private LoginPresenter loginPresenter;

    //密码必须是8位以上  字母+数字组合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPrenter() {
        loginPresenter = new LoginPresenter();
        SpUtils.getInstance().setValue("bbb",222);

        return loginPresenter;
    }

    @Override
    protected void initView() {
        imgPw.setTag(1);
    }

    @Override
    protected void initData() {

    }





    @OnClick({R.id.img_pw, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.img_pw:
                showPwd();
                break;
        }
    }



    private void login() {
        String pwd = inputPw.getText().toString();
        String username = inputUsername.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)){
            loginPresenter.getLogin(username,pwd);
        }else {
            Toast.makeText(this, "你的账号或密码有误", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPwd() {
        int tag = (int) imgPw.getTag();
        if (tag==1){
            imgPw.setImageResource(R.mipmap.ic_pw_open);
            imgPw.setTag(2);
            inputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else {
            imgPw.setImageResource(R.mipmap.ic_pw_close);
            imgPw.setTag(1);
            inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override
    public void getLogin(LoginBean loginBean) {
        if (loginBean.getData().getToken()!=null){
            SpUtils.getInstance().setValue("token",loginBean.getData().getToken()+"");
            SpUtils.getInstance().setValue("uid",loginBean.getData().getUserInfo().getUid()+"");
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
