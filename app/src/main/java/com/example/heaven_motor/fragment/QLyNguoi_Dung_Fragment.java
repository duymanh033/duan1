package com.example.heaven_motor.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heaven_motor.Login_MainActivity2;
import com.example.heaven_motor.MainActivity;
import com.example.heaven_motor.R;
import com.example.heaven_motor.Sign_Up.Sign_up_MainActivity2;
import com.example.heaven_motor.adapter.UserAdapter;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QLyNguoi_Dung_Fragment extends Fragment {

    RecyclerView rvUser;
    UserDAO userDAO;
    FloatingActionButton fab;
    ArrayList<Users> list = new ArrayList<>();
    UserAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_ly_nguoi_dung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvUser = view.findViewById(R.id.rcv_User);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rvUser.setLayoutManager(layoutManager);

        userDAO = new UserDAO(getContext());
        list = (ArrayList<Users>) userDAO.getAll();

        adapter = new UserAdapter(getContext(), list);
        rvUser.setAdapter(adapter);

    }
//    public void xoa(final String Id) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Delete");
//        builder.setMessage("Bạn có muốn xóa không");
//        builder.setCancelable(true);
//
//        builder.setPositiveButton("Xóa",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        //userDAO.delete(Id);
//                        capnhatLv();
//                        dialog.cancel();
//                    }
//                });
//        builder.setNegativeButton("Hủy",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        builder.show();
//    }
    void capnhatLv(){
        list = (ArrayList<Users>) userDAO.getAll();
        adapter = new UserAdapter(getContext(), list);
        rvUser.setAdapter(adapter);
    }
    public void capnhatLv2(){
        list = (ArrayList<Users>) userDAO.getAll();
        adapter = new UserAdapter(getContext(), list);
        rvUser.setAdapter(adapter);
    }
}