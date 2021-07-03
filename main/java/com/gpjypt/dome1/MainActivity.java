package com.gpjypt.dome1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gpjypt.dome1.activity.LoginActivity;
import com.gpjypt.dome1.utils.SPUtils;
import com.gpjypt.dome1.utils.StaticConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (SPUtils.getBoolean(StaticConfig.IS_LOGIN_KEY)) {

                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            } else {
                                //当前界面跳转到登录界面
                                //表明意图
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            finish();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}