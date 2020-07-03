package com.example.messagetoast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFY_ID = 8888;
    private Button mToast;
    private Button alertDialog1;
    private Button alertDialog2;
    private Button alertDialog3;
    private Button alertDialog4;
    private Button notification;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        private AlertDialog.Builder builder;
        private AlertDialog alertDialog;

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.toast:
                    Toast.makeText(MainActivity.this, "测试Toast", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.alertDialog1:

                    //带按钮的对话框
                    alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setIcon(R.drawable.my);//设置图片
                    alertDialog.setTitle("测试1");
                    alertDialog.setMessage("这是一个测试对话框");
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "单击了取消按钮", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.show();
                    break;
                case R.id.alertDialog2:
                    //带列表的对话框
                    final String[] items = new String[]{"列表1","列表2","列表3","列表4"};
                    builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setIcon(R.drawable.my);
                    builder.setTitle("测试2");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, items[i], Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.create().show();
                    break;
                case R.id.alertDialog3:
                    //带单选按钮的对话框
                    final String[] items2 = new String[]{"列表1","列表2","列表3","列表4"};
                    builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setIcon(R.drawable.my);
                    builder.setTitle("测试3");
                    builder.setSingleChoiceItems(items2, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, items2[i], Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setPositiveButton("确定",null);
                    builder.create().show();
                    break;
                case R.id.alertDialog4:
                   //带单选按钮的对话框
                    final boolean[] checkItems = new boolean[]{true,false,false,true,false};
                    final String[] items3 = new String[]{"列表1","列表2","列表3","列表4","列表5"};
                    builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setIcon(R.drawable.my);
                    builder.setTitle("测试4");
                    builder.setMultiChoiceItems(items3, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            checkItems[i] = b;
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            String s = "";
                            for (int i = 0; i < items3.length; i++){
                                if(checkItems[i]){
                                    s += items3[i]+"、";
                                }
                            }
                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.create().show();
                    break;
                case R.id.notification:
                    break;
            }
        }
    };
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);












        mToast = findViewById(R.id.toast);
        alertDialog1 = findViewById(R.id.alertDialog1);
        alertDialog2 = findViewById(R.id.alertDialog2);
        alertDialog3 = findViewById(R.id.alertDialog3);
        alertDialog4 = findViewById(R.id.alertDialog4);
        notification = findViewById(R.id.notification);
        mToast.setOnClickListener(mOnClickListener);
        alertDialog1.setOnClickListener(mOnClickListener);
        alertDialog2.setOnClickListener(mOnClickListener);
        alertDialog3.setOnClickListener(mOnClickListener);
        alertDialog4.setOnClickListener(mOnClickListener);
        notification.setOnClickListener(mOnClickListener);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        Log.i("test", String.valueOf(manager.areNotificationsEnabled()));

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this,"chat");
        notification.setAutoCancel(true);//通知打开后自动消失
        notification.setSmallIcon(R.drawable.my);//设置通知图片
        notification.setContentTitle("测试通知栏");
        notification.setContentText("查看详情");
        notification.setWhen(System.currentTimeMillis());//设置发送时间
        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);//设置默认声音和震动
        //创建一个详细Intent
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
        notification.setContentIntent(pendingIntent);//设置通知栏跳转
        notificationManager.notify(NOTIFY_ID,notification.build());
    }
}
