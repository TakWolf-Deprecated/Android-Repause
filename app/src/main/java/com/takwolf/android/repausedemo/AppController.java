package com.takwolf.android.repausedemo;

import android.app.Application;
import android.util.Log;

import com.takwolf.android.repause.Repause;

public class AppController extends Application implements Repause.Listener {

    private static final String TAG = "AppController";

    @Override
    public void onCreate() {
        super.onCreate();
        Repause.init(this);
        Repause.registerListener(this);
    }

    @Override
    public void onApplicationResumed() {
        Log.d(TAG, "Application Resumed");
        ToastUtils.with(this).show("Application Resumed");
    }

    @Override
    public void onApplicationPaused() {
        Log.d(TAG, "Application Paused");
        ToastUtils.with(this).show("Application Paused");
    }

}
