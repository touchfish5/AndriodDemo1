package com.gpjypt.dome1;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gpjypt.dome1.fragment.GuoNeiFragment;
import com.gpjypt.dome1.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    GuoNeiFragment guoNeiFragment;
    GuoNeiFragment guoJiFragment;
    GuoNeiFragment yuLeFragment;
    GuoNeiFragment tiYuFragment;
    GuoNeiFragment woDeFragment;
    String tag_gn = "guonei";
    String tag_gj = "guoji";
    String tag_yl = "yule";
    String tag_ty = "tiyu";
    String tag_wd = "wode";
    Map<String, GuoNeiFragment> fragments;

    public static final String FRAGEMNT_NAME_KEY = "fragment_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        //添加fragment到FragmentManager中
        addFragment();
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        //设置RadioGroup选中改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtnGN:
                        showFragment(tag_gn);
                        break;
                    case R.id.rbtnGJ:
                        showFragment(tag_gj);
                        break;
                    case R.id.rbtnYL:
                        showFragment(tag_yl);
                        break;
                    case R.id.rbtnTY:
                        showFragment(tag_ty);
                        break;
                    case R.id.rbtnWD:
                        showFragment(tag_wd);
                        break;
                    default:
                        showFragment(tag_gn);
                        break;
                }

            }
        });
        //初始化状态：第一个按钮选中
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
        radioButton.setChecked(true);
        //默认显示国内fragment
        showFragment(tag_gn);
    }

    private void addFragment() {
        //创建5个对应的fragment:国内，国际，娱乐，体育，我的
        guoNeiFragment = new GuoNeiFragment();
        guoJiFragment = new GuoNeiFragment();
        yuLeFragment = new GuoNeiFragment();
        tiYuFragment = new GuoNeiFragment();
        woDeFragment = new GuoNeiFragment();
        //将fragment添加进map集合
        fragments = new HashMap<>();
        fragments.put(tag_gn, guoNeiFragment);
        fragments.put(tag_gj, guoJiFragment);
        fragments.put(tag_yl, yuLeFragment);
        fragments.put(tag_ty, tiYuFragment);
        fragments.put(tag_wd, woDeFragment);
        //获取FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = manager.beginTransaction();
        //遍历集合：将每一个fragment添加进FragmentManager
        for (String key : fragments.keySet()) {

            GuoNeiFragment fragment = fragments.get(key);
            //添加fragment
            transaction.add(R.id.frameLayout, fragment, key);
            //fragment参数传递：将key传递到Fragment页面
            Bundle bundle = new Bundle();
            bundle.putString(FRAGEMNT_NAME_KEY, key);
            fragment.setArguments(bundle);
            //添加之后先隐藏所有fragment
            transaction.hide(fragment);
        }
        //提交事务
        transaction.commit();
    }

    private void showFragment(String tag) {

        FragmentManager manager = getSupportFragmentManager();
        //根据tag查找fragment
        Fragment fragment = manager.findFragmentByTag(tag);
        FragmentTransaction transaction = manager.beginTransaction();
        //遍历集合并将不需要显示的fragment隐藏
        for (String key : fragments.keySet()) {
            Fragment mFragemnt = fragments.get(key);
            if (fragment == null && tag.equals(key)) {
                fragment = mFragemnt;
            } else {
                transaction.hide(mFragemnt);
            }
        }
        //显示fragment
        transaction.show(fragment);

        transaction.commit();
    }


}