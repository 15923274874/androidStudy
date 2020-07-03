package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button mShared;
    private Button inSharedWrite;
    private Button inSharedRead;
    private Button outSharedWrite;
    private Button outSharedRead;
    private Button sqlite1;
    private Button sqlite2;
    private DBopenHelper mDBopenHelper;
    private File mFilePath  = new File(Environment.getExternalStorageDirectory(),"/lds");
    private FileOutputStream mFileOutputStream;
    private String mText;
    private FileInputStream mFileInputStream;
    private Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShared = findViewById(R.id.shared);
        mShared.setOnClickListener(mOnClickListener);
        inSharedWrite = findViewById(R.id.inSharedWrite);
        inSharedWrite.setOnClickListener(mOnClickListener);
        inSharedRead = findViewById(R.id.inSharedRead);
        inSharedRead.setOnClickListener(mOnClickListener);
        outSharedWrite = findViewById(R.id.outSharedWrite);
        outSharedWrite.setOnClickListener(mOnClickListener);
        outSharedRead = findViewById(R.id.outSharedRead);
        outSharedRead.setOnClickListener(mOnClickListener);

        sqlite1 = findViewById(R.id.sqlite1);
        sqlite1.setOnClickListener(mOnClickListener);
        sqlite2 = findViewById(R.id.sqlite2);
        sqlite2.setOnClickListener(mOnClickListener);

//        初始化数据库
        mDBopenHelper = new DBopenHelper(MainActivity.this,"test",null,1);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.shared:
                    mIntent = new Intent(MainActivity.this,SharedActivity.class);
                    startActivity(mIntent);
                    break;
                case R.id.inSharedWrite:
                    FileOutputStream fileOutputStream = null;//文件输出流
                    String text  = "测试内部存储2";
                    //获得文件输出流
                    try {
                        fileOutputStream = openFileOutput("lds",MODE_PRIVATE);
                        fileOutputStream.write(text.getBytes());//保存信息
                        fileOutputStream.flush();//清除缓存
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(fileOutputStream != null){
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.inSharedRead:
                    try {
                        mFileInputStream = openFileInput("lds");
                        byte[] buffer = new byte[mFileInputStream.available()];
                        mFileInputStream.read(buffer);//读取数据
                        mText = new String(buffer);
                        Toast.makeText(MainActivity.this, "读取到的文件为"+mText, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                       if(mFileInputStream != null){
                           try {
                               mFileInputStream.close();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                    break;
                case R.id.outSharedWrite:
                    try {
                        if(!mFilePath.exists()){
                            mFilePath.mkdirs();
                        }
                        File file = new File(mFilePath,"/lds.txt");
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        mFileOutputStream = new FileOutputStream(file);
                        mFileOutputStream.write("测试外部存储".getBytes());
                        mFileOutputStream.flush();//清除缓存
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if (mFileOutputStream != null){
                            try {
                                mFileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.outSharedRead:
                    try {
                        if(!mFilePath.exists()){
                            mFilePath.mkdirs();
                        }
                        File file = new File(mFilePath,"/lds.txt");
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        mFileInputStream = new FileInputStream(file);
                        byte[] buffer = new byte[mFileInputStream.available()];
                        mFileInputStream.read(buffer);//读取数据
                        mText = new String(buffer);
                        Toast.makeText(MainActivity.this, "读取到的文件为"+mText, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(mFileInputStream != null){
                            try {
                                mFileInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case R.id.sqlite1:
                    insertData(mDBopenHelper.getReadableDatabase(),"lds");
                    Toast.makeText(MainActivity.this, "数据库创建及数据插入成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sqlite2:
                    //查询
                    Cursor cursor = mDBopenHelper.getReadableDatabase().query("info",null,null,null,null,null,null);
                    List<Map<String,String>> list = new ArrayList<>();
                    while (cursor.moveToNext()){
                        Map<String,String> map = new HashMap<>();
                        map.put("text",cursor.getString(1));//获取第二列数据
                        list.add(map);
                    }
                    if(list == null || list.size() == 0){
                        Toast.makeText(MainActivity.this, "数据库无数据", Toast.LENGTH_SHORT).show();
                    }else{
                        String s = "";
                        for (Map<String,String> map : list){
                            s += map.get("text") +",";
                        }
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }


        }
    };

    @Override
    protected void onDestroy() {
        if (mDBopenHelper != null){
            mDBopenHelper.close();
        }
        super.onDestroy();
    }
    private void insertData(SQLiteDatabase sqLiteDatabase,String text){
        ContentValues values = new ContentValues();
        values.put("text",text);
        sqLiteDatabase.insert("info",null,values);//执行插入操作
    }
}
