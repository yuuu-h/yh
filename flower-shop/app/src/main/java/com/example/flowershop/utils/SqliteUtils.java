package com.example.flowershop.utils;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteUtils extends SQLiteOpenHelper {

    public SqliteUtils() {
        super(AppUtils.getApplication(), "flower_shop.db", null, 1);
    }


    /**
     * 创建并获取单例
     */
    public static SqliteUtils getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(name text primary key,psw text not null,address text not null)");
        db.execSQL("create table stuff(id INTEGER primary key AUTOINCREMENT,name text not null,title text not null,kind text not null,price text not null)");
        db.execSQL("create table record(username text not null,id text not null,name text not null,price text not null,address text not null)");
        db.execSQL("create table cart(id text not null,username text not null,PRIMARY KEY(id,username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //删除表
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS stuff");
        db.execSQL("DROP TABLE IF EXISTS record");
        db.execSQL("DROP TABLE IF EXISTS cart");
        //重新创建表
        onCreate(db);
    }

    private static final class InstanceHolder {
        /**
         * 单例
         */
        static final SqliteUtils instance = new SqliteUtils();
    }

}
