package com.example.actionanddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * 拨打电话以及短信功能需要真机测试，模拟器不支持
 */
public class MainActivity extends AppCompatActivity {

    private Button phone;
    private Button message;
    private Button rtn;
    private Button toTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.btnPhone);
        message = findViewById(R.id.btnMessage);
        rtn = findViewById(R.id.btnRtn);
        toTest = findViewById(R.id.toTest);


        phone.setOnClickListener(mOnClickListener);
        message.setOnClickListener(mOnClickListener);
        rtn.setOnClickListener(mOnClickListener);
        toTest.setOnClickListener(mOnClickListener);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()){

                //拨打电话
                case R.id.btnPhone:
                    TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
                     boolean f = (tm != null && tm.getSimState()==TelephonyManager.SIM_STATE_READY);
                    Log.i(MainActivity.class.getName(),String.valueOf(f));
                    intent.setAction(intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:15923274874"));
                    startActivity(intent);
                    break;

                    //发送信息
                case R.id.btnMessage:
                    intent.setAction(intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:15923274874"));
                    intent.putExtra("sms_body","你好！");
                    startActivity(intent);
                    break;

                    //返回主界面
                case R.id.btnRtn:
                    intent.setAction(intent.ACTION_MAIN);
                    intent.addCategory(intent.CATEGORY_HOME);
                    startActivity(intent);
                    break;

                case R.id.toTest:
                     Log.i("test","点击按钮");
                    intent.setClass(MainActivity.this,TestActivity.class);
//                    intent = new Intent(MainActivity.this,TestActivity.class);
                    //离开跳转页面则销毁该页面
                    intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    break;
            }
        }
    };
}
