package com.gpjypt.dome1.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.gpjypt.dome1.App;
import com.gpjypt.dome1.R;


public class StaticConfig {
    public static final String USER_HEADER = "user_";
    public static final String PWD_HEADER="pwd_";
    public static int inputTypeHide = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
    public static int inputTypeShow = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    public static String IS_LOGIN_KEY="is_login_key";


    @SuppressLint("WrongConstant")
    public static void toast(String msg){
        Toast.makeText(App.mContext,msg,2000).show();
    }

    public static void setPwdEditView(final EditText etPwd, final ImageView imageView){
        etPwd.setInputType(inputTypeHide);
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if (etPwd.getInputType() == inputTypeHide) {
                    etPwd.setInputType(inputTypeShow);
                    imageView.setImageDrawable(App.mContext.getDrawable(R.drawable.login_xianshi_mima));

                } else {
                    etPwd.setInputType(inputTypeHide);
                    imageView.setImageDrawable(App.mContext.getDrawable(R.drawable.login_yinccang_mima));
                }

            }
        });
    }
}
