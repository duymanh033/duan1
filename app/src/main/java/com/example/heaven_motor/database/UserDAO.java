package com.example.heaven_motor.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.heaven_motor.model.Users;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    SQL sqLite;
    SQLiteDatabase db;
    Context context;
    public UserDAO(Context context){
        this.context = context;
        sqLite = new SQL(context);
        db = sqLite.getWritableDatabase();
    }
    public ArrayList<Users> getAll_rcv() {
        ArrayList<Users> ds = new ArrayList<>();
        SQLiteDatabase db = sqLite.getReadableDatabase();
        String sql = "SELECT * FROM Users";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false) {

            String id = cs.getString(0);
            String name = cs.getString(1);
            String passwork = cs.getString(2);
            String phone = cs.getString(3);
            int date = cs.getInt(4);
            String address = cs.getString(5);
            String cccd = cs.getString(6);
//            byte[] img = cs.getBlob(7);
//            String phanHoi = cs.getString(8);
            Users us = new Users();
            ds.add(us);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return ds;
    }
    public int insert(Users u){
        ContentValues values = new ContentValues();
        values.put("id",u.getId());
        values.put("name",u.getName());
        values.put("date",u.getDate());
        values.put("phone",u.getPhone());
        values.put("address",u.getAddress());
        values.put("cccd",u.getCCCD());
        values.put("img", u.getImg());
        values.put("passwork",u.getPasswork());
        values.put("phanHoi",u.getPhanhoi());
        long kq = db.insert("Users",null,values);
        if (kq <= 0){
            return -1;
        }
        return 1;
    }
    public  int update(Users u){
        ContentValues values = new ContentValues();
        values.put("id",u.getId());
        values.put("name",u.getName());
        values.put("date",u.getDate());
        values.put("phone",u.getPhone());
        values.put("address",u.getAddress());
        values.put("cccd",u.getCCCD());
        values.put("img",u.getImg());
        values.put("passwork",u.getPasswork());
        values.put("phanHoi",u.getPhanhoi());
        long kq = db.update("Users",values,"id=?",new String[]{String.valueOf(u.getId())});
        if (kq <= 0){
            return -1;
        }
        return 1;
    }
    public boolean delete_(String id) {
        SQLiteDatabase db = sqLite.getWritableDatabase();
        int row = db.delete("Users", "id=?", new String[]{id + ""});
        return (row > 0);
    }


    public int updatePass(Users obj) {
        ContentValues values = new ContentValues();
        values.put("name", obj.getName());
        values.put("passwork", obj.getPasswork());
        return db.update("Users", values, "id=?", new String[]{obj.getId()});

    }

    public int delete(String id){
        return db.delete("Users","id=?",new String[]{id});
    }


    @SuppressLint("Range")
    public List<Users> getData(String sql, String ...selectionArgs){
        List<Users> list =  new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Users u = new Users();
            u.setId(c.getString(c.getColumnIndex("id")));
            u.setName(c.getString(c.getColumnIndex("name")));
            u.setDate(c.getInt(c.getColumnIndex("date")));
            u.setPhone(c.getString(c.getColumnIndex("phone")));
            u.setAddress(c.getString(c.getColumnIndex("address")));
            u.setCCCD(c.getString(c.getColumnIndex("cccd")));
            u.setImg(c.getBlob(c.getColumnIndex("img")));
            u.setPasswork(c.getString(c.getColumnIndex("passwork")));
            u.setPhanhoi(c.getString(c.getColumnIndex("phanHoi")));
            list.add(u);
        }
        return list;
    }
    public Users getID(String id){
        String sql = "SELECT * FROM Users WHERE id=?";
        List<Users> list = getData(sql,id);
        return list.get(0);
    }
    public List<Users> getAll(){
        String sql ="SELECT * FROM Users";
        return getData(sql);
    }
    public int checkLogin(String strUser, String strPass){
        List<Users> ls = getAll();
        for (Users u:ls) {
            if(u.getId().equals(strUser) && u.getPasswork().equals(strPass)){
                return 1;
            }
        }return -1;
    }
    public  int checkTKdau(){
        List<Users> ls = getAll();
        if (ls.size() <= 0){
            return 1;
        }
        return -1;
    }
}
