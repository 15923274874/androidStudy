package com.example.drawfiash;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private boolean flag = true;//播放状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.linerLayout);
        final AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    animationDrawable.start();
                    flag = false;
                }else {
                    animationDrawable.stop();
                    flag = true;
                }
            }
        });
    }
}
