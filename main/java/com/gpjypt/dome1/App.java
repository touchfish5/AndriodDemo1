package com.gpjypt.dome1;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
