package com.example.sensor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class AddActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Vibrator mVibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensortType = sensorEvent.sensor.getType();
        if(sensortType == Sensor.TYPE_ACCELEROMETER){
            float[] value = sensorEvent.values;
            if(value[0] > 15 || value[1] > 15 || value[2] > 20){
                Toast.makeText(this, "摇一摇", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(this);//创建对话框
                alert.setView(R.layout.packet);
                alert.show();
                mVibrator.vibrate(500);//设置振动器频率
                mSensorManager.unregisterListener(this);//取消注册监听器
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
