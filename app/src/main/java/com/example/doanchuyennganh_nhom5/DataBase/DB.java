package com.example.doanchuyennganh_nhom5.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context,"DACN",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sanpham = "CREATE TABLE TAIKHOAN(ID TEXT)";
//        db.execSQL(sanpham);
    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }

    public void Query(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor Get(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
