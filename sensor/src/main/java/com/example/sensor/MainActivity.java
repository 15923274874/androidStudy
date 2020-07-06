package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mMagnetic;
    private Button addBtn;
    private Button light;
    private Button level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMagnetic = findViewById(R.id.magnetic);
        addBtn = findViewById(R.id.add_btn);
        light = findViewById(R.id.light);
        level = findViewById(R.id.level);

        mMagnetic.setOnClickListener(mOnClickListener);
        addBtn.setOnClickListener(mOnClickListener);
        light.setOnClickListener(mOnClickListener);
        level.setOnClickListener(mOnClickListener);
    }
    View.OnClickListener mOnClickListener = new View.OnClickListener() {

        private Intent mIntent;

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.magnetic:
                    mIntent = new Intent(MainActivity.this,MagneticActivity.class);
                    startActivity(mIntent);
                    break;
                case R.id.add_btn:
                    mIntent = new Intent(MainActivity.this,AddActivity.class);
                    startActivity(mIntent);
                    break;
                case R.id.light:
                    mIntent = new Intent(MainActivity.this,LightActivity.class);
                    startActivity(mIntent);
                    break;
                case R.id.level:
                    mIntent = new Intent(MainActivity.this,LevelActivity.class);
                    startActivity(mIntent);
                    break;
            }
        }
    };
}
