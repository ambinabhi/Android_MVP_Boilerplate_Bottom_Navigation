package com.oneday.login;

import android.app.Activity;

import com.oneday.utils.DaConstants;

import static com.oneday.utils.ConnectionUtils.isConnectedToNetwork;
import static com.oneday.utils.ValidationRegex.isRegexValid;

/**
 * Created by Abhilash on 29/03/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView mLoginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void validateCredentials(Activity activity, String username, String password) {
        if (username.trim().length() == 0) {
            mLoginView.onLoginError("Please enter user name");
        } else if (!isRegexValid(username, DaConstants.REGEX_USERNAME)) {
            mLoginView.onLoginError("Please enter valid username");
        } else if (password.length() == 0) {
            mLoginView.onLoginError("Please enter password");
        } else {
            if (isConnectedToNetwork(activity)) {
               mLoginView.onLoginSuccess();
            } else {
                mLoginView.onLoginError("No internet connection");
            }
        }
    }
}
