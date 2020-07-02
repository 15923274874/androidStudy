package com.example.xuanxiangka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        layoutInflater.inflate(R.layout.tab1,tabHost.getTabContentView());
        layoutInflater.inflate(R.layout.tab2,tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("ta1").setIndicator("选项卡1").setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("ta2").setIndicator("选项卡2").setContent(R.id.tab2));
    }
}
