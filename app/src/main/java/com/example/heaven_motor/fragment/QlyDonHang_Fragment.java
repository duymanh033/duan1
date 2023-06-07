package com.example.heaven_motor.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.QLyDHAdapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.model.Orders;

import java.util.List;


public class QlyDonHang_Fragment extends Fragment {

    ListView listView;
    List<Orders> list;
    OrdersDao dao;
    QLyDHAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_qly_don_hang, container, false);
        listView = v.findViewById(R.id.frag_QLDH_lv);
        dao = new OrdersDao(getContext());
        return v;
    }
    public void loadData(){
        list = dao.getAll();
        adapter = new QLyDHAdapter(getContext(),this,list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        onStart();
    }
}