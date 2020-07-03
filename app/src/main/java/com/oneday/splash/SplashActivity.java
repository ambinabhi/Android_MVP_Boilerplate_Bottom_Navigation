package com.oneday.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.oneday.R;
import com.oneday.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abhilash on 28/03/2018.
 */

public class SplashActivity extends AppCompatActivity implements SplashView  {
    SplashPresenter presenter;
    @BindView(R.id.iv_splash)
    KenBurnsView ImageViewSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setResources();
        presenter = new SplashPresenterImpl(this);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JumpToHomeScreen();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 5000);

    }

    private void setResources() {
       ImageViewSplash.setImageResource(R.drawable.splash);
    }

    public void JumpToHomeScreen() {
       /* if (UaPreference.getBoolean(AppConstant.SP_IS_USER_LOGGED_IN)) {
            Intent JumpToHomeScreen = new Intent(SplashActivity.this,
                    ZonesActivity.class);
            startActivity(JumpToHomeScreen);
            finish();

        } else {*/
            Intent JumpToHomeScreen1 = new Intent(SplashActivity.this,
                    LoginActivity.class);
            startActivity(JumpToHomeScreen1);
            finish();
      //  }
    }

    @Override
    public void onBackPressed() {
        finish();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
