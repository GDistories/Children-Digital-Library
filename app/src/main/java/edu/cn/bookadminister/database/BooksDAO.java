package edu.cn.bookadminister.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.cn.bookadminister.bean.Book;
import edu.cn.bookadminister.database.MyDBHelper;

public class BooksDAO {
    private Context context;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public BooksDAO(Context context) {
        this.context = context;
    }

    //打开数据库
    public void open() throws SQLiteException {
        dbHelper = new MyDBHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //关闭数据库
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }


    //添加借书信息
    public long addBooks(Book o) {
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入键值对
        values.put("studentid", o.studentid);
        values.put("studentname", o.studentname);
        values.put("majoy", o.majoy);
        values.put("booknum", o.booknum);
        values.put("username",o.userName);

        // 调用insert()方法将数据插入到数据库当中
        return db.insert("tb_Books", null, values);
    }

    //删除指定借书信息
    public int deletBooks(Book o,String userName) {
        return db.delete("tb_Books", "studentid=? and username=?", new String[]{String.valueOf(o.studentid),userName});
    }

    //修改指定借书信息
    public int updateBooks(Book o,String userName) {
        ContentValues value = new ContentValues();
        value.put("studentname", o.studentname);
        value.put("majoy", o.majoy);
        value.put("booknum", o.booknum);
        return db.update("tb_Books", value, "studentid=? and username=?", new String[]{String.valueOf(o.studentid),userName});
    }

    //根据学生学号查找订单
    public Book getBooks(String studentid,String username) {
        //查询学生
        Cursor cursor = db.query("tb_Books", null, "studentid=? and username=?", new String[]{studentid,username}, null, null, null);
       Book o = new Book();
        while (cursor.moveToNext()) {
            o.studentid = cursor.getString(cursor.getColumnIndex("studentid"));
            o.studentname = cursor.getString(cursor.getColumnIndex("studentname"));
            o.majoy = cursor.getString(cursor.getColumnIndex("majoy"));
            o.booknum = cursor.getString(cursor.getColumnIndex("booknum"));
            o.userName = cursor.getString(cursor.getColumnIndex("username"));
        }
        return o;
    }

    //查找所有借书信息
    public ArrayList<Map<String, Object>> getAllbooks(String userName) {
        ArrayList<Map<String, Object>> listBooks = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_Books", null, "username=?", new String[]{userName}, null, null,null);

        int resultCounts = cursor.getCount();  //记录数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("studentid", cursor.getString(cursor.getColumnIndex("studentid")));
                map.put("studentname", cursor.getString(cursor.getColumnIndex("studentname")));
                map.put("majoy", cursor.getString(cursor.getColumnIndex("majoy")));
                map.put("booknum", cursor.getString(cursor.getColumnIndex("booknum")));

                listBooks.add(map);
            }
            return listBooks;
        }
    }
}