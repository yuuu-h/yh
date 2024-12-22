package com.example.flowershop.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.flowershop.entity.Stuff;
import com.example.flowershop.utils.SqliteUtils;

import java.util.ArrayList;
import java.util.List;

public class DBStuff {

    public static boolean add(Stuff s) {
        ContentValues values = new ContentValues();
        Log.d("", "插入 ");
        values.put("name", s.getName());
        values.put("title", s.getTitle());
        values.put("kind", s.getKind());
        values.put("price", s.getPrice());
        long i = SqliteUtils.getInstance().getWritableDatabase().insert("stuff", null, values);
        if (i > 0) {
            Log.d("", "插入成功");
            return true;
        }
        Log.d("", "插入失败");
        return false;
    }

    public static void deleteAll() {
        List<Stuff> all = getAll();
        for (int i = 0; i < all.size(); i++) {
            String sqlStr = "id" + "=?";
            String[] contentValuesArray = new String[]{all.get(i).getId()};
            SqliteUtils.getInstance().getWritableDatabase().delete("stuff", sqlStr, contentValuesArray);
        }
    }

    public static List<Stuff> getAll() {
        List<Stuff> array = new ArrayList<>();
        Cursor cursor = SqliteUtils.getInstance().getReadableDatabase().query("stuff", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String kind = cursor.getString(cursor.getColumnIndex("kind"));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));

            Stuff u = new Stuff(String.valueOf(id), name, title, kind, price);
            array.add(u);

        }
        return array;
    }

    public static Stuff getById(int _id) {
        Stuff s = new Stuff();
        Cursor cursor = SqliteUtils.getInstance().getReadableDatabase().query("stuff", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String kind = cursor.getString(cursor.getColumnIndex("kind"));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));
            if (id == _id) {
                s = new Stuff(String.valueOf(id), name, title, kind, price);
                break;
            }
        }
        return s;
    }
}

