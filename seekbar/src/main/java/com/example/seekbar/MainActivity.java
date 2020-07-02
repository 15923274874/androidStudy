package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {


    private SeekBar mSeekBar;
    private ImageView mImageView;

    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            //进度改变时
            mImageView.setAlpha(i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //开始触摸
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //停止触摸
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSeekBar = findViewById(R.id.seekBar);
        mImageView = findViewById(R.id.imageView);
        mSeekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    }
}
