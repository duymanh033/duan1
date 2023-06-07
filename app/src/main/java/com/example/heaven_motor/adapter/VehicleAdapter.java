package com.example.heaven_motor.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.fragment.ChiTietActivity;
import com.example.heaven_motor.fragment.QLyXe_Fragment;
import com.example.heaven_motor.model.Categoris;
import com.example.heaven_motor.model.Vehicle;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;

public class VehicleAdapter extends BaseAdapter {
    Context context;
    ArrayList<Vehicle> list;
    TextView tvTenXe, tvLoaiXe, tvHangXe, tvDungTich, tvGiaThue, tvChiTiet, tvXoaXe;
    ImageView imgXe;
    QLyXe_Fragment qLyXe_fragment;
    LayoutInflater inflater;
    EditText edMaXe, edTenXe, edBKS, edGiaThue, edTinhTrang;

    public VehicleAdapter(Context context, QLyXe_Fragment qLyXe_fragment, ArrayList<Vehicle> list) {
        this.context = context;
        this.qLyXe_fragment = qLyXe_fragment;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Vehicle obj = list.get(position);
        return obj;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.layout_item_danh_sachxe, null);
            Vehicle obj = list.get(position);
            if (obj != null) {
                CategorisDao categorisDao = new CategorisDao(context);
                Categoris categoris = categorisDao.getID(String.valueOf(obj.getCategorie_id()));
                tvTenXe = itemView.findViewById(R.id.tvTenXe);
                tvTenXe.setText("Tên xe : " + obj.getName());
                tvLoaiXe = itemView.findViewById(R.id.tvLoaiXe);
                tvLoaiXe.setText("Loại xe: " + categoris.getName());
                tvHangXe = itemView.findViewById(R.id.tvHangXe);
                tvHangXe.setText("Hãng xe: " + obj.getBrand());
                tvDungTich = itemView.findViewById(R.id.tvDungTich);
                tvDungTich.setText("Dung tích: " + obj.getCapacity() + " CC");
                tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
                tvGiaThue.setText("Giá thuê: " + obj.getPrice() + " VNĐ/Ngày");
                tvXoaXe = itemView.findViewById(R.id.tvXoaXe);
                imgXe = itemView.findViewById(R.id.imgXe);

                byte[] xeImg = obj.getImg();
                Bitmap bitmap = BitmapFactory.decodeByteArray(xeImg, 0, xeImg.length);
                imgXe.setImageBitmap(bitmap);
                tvChiTiet = itemView.findViewById(R.id.tvChiTiet);
                tvChiTiet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ChiTietActivity.class);
                        Categoris categoris = categorisDao.getID(String.valueOf(obj.getCategorie_id()));
                        intent.putExtra("ma", obj.getId());
                        intent.putExtra("loai", categoris.getName());
                        context.startActivity(intent);


                    }
                });
                tvXoaXe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        qLyXe_fragment.delete(obj.getId());
                    }
                });
            }
        }
        return itemView;
    }

    public int Validate() {
        int check = 1;
        if (edMaXe.getText().length() == 0 || edBKS.getText().length() == 0 || edGiaThue.getText().length() == 0 || edTinhTrang.getText().length() == 0 || edTenXe.getText().length() == 0) {
            check = -1;
        }
        return check;
    }

    private byte[] imgViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}
