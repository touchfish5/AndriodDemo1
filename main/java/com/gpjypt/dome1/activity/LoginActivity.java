package com.gpjypt.dome1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gpjypt.dome1.HomeActivity;
import com.gpjypt.dome1.R;
import com.gpjypt.dome1.utils.SPUtils;
import com.gpjypt.dome1.utils.StaticConfig;

public class LoginActivity extends AppCompatActivity {
    EditText etUserName, etPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化界面
        initView();
    }

    private void initView() {
        etUserName = findViewById(R.id.etUserName);
        etPwd = findViewById(R.id.etPwd);
        TextView register = findViewById(R.id.tvRegister);
        //设置监听事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册界面
                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mIntent);
            }
        });
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();

            }
        });

        findViewById(R.id.tvForgetPwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
            }
        });
        ImageView ivHidePwd = findViewById(R.id.ivHidePwd);
        StaticConfig.setPwdEditView(etPwd, ivHidePwd);

    }

    private void doLogin() {

        String inputUserName = etUserName.getText().toString();
        if (TextUtils.isEmpty(inputUserName)) {
            StaticConfig.toast("账号不能为空");
            return;
        }
        String inputPwd = etPwd.getText().toString();

        if (TextUtils.isEmpty(inputPwd)) {
            StaticConfig.toast("密码不能为空");
            return;
        }
        //取出本地对应的账号：key:user_+username
        String spUserName = SPUtils.getString(StaticConfig.USER_HEADER + inputUserName);
        //取出对应的密码
        String spPwd = SPUtils.getString(StaticConfig.PWD_HEADER + inputUserName);
        //输入的用户名与本地取的用户一致，说明已经注册
        if (inputUserName.equals(spUserName)) {


            if (inputPwd.equals(spPwd)) {


                StaticConfig.toast("登录成功！");
                //存储一个登录状态
                SPUtils.putBoolean(StaticConfig.IS_LOGIN_KEY,true);

                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            } else {
                StaticConfig.toast("密码错误！");
            }
        } else {
            StaticConfig.toast("账号不存在！");
        }


    }
}