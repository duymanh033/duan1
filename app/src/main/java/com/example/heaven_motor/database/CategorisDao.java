package com.example.heaven_motor.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.heaven_motor.model.Categoris;

import java.util.ArrayList;
import java.util.List;

public class CategorisDao {
    SQLiteDatabase db;
    public CategorisDao(Context context){
        SQL sqlite = new SQL(context);
        db = sqlite.getWritableDatabase();
    }

    public int insert(Categoris c){
        ContentValues values = new ContentValues();
        values.put("name",c.getName());
        long kq = db.insert("Categories",null,values);
        if (kq <= 0){
            return -1;
        }
        return 1;
    }
    public int Update(Categoris c){
        ContentValues values = new ContentValues();
        values.put("name",c.getName());
        long kq = db.update("Categories",values,"id=?",new String[]{String.valueOf(c.getId())});
        if (kq <= 0){
            return -1;
        }
        return 1;
    }
    public int delete(String id){
        return db.delete("Categories","id=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<Categoris> getData(String sql, String ...selectionArgs){
        List<Categoris> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Categoris cg = new Categoris();
            cg.setId(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            cg.setName(c.getString(c.getColumnIndex("name")));
            list.add(cg);
        }
        return list;
    }
    public Categoris getID(String id){
        String sql = "SELECT * FROM Categories WHERE id=?";
        List<Categoris> list = getData(sql,id);
        return list.get(0);
    }
    public List<Categoris> getAll(){
        String sql ="SELECT * FROM Categories";
        return getData(sql);
    }
    public List<String> getAllTenLoai(){
        List<String> tenLoaiXe =new ArrayList<>();
        Cursor c = db.rawQuery("SELECT name FROM Categories",null);
        while (c.moveToNext()){
            tenLoaiXe.add(c.getString(0));
        }
        return tenLoaiXe;
    }
}
