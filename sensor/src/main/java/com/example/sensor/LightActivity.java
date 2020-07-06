package com.example.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class LightActivity extends Activity implements SensorEventListener {

    private TextView mTextView;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_activity);
        mTextView = findViewById(R.id.light_text);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
       int sentType = sensorEvent.sensor.getType();
       StringBuilder stringBuilder = null;
       if(sentType == Sensor.TYPE_LIGHT){
           stringBuilder = new StringBuilder();
           stringBuilder.append("光的强度值："+values[0]);
           mTextView.setText(stringBuilder.toString());
       }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
