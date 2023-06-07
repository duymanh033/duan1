package com.example.heaven_motor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.heaven_motor.R;
import com.example.heaven_motor.model.Categoris;

import java.util.ArrayList;
import java.util.List;

public class LoaiXeSpinnerAdapter extends ArrayAdapter<Categoris> {
    private Context context;
    private ArrayList<Categoris> list;
    private TextView tvStt,tvNd;
    public LoaiXeSpinnerAdapter(@NonNull Context context,ArrayList<Categoris> list) {
        super(context, 0, list);
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if (v ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.layout_item_one_row,null);
        }
        Categoris obj  =list.get(position);
        if (obj!=null){
            tvStt = v.findViewById(R.id.tvStt);
            tvStt.setText(String.valueOf(obj.getId()));
            tvNd = v.findViewById(R.id.tvNd);
            tvNd.setText(obj.getName());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if (v ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.layout_item_one_row,null);
        }
        Categoris obj  =list.get(position);
        if (obj!=null){
            tvStt = v.findViewById(R.id.tvStt);
            tvStt.setText(String.valueOf(obj.getId()));
            tvNd = v.findViewById(R.id.tvNd);
            tvNd.setText(obj.getName());
        }
        return v;
    }
}
