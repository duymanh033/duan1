package com.example.heaven_motor.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.model.Categoris;
import com.example.heaven_motor.model.Vehicle;

import java.io.ByteArrayOutputStream;

public class ChiTietActivity extends AppCompatActivity {
    TextView tvmaXe, tvTenXe, tvLoaiXe, tvHangXe, tvDungTich, tvGiaThue, tvBKS, tvTrangThai, tvNam;
    ImageView img;
    Vehicle obj;
    VehicleDAO vehicleDAO;
    Button btnThue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        tvmaXe = findViewById(R.id.tvMaXe);
        tvTenXe = findViewById(R.id.tvTenXe);
        tvLoaiXe = findViewById(R.id.tvLoaiXe);
        tvHangXe = findViewById(R.id.tvHangXe);
        tvDungTich = findViewById(R.id.tvDungTich);
        tvGiaThue = findViewById(R.id.tvTienThue);
        tvBKS = findViewById(R.id.tvBKS);
        tvTrangThai = findViewById(R.id.tvTrangThai);
        tvNam = findViewById(R.id.tvNam);
        img = findViewById(R.id.imgXeChiTiet);
        Intent intent = getIntent();
        if (getIntent().hasExtra("ma")) {
            String ma = intent.getStringExtra("ma");

            vehicleDAO = new VehicleDAO(getApplicationContext());
            obj = vehicleDAO.getID(ma);
            tvmaXe.setText(obj.getId());
            tvTenXe.setText(obj.getName());
            tvLoaiXe.setText(intent.getStringExtra("loai"));
            tvHangXe.setText(obj.getBrand());
            tvGiaThue.setText(String.valueOf(obj.getPrice())+"VNĐ/Ngày");
            tvDungTich.setText(String.valueOf(obj.getCapacity())+" CC");
            tvTrangThai.setText(String.valueOf(obj.getStatus()+" %"));
            tvBKS.setText(obj.getBKS());
            tvNam.setText(String.valueOf(obj.getYear()));
            Bitmap bitmap = BitmapFactory.decodeByteArray(obj.getImg(), 0, obj.getImg().length);
            img.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }
}