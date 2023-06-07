package com.example.heaven_motor.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.fragment.QLyLoaiXe_Fragment;
import com.example.heaven_motor.model.Categoris;

import java.util.ArrayList;
import java.util.List;

public class CategrisAdapter extends RecyclerView.Adapter<CategrisAdapter.CategoriViewHoder> {
    Context context;
    QLyLoaiXe_Fragment fragment;
    List<Categoris> list;
    Button btnUpdate;
    EditText edMaloai,edTenloai;
    CategorisDao dao;
    public CategrisAdapter(Context context, QLyLoaiXe_Fragment fragment, List<Categoris> list){
        this.context = context;
        this.list  = list;
        this.fragment = fragment;
    }
    @Override
    public CategoriViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row_loai_xe,parent,false);
        return new CategoriViewHoder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriViewHoder holder, int position) {
        Categoris c =list.get(position);

        holder.tvMaLoai.setText("Mã Loại: "+ list.get(position).getId());
        holder.tvTenloai.setText("Tên Loại: "+list.get(position).getName());
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.delete(String.valueOf(c.getId()));
            }
        });
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update(c);
            }
        });
    }
    @SuppressLint("MissingInflatedId")
    public void Update(Categoris c){
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_update_loai_xe,null);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(v);

        edMaloai = v.findViewById(R.id.dialog_update_lx_edmaLoai);
        edTenloai = v.findViewById(R.id.dialog_update_lx_edTenloai);
        btnUpdate = v.findViewById(R.id.dialog_update_lx_btnUpdate);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        // set kích thước dialog
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // set vị trí dialog
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        edMaloai.setEnabled(false);
        edMaloai.setText(String.valueOf(c.getId()));
        edTenloai.setText(c.getName());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new CategorisDao(context);
                c.setName(edTenloai.getText().toString());
                int kq = dao.Update(c);

                if (Validate()<0){
                    Toast.makeText(context, "Vui lòng điều đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (kq > 0) {
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                fragment.loadData();
                dialog.dismiss();

            }
        });
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class CategoriViewHoder extends RecyclerView.ViewHolder{
        TextView tvMaLoai,tvTenloai;
        ImageView imgUpdate,imgDel;
        public CategoriViewHoder(@NonNull View v) {
            super(v);
            tvMaLoai = v.findViewById(R.id.item_row_tvMaLoai);
            tvTenloai = v.findViewById(R.id.item_row_tvTenloai);
            imgUpdate = v.findViewById(R.id.item_row_imgUpdate);
            imgDel = v.findViewById(R.id.item_row_imgDelete);
        }
    }
    public int Validate(){
        int check = 1;
        if (edMaloai.getText().length() == 0|| edTenloai.getText().length()==0){
            check = -1;
        }
        return check;
    }

}
