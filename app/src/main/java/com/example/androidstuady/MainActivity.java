package com.example.androidstuady;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private MyView mMyView;

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mMyView.setDrowX(motionEvent.getX());
            mMyView.setDrowY(motionEvent.getY());
            mMyView.invalidate();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = findViewById(R.id.myLayOut);
        mMyView = new MyView(this);
        mMyView.setOnTouchListener(mOnTouchListener);
        relativeLayout.addView(mMyView);

    }
}
