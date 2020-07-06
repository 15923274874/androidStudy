package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class BoundService extends Service {
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder{
        public BoundService getService(){//创建获取service方法
           return BoundService.this;
        }
    }

    //自定义方法
    public Integer getRandomNumber(){
        return new Random().nextInt(33);//生成随机数
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
