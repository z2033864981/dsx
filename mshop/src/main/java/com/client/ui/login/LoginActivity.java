package com.client.ui.login;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.client.R;
import com.client.base.BaseActivity;
import com.client.interfaces.IBasePresenter;
import com.client.interfaces.login.ILogin;
import com.client.model.login.LoginBean;
import com.client.presenter.login.LoginPresenter;
import com.client.utils.SpUtils;
import com.luck.picture.lib.tools.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 * 密码必须是8位以上  字母+数字组合
 */

public class LoginActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View {

    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.img_pw)
    ImageView imgPw;

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
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_login,R.id.img_pw})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.img_pw:
                int tag = (int) imgPw.getTag();
                if(tag == 1){
                    imgPw.setImageResource(R.mipmap.ic_pw_open);
                    imgPw.setTag(2);
                    inputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    imgPw.setImageResource(R.mipmap.ic_pw_close);
                    imgPw.setTag(1);
                    inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }

    private void login(){
        String username = inputUsername.getText().toString();
        String pw = inputPw.getText().toString();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pw)){
            presenter.login(username,pw);
        }else{
            ToastUtils.s(this,getString(R.string.tips_login));
        }
    }


    @Override
    public void loginReturn(LoginBean loginBean) {
        if(!TextUtils.isEmpty(loginBean.getData().getToken())){
            SpUtils.getInstance().setValue("token",loginBean.getData().getToken());
            SpUtils.getInstance().setValue("uid",loginBean.getData().getUserInfo().getUid());
            SpUtils.getInstance().setValue("nickname",loginBean.getData().getUserInfo().getNickname());
            SpUtils.getInstance().setValue("avatar",loginBean.getData().getUserInfo().getAvatar());
            SpUtils.getInstance().setValue("username",loginBean.getData().getUserInfo().getUsername());
            SpUtils.getInstance().setValue("mark","");
            finish();
        }
    }
}
