package com.gpjypt.dome1.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gpjypt.dome1.App;

public class SPUtils {
    private static final String fileName = "mysp_dome1";
    private static SharedPreferences sp = App.mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);

    //封装的一个存储String的方法
    public static void putString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static String getString(String key) {
        return sp.getString(key, "");
    }

    //封装的一个存储Boolean的方法
    public static void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static Boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

}
