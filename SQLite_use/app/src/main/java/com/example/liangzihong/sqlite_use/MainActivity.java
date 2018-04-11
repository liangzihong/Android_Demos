package com.example.liangzihong.sqlite_use;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    private static final String TABLENAME="user";
    private static final String TAG = "Info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //用sql语句去搞
        //useSQLStatement();

        //用database的内置函数去搞
        useSQLFunction();
    }

    private void useSQLFunction(){
        SQLiteDatabase database=openOrCreateDatabase("test1.db",MODE_PRIVATE,null);
        database.execSQL("create table if not exists " + TABLENAME + " (_id integer primary key autoincrement, " +
                "name text not null, sex text not null, age integer not null)");


        //database.insert()插入功能
        ContentValues values=new ContentValues();
        values.put("name","Wongtsuiyu");
        values.put("age",35);
        values.put("sex","female");
        database.insert(TABLENAME,null,values);
        values.clear();  //清除

        values.put("name","Moon");
        values.put("age",28);
        values.put("sex","female");
        database.insert(TABLENAME,null,values);
        values.clear();  //清除

        values.put("name","Louis");
        values.put("age",35);
        values.put("sex","male");
        database.insert(TABLENAME,null,values);
        values.clear();  //清除

        //database.update()更新功能
        //将男的变成100岁
        values.put("age",100);
        database.update(TABLENAME,values,"sex='male' ",null);



        //database.query()  查询功能
        //查出所有性别为女的

        Cursor cursor=database.query(TABLENAME,null,"sex='female'",null,null,null,"age");
        if(cursor!=null){
            while (cursor.moveToNext()) {
                Log.i(TAG, "onCreate: " + cursor.getInt(cursor.getColumnIndex("_id")));
                Log.i(TAG, "onCreate: " + cursor.getString(cursor.getColumnIndex("name")));
                Log.i(TAG, "onCreate: " + cursor.getString(cursor.getColumnIndex("sex")));
                Log.i(TAG, "onCreate: " + cursor.getInt(cursor.getColumnIndex("age")));
            }
        }

    }


    private void useSQLStatement() {
        //每个app都有一个自己的数据库，直接声明创建即可。不麻烦
        //第一句是如果没有数据库，就创建一个数据库叫test1.db
        //如果已经有了这个数据库，就打开
        SQLiteDatabase database = openOrCreateDatabase("test1.db", MODE_PRIVATE, null);

        //如果没有这个表，就创建，如果有，就打开
        database.execSQL("create table if not exists " + TABLENAME + " (_id integer primary key autoincrement, " +
                "name text not null, sex text not null, age integer not null)");

        database.execSQL("insert into " + TABLENAME + "(name,sex,age) values ('Ben','male',20)");
        database.execSQL("insert into " + TABLENAME + "(name,sex,age) values ('nancy','female',36)");
        database.execSQL("insert into " + TABLENAME + "(name,sex,age) values ('ali','female',35)");

        Cursor cursor = database.rawQuery("select * from user where sex='female' ", null);
        while (cursor.moveToNext()) {
            Log.i(TAG, "onCreate: " + cursor.getInt(cursor.getColumnIndex("_id")));
            Log.i(TAG, "onCreate: " + cursor.getString(cursor.getColumnIndex("name")));
            Log.i(TAG, "onCreate: " + cursor.getString(cursor.getColumnIndex("sex")));
            Log.i(TAG, "onCreate: " + cursor.getInt(cursor.getColumnIndex("age")));
        }
        cursor.close();
        database.close();
    }


}

























