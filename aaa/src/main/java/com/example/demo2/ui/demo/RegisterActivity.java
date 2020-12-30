package com.example.demo2.ui.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo2.R;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.bean.LoginBean;
import com.example.demo2.bean.RegisterBean;
import com.example.demo2.interfaces.demo.ILogin;
import com.example.demo2.presenter.LoginPresenter;
import com.example.demo2.utils.CodeUtils;
import com.example.demo2.utils.SpUtils;
import com.example.demo2.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View {

    @BindView(R.id.et_register_name)
    EditText et_Name;
    @BindView(R.id.et_register_pw)
    EditText et_Pw;
    @BindView(R.id.et_register_repwd)
    EditText et_Repwd;
    @BindView(R.id.et_register_code)
    EditText et_Code;
    @BindView(R.id.iv_register_code_img)
    ImageView iv_Code_Img;
    @BindView(R.id.btn_register)
    Button btn_Register;
    private String name;
    private String pwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected ILogin.Presenter createPrenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        //验证码
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();
        iv_Code_Img.setImageBitmap(bitmap);

    }

    @OnClick({R.id.iv_register_code_img, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_register_code_img:
                //点击更换验证码
                Bitmap bitmap = CodeUtils.getInstance().createBitmap();
                iv_Code_Img.setImageBitmap(bitmap);
                break;
            case R.id.btn_register:
                initRegist();
                break;
        }
    }

    private void initRegist() {
        name = et_Name.getText().toString();//用户名
        pwd = et_Pw.getText().toString();//密码
        String Repwd = et_Repwd.getText().toString();//确认密码

        String ver = et_Code.getText().toString();//验证码

        //不能为空
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(Repwd)) {
            //密码必须一致
            if (pwd.equals(Repwd)) {
                //密码大于6位
                if (pwd.length() >= 6) {
                    //验证码不为空
                    if (ver.equals("") || ver.length() != 0) {
                        // 取出sp中存入的username
                        String string = SpUtils.getInstance().getString(name);
                        Log.e("TAG", "initRegist: "+string );

                        //获得服务器的token
                        //String token = SpUtils.getInstance().getString("token");
                        //判断sp中是否有存入的username
                        if (!TextUtils.isEmpty(string)){     //如果有存入的
                            //用户名已经注册
                            ToastUtils.s(this,getString(R.string.tips_regist_re));
                            return;
                        }else{     //没有存入的
                            //1.注册
                            //2.将用户名最为key 密钥（token）作为value 存入sp (sp.....set)
                            presenter.getRegister(name, pwd);
                        }
                    } else {
                        ToastUtils.s(this, getString(R.string.tips_regist_ver));
                    }
                } else {
                    ToastUtils.s(this, getString(R.string.tips_regist_pw_6));
                }
            } else {
                ToastUtils.s(this, getString(R.string.tips_regist_pwd_repwd));
            }
        } else {
            ToastUtils.s(this, getString(R.string.tips_regist));
        }

    }

    @Override
    protected void initData() {
        presenter=new LoginPresenter();
    }

    @Override
    public void loginReturn(LoginBean loginBean) {

    }

    @Override
    public void getRegister(RegisterBean registerBean) {
        String token = registerBean.getData().getToken();

        //如果获得token不为空
        if(!TextUtils.isEmpty(token)) {
            //保存到sp中
            SpUtils.getInstance().setValue(name, token);
            //SpUtils.getInstance().setValue("token", token);
            SpUtils.getInstance().setValue("uid", registerBean.getData().getUserInfo().getUid());

            //回传值到登录界面
            String name = et_Name.getText().toString();
            String pw = et_Pw.getText().toString();
            Intent intent = getIntent();
            intent.putExtra("name", name);
            intent.putExtra("pw", pw);
            setResult(100, intent);

            finish();//关闭当前页面返回之前页面
        }else {
            finish();
        }
    }
}