package com.oneday.base;

import android.support.v4.app.Fragment;

/**
 * Created by Abhilash on 30/03/2018.
 */

public class DaBaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        DaBaseApplication.getEventBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        DaBaseApplication.getEventBus().unregister(this);
    }
}