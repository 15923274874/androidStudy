package com.example.timechoose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TimePicker mTimePicker;

    private TimePicker.OnTimeChangedListener mOnTimeChangedListener= new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker timePicker, int i, int i1) {
            String str = i+"时"+i1+"分";
            Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimePicker = findViewById(R.id.time_picker);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(mOnTimeChangedListener);
    }
}
