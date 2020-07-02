package com.example.actionbartab;


import android.app.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class MyTabListener implements ActionBar.TabListener {
    private final Activity activity;
    private final Class aClass;
    private Fragment fragment;

    public MyTabListener(Activity activity, Class aClass) {
        this.activity = activity;
        this.aClass = aClass;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //选择时
        if(fragment == null){
            fragment = Fragment.instantiate(activity,aClass.getName());
            fragmentTransaction.add(android.R.id.content,fragment,null);
        }
        fragmentTransaction.attach(fragment);
    }

    //退出选择时
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if(fragment != null){
            fragmentTransaction.detach(fragment);
        }

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
