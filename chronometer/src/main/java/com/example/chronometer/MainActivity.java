package com.example.chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.WindowManager;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Chronometer mChronometer;

    private Chronometer.OnChronometerTickListener mOnChronometerTickListener = new Chronometer.OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
             if(SystemClock.elapsedRealtime() - chronometer.getBase() > 20000){
                 chronometer.stop();
             }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //全局显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mChronometer = findViewById(R.id.chronometer);

        //SystemClock.elapsedRealtime() 获取系统当前时间
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setFormat("%s");
        mChronometer.start();
        mChronometer.setOnChronometerTickListener(mOnChronometerTickListener);
    }
}
