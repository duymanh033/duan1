package com.example.heaven_motor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.PhanHoi;
import com.example.heaven_motor.model.Users;

import java.util.ArrayList;
import java.util.List;

public class PhanHoiAdapter extends BaseAdapter {
   List<Users> list;
    Context context;
    TextView kh,nd;

    public PhanHoiAdapter(List<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Users obj = list.get(position);
        return obj;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.phan_hoi_item, null);
        }
        Users obj =  list.get(position);
        if (obj!=null){

            nd=  itemView.findViewById(R.id.tvNoiDungPhanHoi);
            nd.setText("Nội dung : "+ obj.getPhanhoi());
            kh = itemView.findViewById(R.id.tvThongTinNguoiPhanHoi);
            kh.setText(""+obj.getName());
        }

        return itemView;
    }
}
