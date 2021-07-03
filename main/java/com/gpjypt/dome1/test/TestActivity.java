package com.gpjypt.dome1.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gpjypt.dome1.R;

public class TestActivity extends AppCompatActivity {

    //初次进入界面时调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);


        //fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //创建Fragment
        final TestFragment testFragment = new TestFragment();
        final TestFragment2 testFragment2 = new TestFragment2();
        //添加fragment
        fragmentTransaction.add(R.id.testFrameLayout, testFragment, "testFragment");
        fragmentTransaction.add(R.id.testFrameLayout, testFragment2, "testFragment2");
//        fragmentManager.findFragmentByTag("testFragment");
        //提交事务
        fragmentTransaction.commit();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragment的显示隐藏
                //获取fragment的管理器
                FragmentManager fragmentManager = getSupportFragmentManager();
                //创建事务
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                //隐藏已显示的fragment
                //判断是否隐藏
                if (!testFragment2.isHidden())
                    //隐藏testFragment2
                    fragmentTransaction1.hide(testFragment2);
                //显示testFragment
                fragmentTransaction1.show(testFragment);
                //提交事务
                fragmentTransaction1.commit();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragment管理器
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.hide(testFragment);
                fragmentTransaction1.show(testFragment2);
                fragmentTransaction1.commit();
            }
        });


//        startActivityForResult(new Intent(TestActivity.this, LoginActivity.class),2000);
    }
//    //页面启动时调用
//    @Override
//    protected void onStart() {
//        super.onStart();
//        LogUtils.logD("onStart-----");
//    }
//    //界面显示的时候调用
//    @Override
//    protected void onResume() {
//        super.onResume();
//        LogUtils.logD("onResume-----");
//    }
//    //界面重新显示的时候调用
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        LogUtils.logD("onRestart-----");
//    }
//    //暂停的时候调用
//    @Override
//    protected void onPause() {
//        super.onPause();
//        LogUtils.logD("onPause-----");
//    }
//    //停止时调用
//    @Override
//    protected void onStop() {
//        super.onStop();
//        LogUtils.logD("onStop-----");
//    }
//
//    //界面销毁的时候调用
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        LogUtils.logD("onDestroy-----");
//    }
//    //对应startActivityForResult（）这个启动方法使用：一般是在我们需要用到跳转到另一个activity的返回值的时候使用
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        LogUtils.logD("onActivityResult-----");
//    }
}