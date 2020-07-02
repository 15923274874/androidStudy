package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public static final int MECCAGE_LODING = 99999;
    public static final int MECCAGE_SUCCESS = 88888;
    private ProgressBar mProgressBar;
    private int mProgress = 0;
    private Handler mMyHandler;
    private  Thread loadThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progressBar);
        mMyHandler = new MyHandler(this);
        load();
    }

    private void load(){
        loadThread = new Thread(){
            @Override
            public void run() {
              while (true){
                  mProgress = doWork();
                  Message message = mMyHandler.obtainMessage();
                  if(mProgress <= 100){
                      message.arg1 = 0;
                      message.arg2 =1;
                      message.what = MECCAGE_LODING;
                      message.obj = mProgress;
                      mMyHandler.sendMessage(message);
                  }else {
                      message.arg1 = 0;
                      message.arg2 =1;
                      message.what = MECCAGE_SUCCESS;
                      message.obj = mProgress;
                      mMyHandler.sendMessage(message);
                      break;
                  }
              }
            }
        };
        loadThread.start();
    }

    private int doWork(){
        mProgress += Math.random()*10;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mProgress;
    }

}
