package com.example.looper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class My2Thread extends Thread {
    private Handler mHandler;
    public My2Thread(Handler handler) {
        this.mHandler = handler;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0 ; i < 100; i++){
            Log.i("test","子线程2显示--->"+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 10 == 0){
                Message message = mHandler.obtainMessage();
                message.what = 0x999;
                message.obj = "线程2执行到了："+i;
                mHandler.sendMessage(message);
            }
        }
    }
}
