package com.client.ui.me;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.client.R;
import com.client.base.BaseFragment;
import com.client.interfaces.IBasePresenter;
import com.client.ui.login.LoginActivity;
import com.client.utils.ImageLoader;
import com.client.utils.SpUtils;
import com.client.utils.TxtUtils;

import butterknife.BindView;

public class MeFragment extends BaseFragment implements View.OnClickListener {


    public static final int LOGIN_ME = 10001; //登录成功的回传值
    public static final int LOGINOUT_ME = 10002; //退出登录的回传

    @BindView(R.id.layout_userinfo)
    ConstraintLayout layoutUserInfo;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_mark)
    TextView txtMark;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.btn_loginout)
    Button btnLoginOut;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        layoutUserInfo.setOnClickListener(this);
        btnLoginOut.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        if(!TextUtils.isEmpty(token)){
            isLogin(true);
        }else{
            isLogin(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_userinfo:
                String token = SpUtils.getInstance().getString("token");
                if(!TextUtils.isEmpty(token)){
                    openUserInfoDetail();
                }else{
                    openLogin();
                }
                break;
        }
    }

    /**
     * 登录成功
     */
    public void loginSuccess(){
        isLogin(true);
    }

    /**
     * 登录状态的界面控制
     * @param bool
     */
    private void isLogin(boolean bool){
        if(bool){
            txtLogin.setVisibility(View.GONE);
            txtNickname.setVisibility(View.VISIBLE);
            txtMark.setVisibility(View.VISIBLE);
            String username = SpUtils.getInstance().getString("username");
            String nickname = SpUtils.getInstance().getString("nickname");
            String avatar = SpUtils.getInstance().getString("avatar");
            String mark = SpUtils.getInstance().getString("mark");
            if(!TextUtils.isEmpty(nickname)){
                txtNickname.setText(nickname);
            }else{
                txtNickname.setText(username);
            }
            ImageLoader.loadImage(avatar,imgAvatar);
            TxtUtils.setTextView(txtMark,mark);
        }else{
            txtLogin.setVisibility(View.VISIBLE);
            txtNickname.setVisibility(View.GONE);
            txtMark.setVisibility(View.GONE);
        }
    }

    /**
     * 打开登录页面
     */
    private void openLogin(){
        Intent intent = new Intent(mContext, LoginActivity.class);
        getActivity().startActivityForResult(intent,LOGIN_ME);
    }

    private void openUserInfoDetail(){
        Intent intent = new Intent(mContext,UserInfoDetailActivity.class);
        startActivityForResult(intent,100);
    }

    /**
     * 退出登录
     */
    private void loginOut(){

    }
}
