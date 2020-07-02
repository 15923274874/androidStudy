package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TimePicker mTimePicker;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimePicker = findViewById(R.id.picker);
        mTimePicker.setIs24HourView(true);
        mButton = findViewById(R.id.btn);
        mButton.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //设置闹钟
            Intent intent = new Intent(MainActivity.this,AlarmActivity.class);
            //获取显示闹钟的对象
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

            //获取闹钟对象
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            //获取日历对象
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,mTimePicker.getCurrentHour());//设置小时数
            calendar.set(Calendar.MINUTE,mTimePicker.getCurrentMinute());//设置分钟数
            calendar.set(Calendar.SECOND,0);//设置秒数

            //设置闹钟
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

            Toast.makeText(MainActivity.this, "闹钟设置成功", Toast.LENGTH_SHORT).show();
        }
    };
}
