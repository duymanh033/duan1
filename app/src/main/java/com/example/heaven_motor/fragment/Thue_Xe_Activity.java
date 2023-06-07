package com.example.heaven_motor.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heaven_motor.MainActivity;
import com.example.heaven_motor.R;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.model.Categoris;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Vehicle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class Thue_Xe_Activity extends AppCompatActivity  {
    Intent intent;
    TextView tvmaXe, tvTenXe, tvLoaiXe, tvHangXe, tvDungTich, tvGiaThue, tvBKS, tvTrangThai, tvNam, tvThongBao;
    ImageView imageView,img;

    Button btnThue;

    EditText edtuNgay,edDenNgay;
    Button btnThueXe;
    TextView tvGia;
    List<Vehicle> list;
    VehicleDAO dao1;

    Vehicle v;
    Orders o;
    OrdersDao ordersDao;
    Context context;

    int tinhtien;
    Calendar calendarOne,calendarTwo;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thue_xe);


        ordersDao = new OrdersDao(this);
        anhXa();
        intent = getIntent();
        dao1 = new VehicleDAO(getApplicationContext());
        v = dao1.getID(intent.getStringExtra("id"));

        insert();
        context = this;
        btnThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOrder();
            }
        });
    }


    @SuppressLint("MissingInflatedId")
    public void dialogOrder(){

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thue_xe,null);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(view);

        tvGia = dialog.findViewById(R.id.dialog_thue_xe_tvgia);
        edDenNgay = dialog.findViewById(R.id.dialog_thue_xe_edDenngay);
        edtuNgay = dialog.findViewById(R.id.dialog_thue_xe_edTungay);
        btnThueXe = dialog.findViewById(R.id.dialog_thue_xe_btnThue);
        img = dialog.findViewById(R.id.dialog_thue_xe_img);
        tvThongBao = dialog.findViewById(R.id.dialog_thue_xe_tvThongBao);
        btnThueXe.setEnabled(false);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // set vị trí dialog
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        edtuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay1();
            }
        });
        edDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay2();

            }
        });
        tvGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhNgay();
            }
        });
        edDenNgay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String date1 = edtuNgay.getText().toString();
                String date2 = edDenNgay.getText().toString();
                List<Orders> list1 = ordersDao.getAll();
                Toast.makeText(context, ""+list1.size(), Toast.LENGTH_SHORT).show();
                btnThueXe.setEnabled(true);
                for (int i = 0; i <list1.size(); i ++){

                    String id = list1.get(i).getVehicle_id();
                    int stt = dao1.getID(id).getTrangThai();
                    if (id.equals(v.getId())){
                        if(stt == 0){
                            btnThueXe.setEnabled(true);
                        }else {
                            if (list1.get(i).getStart_time().equals(date1) || list1.get(i).getEnd_time().equals(date2)){
                                tvThongBao.setText("Thông báo: Từ ngày " + date1 + " Đến ngày " + date2 +" đã có người thuê \n Vui lòng chọn ngày khác!" );
                                btnThueXe.setEnabled(false);
                            }
                        }
                    }

                }
                tinhNgay();
            }
        });
        btnThueXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInsert();
                dialog.dismiss();
            }


        });
        dialog.show();
    }


    public void anhXa(){
        tvmaXe = findViewById(R.id.tvMaXe);
        tvTenXe = findViewById(R.id.tvTenXe);
        tvLoaiXe = findViewById(R.id.tvLoaiXe);
        tvHangXe = findViewById(R.id.tvHangXe);
        tvDungTich = findViewById(R.id.tvDungTich);
        tvGiaThue = findViewById(R.id.tvTienThue);
        tvBKS = findViewById(R.id.tvBKS);
        tvTrangThai = findViewById(R.id.tvTrangThai);
        tvNam = findViewById(R.id.tvNam);
        imageView = findViewById(R.id.imgXeChiTiet);

        btnThue = findViewById(R.id.btnThue);
    }
    public void insert(){
        if (getIntent().hasExtra("id")){

            CategorisDao dao = new CategorisDao(this);
            Categoris c = dao.getID(String.valueOf(v.getCategorie_id()));

            tvmaXe.setText(v.getId());
            tvTenXe.setText(v.getName());
            tvLoaiXe.setText(c.getName());
            tvHangXe.setText(v.getBrand());
            tvGiaThue.setText(String.valueOf(v.getPrice())+" Vnd");
            tvDungTich.setText(String.valueOf(v.getCapacity())+"cc");
            tvTrangThai.setText(String.valueOf(v.getStatus()));
            tvBKS.setText(v.getBKS());
            tvNam.setText(String.valueOf(v.getYear()));

            Bitmap bitmap = BitmapFactory.decodeByteArray(v.getImg(), 0, v.getImg().length);
            imageView.setImageBitmap(bitmap);

        }else {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    private void chonNgay1(){
        calendarOne = Calendar.getInstance();
        int mday,mMonth,mYear;
        mday = calendarOne.get(Calendar.DAY_OF_MONTH);
        mMonth = calendarOne.get(Calendar.MONDAY);
        mYear = calendarOne.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendarOne.set(year,month,dayOfMonth);
                        edtuNgay.setText(simpleDateFormat.format(calendarOne.getTime()));
                    }
                },mYear,mMonth,mday);
        datePickerDialog.show();
    }
    private void chonNgay2(){
        calendarTwo = Calendar.getInstance();
        int mday,mMonth,mYear;
        mday = calendarTwo.get(Calendar.DAY_OF_MONTH);
        mMonth = calendarTwo.get(Calendar.MONDAY);
        mYear = calendarTwo.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendarTwo.set(year,month,dayOfMonth);
                        edDenNgay.setText(simpleDateFormat.format(calendarTwo.getTime()));
                    }
                },mYear,mMonth,mday);
        datePickerDialog.show();
    }
    private void tinhNgay(){
        int ngay = (int) (calendarTwo.getTimeInMillis()-calendarOne.getTimeInMillis())/(1000*60*60*24);
         tinhtien = ngay*v.getPrice();
        if (ngay <0){
            Toast.makeText(context, "Bạn vui lòng chọn ngày chọn ngày bạn muốn thuê trước", Toast.LENGTH_SHORT).show();
        }else {
            tvGia.setText("Tổng tiền: " + tinhtien +" VNĐ");

        }

    }

    private void dialogInsert(){
        SharedPreferences pref = getApplication().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        Toast.makeText(Thue_Xe_Activity.this, user, Toast.LENGTH_SHORT).show();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String dateTime = sdf.format(c.getTime());
        o = new Orders();
        o.setUser_id(user);
        o.setVehicle_id(v.getId());
        o.setStart_time(edtuNgay.getText().toString());
        o.setEnd_time(edDenNgay.getText().toString());
        o.setTimethuc(dateTime);

        v.setTrangThai(1);// trạng thái đặt hàng
        dao1.Update(v);

        o.setTotal(tinhtien);
        o.setPhatsinh(0);
        int kq = ordersDao.insert(o);
        if (kq >0){
            Toast.makeText(Thue_Xe_Activity.this, "Đang chờ duyệt", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Thue_Xe_Activity.this, "Không thành công", Toast.LENGTH_SHORT).show();
        }
    }

}