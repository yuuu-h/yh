package com.example.flowershop.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.flowershop.entity.Record;
import com.example.flowershop.utils.SqliteUtils;

import java.util.ArrayList;
import java.util.List;

public class DBRecord {

    public static boolean add(Record s){
        ContentValues values=new ContentValues();
        values.put("username",s.getUsername());
        values.put("id",s.getId());
        values.put("name",s.getName());
        values.put("price",s.getPrice());
        values.put("address",s.getAddress());
        long i= SqliteUtils.getInstance().getWritableDatabase().insert("record",null,values);
        if(i>0){
            Log.d("","插入成功");
            return true;
        }
        Log.d("","插入失败");
        return false;
    }

    public static List<Record> getAll(String user){
        List<Record> array=new ArrayList<>();
        Cursor cursor=SqliteUtils.getInstance().getReadableDatabase().query("record",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String username=cursor.getString( cursor.getColumnIndex("username"));
            @SuppressLint("Range") String id=cursor.getString( cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name=cursor.getString( cursor.getColumnIndex("name"));
            @SuppressLint("Range") String address=cursor.getString( cursor.getColumnIndex("address"));
            @SuppressLint("Range") String price=cursor.getString( cursor.getColumnIndex("price"));
            if(user.equals(username)){
                Record u=new Record(username,id,name ,price,address);
                array.add(u);
            }
        }
        return array;
    }
}

