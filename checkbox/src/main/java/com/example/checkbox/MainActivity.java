package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CheckBox> mCheckBoxList = new ArrayList<CheckBox>();

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_login:
                    String checkedString = "";
                    for (CheckBox checkBox : mCheckBoxList){
                        if (checkBox.isChecked()){
                            checkedString += checkBox.getText().toString();
                        }
                    }
                    Toast.makeText(MainActivity.this,checkedString,Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = findViewById(R.id.btn_login);
        btnOk.setOnClickListener(mOnClickListener);
        initCheckBoxList();
    }

    private void initCheckBoxList(){
        mCheckBoxList.add((CheckBox)findViewById(R.id.checkbox_01));
        mCheckBoxList.add((CheckBox)findViewById(R.id.checkbox_02));
        mCheckBoxList.add((CheckBox)findViewById(R.id.checkbox_03));
    }
}
