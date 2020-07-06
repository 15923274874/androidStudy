package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mStartBtn;
    private Button mStopBtn;
    private Button mStartBoundBtn;
    private Button mStopBoundBtn;
    private Intent mIntent;
    private BoundService mBoundService;
    private ServiceConnection mServiceConnection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //连接
            mBoundService = ((BoundService.MyBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
           //断开连接
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(MainActivity.this,MyService.class);
        mStartBtn = findViewById(R.id.start);
        mStopBtn = findViewById(R.id.stop);
        mStartBoundBtn = findViewById(R.id.startBound);
        mStopBoundBtn = findViewById(R.id.stopBound);
        mStartBtn.setOnClickListener(mOnClickListener);
        mStopBtn.setOnClickListener(mOnClickListener);
        mStartBoundBtn.setOnClickListener(mOnClickListener);
        mStopBoundBtn.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           switch (view.getId()){
               case R.id.start:
                   startService(mIntent);//启动service
                   break;
               case R.id.stop:
                   Log.i("test","停止service");
                   stopService(mIntent);//停止service
                   break;
               case R.id.startBound:
                    Integer number = mBoundService.getRandomNumber();
                    Log.i("test","点击的BoundService");
                   Toast.makeText(mBoundService, "获取到的随机数为"+number, Toast.LENGTH_SHORT).show();
                   break;
               case R.id.stopBound:
                   Log.i("test","停止service");
                   stopService(mIntent);//停止service
                   break;
           }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,BoundService.class);
        bindService(intent,mServiceConnection,BIND_AUTO_CREATE);//自动绑定
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mServiceConnection);
    }
}
