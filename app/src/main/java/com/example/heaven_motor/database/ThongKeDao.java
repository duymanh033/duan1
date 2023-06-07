package com.example.heaven_motor.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    Context context;
    SQL sqlite;
    SQLiteDatabase db;

    public ThongKeDao(Context context) {
        this.context = context;
        sqlite = new SQL(context);
        db = sqlite.getWritableDatabase();
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT (end_time-start_time) as doanhThu FROM Orders Where doanhThu BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }
            catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
