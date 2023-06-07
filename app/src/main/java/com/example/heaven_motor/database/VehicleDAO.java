package com.example.heaven_motor.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.heaven_motor.fragment.ToiFragment;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    SQLiteDatabase db;

    public VehicleDAO(Context context) {
        SQL sqLite = new SQL(context);
        db = sqLite.getWritableDatabase();
    }

    public int insert(Vehicle v) {
        ContentValues values = new ContentValues();
        values.put("id", v.getId());
        values.put("categorie_id", v.getCategorie_id());
        values.put("name", v.getName());
        values.put("trangThai",v.getTrangThai());
        values.put("BKS", v.getBKS());
        values.put("capacity", v.getCapacity());
        values.put("status", v.getStatus());
        values.put("brand", v.getBrand());
        values.put("price", v.getPrice());
        values.put("year", v.getYear());
        values.put("imager",v.getImg());

        long kq = db.insert("Vehicle", null, values);
        if (kq <= 0) {
            return -1;
        }
        return 1;
    }

    public int Update(Vehicle v) {
        ContentValues values = new ContentValues();
        values.put("id", v.getId());
        values.put("categorie_id", v.getCategorie_id());
        values.put("name", v.getName());
        values.put("trangThai",v.getTrangThai());
        values.put("BKS", v.getBKS());
        values.put("capacity", v.getCapacity());
        values.put("status", v.getStatus());
        values.put("brand", v.getBrand());
        values.put("price", v.getPrice());
        values.put("year", v.getYear());
        values.put("imager",v.getImg());

        long kq = db.update("Vehicle", values, "id=?", new String[]{String.valueOf(v.getId())});
        if (kq <= 0) {
            return -1;
        }
        return 1;
    }

    public int delete(String id) {
        return db.delete("Vehicle", "id=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<Vehicle> getData(String sql, String... selectionArgs) {
        List<Vehicle> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Vehicle v = new Vehicle();
            v.setId((c.getString(c.getColumnIndex("id"))));
            v.setCategorie_id(c.getInt(c.getColumnIndex("categorie_id")));
            v.setName(c.getString(c.getColumnIndex("name")));
            v.setTrangThai(c.getInt(c.getColumnIndex("trangThai")));
            v.setBKS(c.getString(c.getColumnIndex("BKS")));
            v.setCapacity(c.getInt(c.getColumnIndex("capacity")));
            v.setStatus(Integer.parseInt(c.getString(c.getColumnIndex("status"))));
            v.setBrand(c.getString(c.getColumnIndex("brand")));
            v.setPrice(c.getInt(c.getColumnIndex("price")));
            v.setYear(c.getInt(c.getColumnIndex("year")));
            v.setImg(c.getBlob(c.getColumnIndex("imager")));

            list.add(v);
        }
        return list;
    }

    public Vehicle getID(String id) {
        String sql = "SELECT * FROM Vehicle WHERE id=?";
        List<Vehicle> list = getData(sql, id);
        return list.get(0);
    }

    public List<Vehicle> getAll() {
        String sql = "SELECT * FROM Vehicle ";
        return getData(sql);
    }


    @SuppressLint("Range")
    public String  getLoaixe(){
        String sql = "SELECT Categories.name as name_categories FROM Categories JOIN Vehicle ON Vehicle.categorie_id = Categories.id ";
        List<String> list = new ArrayList<>();

        Cursor c = db.rawQuery(sql,null);
        while (c.moveToNext()){
            try {
                list.add(c.getString(c.getColumnIndex("name_categories")));
            }catch (Exception e){
                list.get(0);
            }
        }
        ToiFragment toiFragment = new ToiFragment();
        return list.get(0);
    }

    public List<Vehicle> getNgay(String tuNgay,String denNgay){
        String sql = "SELECT * FROM Orders JOIN Vehicle ON Vehicle.id = Orders.vehicle_id  WHERE start_time BETWEEN ? AND ?";
        return getData(sql,new String[]{tuNgay,denNgay});
    }

}
