package com.example.datechoose;



import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {

    private Integer year;
    private Integer month;
    private Integer day;
    private DatePicker datePicker;
    private DatePicker.OnDateChangedListener mOnDateChangedListener= new DatePicker.OnDateChangedListener(){

        @Override
        public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
            MainActivity.this.year = i;
            MainActivity.this.month = i1;
            MainActivity.this.day = i2;
            show(year,month,day);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year,month,day,mOnDateChangedListener);
    }

    private void show(int year,int month,int day){
        String s = "您选择了"+year+"年"+(month+1)+"月"+day+"日";
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }
}
