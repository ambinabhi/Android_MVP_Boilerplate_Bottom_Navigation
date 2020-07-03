package com.oneday.login;

import android.app.Activity;

/**
 * Created by Abhilash on 29/03/2018.
 */

public interface LoginPresenter {
    void validateCredentials(Activity activity, String username, String password);
}
