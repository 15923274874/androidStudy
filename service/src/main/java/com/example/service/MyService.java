package com.example.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //每次启动调用
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                Log.i("test",String.valueOf(isRunning()));
                while (isRunning()){
                    Log.i("test",String.valueOf(i));
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //判断services释放正在运行
    public boolean isRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfolist = activityManager.getRunningServices(Integer.MAX_VALUE);

        for (int i = 0; i < runningServiceInfolist.size(); i++) {
            String mName = runningServiceInfolist.get(i).service.getClassName().toString();
            if (mName.equals("com.example.service.MyService")) {
                return true;
            }
        }
        return false;
    }
}
