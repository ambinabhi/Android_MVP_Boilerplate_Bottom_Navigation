package com.oneday.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;

import com.oneday.utils.DaToast;
import com.oneday.utils.ProgressUtils;
import com.oneday.R;
import com.oneday.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Abhilash on 28/03/2018.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.email)
    AppCompatEditText mEtLoginUsername;

    @BindView(R.id.et_login_password)
    AppCompatEditText mEtLoginPassword;

    @BindView(R.id.btn_login)
    Button mBtnLoginSubmit;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.btn_login)
    void validateCredentials() {
        ProgressUtils.showProgress(LoginActivity.this, "Loading");
        loginPresenter.validateCredentials(LoginActivity.this, mEtLoginUsername.getText().toString().trim(), mEtLoginPassword.getText().toString());
    }

    @Override
    public void onLoginError(String error) {
        ProgressUtils.hideProgress();
        DaToast.showShort(this,error);
    }

    @Override
    public void onLoginSuccess() {
        ProgressUtils.hideProgress();
        startActivity(new Intent(this, HomeActivity.class));
    }
}
