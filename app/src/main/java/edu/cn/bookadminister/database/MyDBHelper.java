package edu.cn.bookadminister.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    //定义数据库名和版本号
    public static final String name = "book.db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_USERDATA = "create table tb_Books(studentid char(10)primary key,studentname varchar(20),majoy varchar(20),booknum varchar(20),username varchar(20))";
    //构造函数
    public MyDBHelper(Context context) {
        super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}