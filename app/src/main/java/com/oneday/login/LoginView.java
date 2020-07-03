package com.oneday.login;

/**
 * Created by Abhilash on 28/03/2018.
 */

public interface LoginView {
    void onLoginError(String error);

    void onLoginSuccess();
}
