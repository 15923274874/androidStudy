package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;
            switch (view.getId()){
                case R.id.btn1:
                    fragment = new ChatFragMent();
                    break;
                case R.id.btn2:
                    fragment = new Chat2FragMent();
                    break;
                case R.id.btn3:
                    fragment = new Chat3FragMent();
                    break;
                case R.id.btn4:
                    fragment = new Chat4FragMent();
                    break;
            }
            fragmentTransaction.replace(R.id.fragment,fragment);
            fragmentTransaction.commit();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button1.setOnClickListener(mOnClickListener);
        button2.setOnClickListener(mOnClickListener);
        button3.setOnClickListener(mOnClickListener);
        button4.setOnClickListener(mOnClickListener);
    }
}
