package com.oneday.base;

import android.app.Application;
import android.content.Context;
import com.oneday.preference.DaPreference;
import com.splunk.mint.Mint;
import com.squareup.otto.Bus;

/**
 * Created by Abhilash on 29/03/2018.
 */


public class DaBaseApplication extends Application {

    private static Bus sEventBus;
    private static Context context;
    private static DaBaseApplication mApplication;

    public static Bus getEventBus() {
        if (sEventBus == null) {
            synchronized (Bus.class) {
                if (sEventBus == null) {
                    sEventBus = new Bus();
                }
            }
        }
        return sEventBus;
    }


    public static void setEventBus(Bus mockBus) {
        sEventBus = mockBus;
    }

    public static Context getAppContext() {
        return DaBaseApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaPreference.startWith(getApplicationContext());
        Mint.initAndStartSession(this, "b4540f9e");
        DaBaseApplication.context = getApplicationContext();
        mApplication = this;
    }
}
