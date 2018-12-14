package com.fsk.week_3_1214;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者:  方诗康
 * 描述:
 */
public class JPuShApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
