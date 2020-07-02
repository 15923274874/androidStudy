package com.example.progressbar;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class MyHandler extends Handler {

    private final WeakReference<MainActivity> mainActivityWeakReference;
    private ProgressBar mProgressBar;
    private MainActivity mMainActivity;

    public MyHandler(MainActivity mainActivity) {

        //弱引用，优化内存
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
    }


    @Override
    public void handleMessage(@NonNull Message msg) {
        if (msg.what == MainActivity.MECCAGE_LODING){
            mMainActivity = mainActivityWeakReference.get();
            int progress = (int) msg.obj;
            mProgressBar = mMainActivity.findViewById(R.id.progressBar);
            mProgressBar.setProgress(progress);
        }else {
            Toast.makeText(mMainActivity, "加载完成", Toast.LENGTH_SHORT).show();
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
