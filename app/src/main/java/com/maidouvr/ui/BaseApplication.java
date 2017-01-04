package com.maidouvr.ui;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by stone on 2017/1/4.
 * Project:maidouvr_android
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

}
