package com.example.provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String colums = ContactsContract.Contacts.DISPLAY_NAME;//获得通讯录姓名
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String peopleInfo = getQueryData().toString();
        Toast.makeText(this, peopleInfo, Toast.LENGTH_SHORT).show();
    }

    private CharSequence getQueryData(){
        StringBuilder stringBuilder = new StringBuilder();//保存获取的联系人
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        int displayNameIndex = cursor.getColumnIndex(colums);//获取姓名记录结果值
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            String displayName = cursor.getString(displayNameIndex);
            stringBuilder.append(displayName+"\n");
        }
        cursor.close();
        return stringBuilder.toString();
    }
}
