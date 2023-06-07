package com.example.heaven_motor.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.fragment.DatHang_Fragment;
import com.example.heaven_motor.fragment.HomeFragment;
import com.example.heaven_motor.fragment.Thue_Xe_Activity;
import com.example.heaven_motor.fragment.TinTucFragment;
import com.example.heaven_motor.model.Categoris;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Vehicle;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DatHangAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    List<Vehicle> mList;
    ImageView img;
    TextView tvName,tvGia,tvNam,tvtt,tvHang;
    CardView card;
    Button btnCT;
    DatHang_Fragment datHang_fragment;

    public DatHangAdapter(Context context, DatHang_Fragment datHang_fragment, List<Vehicle> mList) {
        this.context = context;
        this.mList = mList;
        this.datHang_fragment = datHang_fragment;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_dat_hang,null);
        }


        final Vehicle v = mList.get(position);
        if (convertView != null){
            img = convertView.findViewById(R.id.item_dat_hang_img);
            tvName = convertView.findViewById(R.id.item_dat_hang_tvName);
            tvGia = convertView.findViewById(R.id.item_dat_hang_tvGia);
            tvNam = convertView.findViewById(R.id.item_dat_hang_tvnam);
            tvtt = convertView.findViewById(R.id.item_dat_hang_tvtinhtrang);
            tvHang= convertView.findViewById(R.id.item_dat_hang_tvHang);
            btnCT = convertView.findViewById(R.id.item_dat_hang_btnCT);
            card = convertView.findViewById(R.id.item_dat_hang_card);

            final byte[] xeImg = v.getImg();
            Bitmap bitmap = BitmapFactory.decodeByteArray(xeImg,0,xeImg.length);
            img.setImageBitmap(bitmap);
            tvName.setText(v.getName() + " "+ v.getCapacity());
            tvGia.setText(v.getPrice() +"VND/Ngày");
            tvNam.setText("Date: "+v.getYear());
            tvtt.setText("Tình Trạng: "+ v.getStatus() +"%");
            tvHang.setText("Hãng: "+v.getBrand());

            btnCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Thue_Xe_Activity.class);
                    intent.putExtra("id",v.getId());
                    context.startActivity(intent);
                }
            });
//            card.setEnabled(false);
//            card.setBackgroundColor(Color.GRAY);

        }
        return convertView;
    }


}
