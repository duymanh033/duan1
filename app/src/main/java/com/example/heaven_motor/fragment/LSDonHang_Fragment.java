package com.example.heaven_motor.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.LSDonHangAdapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.model.Orders;

import java.util.List;


public class LSDonHang_Fragment extends Fragment {
    ListView recyclerView;
    OrdersDao dao;
    List<Orders> list;
    LinearLayoutManager linearLayoutManager;
    LSDonHangAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_l_s_don_hang, container, false);
        dao = new OrdersDao(getContext());
        recyclerView  = v.findViewById(R.id.frag_LS_thue_xe_rey);

        return v;
    }
    public void LoaData(){
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        list = (List<Orders>) dao.getDonhang(user);
        adapter = new LSDonHangAdapter(getContext(),this,list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        LoaData();
    }

    @Override
    public void onResume() {
        super.onResume();
        onStart();
    }
}