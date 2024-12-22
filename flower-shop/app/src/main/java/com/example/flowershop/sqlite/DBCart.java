package com.example.flowershop.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.flowershop.utils.SqliteUtils;

import java.util.ArrayList;

public class DBCart {

    public static boolean add(String id,String username){
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("username",username);
        long i= SqliteUtils.getInstance().getWritableDatabase().insert("cart",null,values);
        if(i>0){
            Log.d("","插入成功");
            return true;
        }
        return false;
    }
    public static boolean del(String id,String username){

        long i=SqliteUtils.getInstance().getWritableDatabase().delete("cart","id=? and username=?",new String[]{id,username});
        if(i>0){
            Log.d("","删除成功");
            return true;
        }
        return false;
    }
    public static ArrayList<String> getLikesTitle(String username){
        ArrayList<String> array=new ArrayList();

        Cursor cursor=SqliteUtils.getInstance().getReadableDatabase().query("cart",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String id=cursor.getString( cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name=cursor.getString( cursor.getColumnIndex("username"));
            if(name.equals(username)){
                array.add(id );
            }
        }
        return array;
    }
}
