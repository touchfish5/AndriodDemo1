package com.gpjypt.dome1.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gpjypt.dome1.R;
import com.gpjypt.dome1.base.BaseActivity;
import com.gpjypt.dome1.utils.SPUtils;
import com.gpjypt.dome1.utils.StaticConfig;

public class ForgetPwdActivity extends BaseActivity {
    EditText etUserName, etPwd1, etPwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        initView();
    }

    private void initView() {
        etUserName = findViewById(R.id.etUserName);
        etPwd1 = findViewById(R.id.etPwd1);
        etPwd2 = findViewById(R.id.etPwd2);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(getString(R.string.update_pwd));

        findViewById(R.id.btnUpdate).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updatePwd();
                    }
                }
        );


        ImageView ivHidePwd1=findViewById(R.id.ivHidePwd1);
        StaticConfig.setPwdEditView(etPwd1,ivHidePwd1);


        ImageView ivHidePwd2=findViewById(R.id.ivHidePwd2);
        StaticConfig.setPwdEditView(etPwd2,ivHidePwd2);

    }

    private void updatePwd() {
        String inputUserName = etUserName.getText().toString();
        String inputPwd1 = etPwd1.getText().toString();
        String inputPwd2 = etPwd2.getText().toString();

        if (TextUtils.isEmpty(inputUserName)) {
            StaticConfig.toast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(inputPwd1)) {
            StaticConfig.toast("密码不能为空");
            return;
        }
        if (!inputPwd1.equals(inputPwd2)) {
            StaticConfig.toast("两次密码不一致");
            return;
        }
        //用户对比
        String spUserName = SPUtils.getString(StaticConfig.USER_HEADER + inputUserName);
        if (inputUserName.equals(spUserName)) {
            SPUtils.putString(StaticConfig.PWD_HEADER + inputUserName, inputPwd1);
            StaticConfig.toast("修改成功");
            finish();
        } else {
            StaticConfig.toast("用户不存在");
        }

    }
}