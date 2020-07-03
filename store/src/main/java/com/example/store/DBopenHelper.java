package com.example.store;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBopenHelper extends SQLiteOpenHelper {
    String table_sql = "create table info(id integer primary key autoincrement,text varchar(20))";

    public DBopenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL(table_sql);//创建数据表
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("test","##版本更新"+i+"---->"+i1);
    }
}
