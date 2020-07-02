package com.example.actionbartab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//设置ActionBar采用选项卡模式
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);//隐藏标题栏
        actionBar.addTab(actionBar.newTab().setText("测试1").setTabListener(new MyTabListener(this, Fragment1.class)));
        actionBar.addTab(actionBar.newTab().setText("测试2").setTabListener(new MyTabListener(this, Fragment2.class)));
        actionBar.addTab(actionBar.newTab().setText("测试3").setTabListener(new MyTabListener(this, Fragment3.class)));
        actionBar.addTab(actionBar.newTab().setText("测试4").setTabListener(new MyTabListener(this, Fragment4.class)));
    }
}
