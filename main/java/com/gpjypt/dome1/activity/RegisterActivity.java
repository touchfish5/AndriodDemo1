package com.gpjypt.dome1.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gpjypt.dome1.R;
import com.gpjypt.dome1.base.BaseActivity;
import com.gpjypt.dome1.utils.SPUtils;
import com.gpjypt.dome1.utils.StaticConfig;

public class RegisterActivity extends BaseActivity {
    EditText etUserName, etPwd1, etPwd2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
//        //实例化SharedPreferences
//        SharedPreferences sp = getSharedPreferences(fileName, MODE_PRIVATE);
//        //获取编辑器
//        SharedPreferences.Editor editor = sp.edit();
//        //数据存储
//        editor.putString(user, "123456");
//        //数据提交
//        editor.apply();

        etUserName = findViewById(R.id.etUserName);
        etPwd1 = findViewById(R.id.etPwd1);
        etPwd2 = findViewById(R.id.etPwd2);

        ImageView ivRegHidePwd1=findViewById(R.id.ivRegHidePwd1);
        StaticConfig.setPwdEditView(etPwd1,ivRegHidePwd1);

        ImageView ivRegHidePwd2=findViewById(R.id.ivRegHidePwd2);
        StaticConfig.setPwdEditView(etPwd2,ivRegHidePwd2);
//        //取出user_1
//        String userName = sp.getString(user, "默认值");
//        //显示到etUserName
//        etUserName.setText(userName);

        Button btnLogin = findViewById(R.id.btnLogin);
        //点击注册
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

//        ImageView ivBack = findViewById(R.id.ivBack);
//        //返回按钮的监听事件
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                finish();
//            }
//        });



    }

    private void register() {
        String userName = etUserName.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        String pwdKey= StaticConfig.PWD_HEADER +userName;
        String userNameKey=StaticConfig.USER_HEADER + userName;
        //用户名已存在:user_123456(key的格式)
        //从本地存储取出输入框里的用户名：
        String spUserName = SPUtils.getString(userNameKey);
        //如果输入框里的userName与本地存储取出的用户相同，说明存在该用户
        if (userName.equals(spUserName)) {
            Toast.makeText(this, "账号已存在", Toast.LENGTH_LONG).show();
            return;
        }

        String pwd1=etPwd1.getText().toString();
        String pwd2=etPwd2.getText().toString();
        if (TextUtils.isEmpty(pwd1)||!pwd1.equals(pwd2)){
            Toast.makeText(this, "两次输入密码不一致或者密码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }

        SPUtils.putString(userNameKey,userName);
        SPUtils.putString(pwdKey,pwd1);
        Toast.makeText(this, "注册成功！", Toast.LENGTH_LONG).show();
        finish();
    }
}