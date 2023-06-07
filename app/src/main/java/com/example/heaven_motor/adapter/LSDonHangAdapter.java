package com.example.heaven_motor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.fragment.LSDonHang_Fragment;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Vehicle;

import java.util.List;

public class LSDonHangAdapter extends ArrayAdapter<Orders> {
    List<Orders> list;
    Context context;
    LSDonHang_Fragment fragment;
    ImageView img;
    TextView tvTenSp,tvMa,tvGia,tvTT;
    Button btnXuly;


    public LSDonHangAdapter(@NonNull Context context, LSDonHang_Fragment fragment, List<Orders> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_l_s_thue_xe,parent,false);
        }

        Orders o = list.get(position);
        if (o != null){
            img = convertView.findViewById(R.id.item_ls_thue_xe_img);
            tvTenSp = convertView.findViewById(R.id.item_ls_thue_xe_tvTenSP);
            tvMa = convertView.findViewById(R.id.item_ls_thue_xe_tvMaxe);
            tvGia = convertView.findViewById(R.id.item_ls_thue_xe_TongTien);
            tvTT = convertView.findViewById(R.id.item_ls_thue_xe_tvTT);
            btnXuly = convertView.findViewById(R.id.item_ls_thue_xe_btnXuly);

            OrdersDao ordersDao = new OrdersDao(context);
            VehicleDAO dao = new VehicleDAO(context);
            String id = o.getVehicle_id();
            Vehicle v = dao.getID(id);

            Bitmap bitmap = BitmapFactory.decodeByteArray(v.getImg(),0,v.getImg().length);
            img.setImageBitmap(bitmap);
            tvTenSp.setText("Tên xe: "+v.getBrand() + " " + v.getName()+ " "+ v.getCapacity());
            tvMa.setText("Mã xe: "+"( "+ v.getId()+" - "+v.getBKS()+" )");


            tvGia.setText(ordersDao.getDate()+ " x " + v.getPrice() +" Tổng thanh toán: "+ o.getTotal());


            if (v.getTrangThai() == 1) {
                tvTT.setTextColor(Color.RED);
                tvTT.setText("Đang xử lý");
                btnXuly.setEnabled(false);
            } else if (v.getTrangThai() == 2) {
                btnXuly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        v.setTrangThai(3);
                        dao.Update(v);
                        tvTT.setText("Đang xử lý");
                        btnXuly.setEnabled(false);
                    }
                });
                tvTT.setTextColor(Color.RED);
                tvTT.setText("Đã nhận xe");
                btnXuly.setText("Trả xe");
                btnXuly.setEnabled(true);
            } else if (v.getTrangThai() == 4) {
                tvTT.setTextColor(Color.RED);
                tvTT.setText("Đã trả xe");
                btnXuly.setText("Đã trả xe");
                btnXuly.setEnabled(false);

            } else if (v.getTrangThai() == 0) {
                tvTT.setTextColor(Color.RED);
                tvTT.setText("Đã trả xe");
                btnXuly.setText("Đã hoàn thành");
                btnXuly.setEnabled(false);

            }
        }


        return convertView;
    }

}
