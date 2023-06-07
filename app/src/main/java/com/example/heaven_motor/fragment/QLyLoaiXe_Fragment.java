package com.example.heaven_motor.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.CategrisAdapter;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.model.Categoris;

import java.util.ArrayList;


public class QLyLoaiXe_Fragment extends Fragment {
    RecyclerView recyclerView;
    Button btnThem, btnThem2, btnCancel;
    Dialog dialog;
    EditText edMaloai, edTenloai, edHangxe;

    ArrayList<Categoris> list;
    Categoris c;
    CategorisDao dao;
    CategrisAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_q_ly_loai_xe, container, false);
        dao = new CategorisDao(getContext());
        btnThem = v.findViewById(R.id.fragLoaiXe_btnLoaixe);
        recyclerView = v.findViewById(R.id.fragLoaiXe_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialogInsert();

            }
        });
        return v;
    }

    public void OpenDialogInsert() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_lx, null);
        dialog = new Dialog(getContext());
        dialog.setContentView(view);

        edTenloai = dialog.findViewById(R.id.dialog_add_lx_edTenloai);
        btnThem2 = dialog.findViewById(R.id.dialog_add_lx_btnThem);
        btnCancel = dialog.findViewById(R.id.dialog_add_lx_btnCancel);

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

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btnThem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Validate() < 0) {
                    Toast.makeText(getContext(), "Vui lòng điều đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    c = new Categoris();
                    c.setName(edTenloai.getText().toString());
                    int kq = dao.insert(c);
                    if (kq > 0) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                loadData();


            }
        });
        dialog.show();
    }


    public void loadData() {
        list = (ArrayList<Categoris>) dao.getAll();
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CategrisAdapter(getContext(), this, list);
        recyclerView.setAdapter(adapter);
    }

    public int Validate() {
        int check = 1;
        if (edTenloai.getText().length() == 0) {
            check = -1;
        }
        return check;
    }

    public void delete(String position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo!");
        builder.setMessage("Bạn có muốn xóa xe mã " + position + " không ? ");
        builder.setIcon(R.drawable.delete_forever_24);
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int i = dao.delete(position);
                if (i < 0) {
                    Toast.makeText(getContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                } else {
                    loadData();
                    dialog.cancel();
                    Toast.makeText(getContext(), "Xóa Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();

    }

    @Override
    public void onStart() {
        loadData();
        super.onStart();

    }

    @Override
    public void onResume() {
        onStart();
        super.onResume();
    }
}