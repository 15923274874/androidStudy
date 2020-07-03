package com.example.looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyThread extends  Thread {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0x999:
                    Log.i("test", "子线程1显示:"+String.valueOf(msg.obj));
                    break;
            }
        }
    };

    @Override
    public void run() {
        super.run();
        Looper.prepare();//初始化Looper对象
        My2Thread my2Thread = new My2Thread(mHandler);
        my2Thread.start();
        Looper.loop();//启动loop,在子线程中调用子线程，必须使用looper
    }
}
