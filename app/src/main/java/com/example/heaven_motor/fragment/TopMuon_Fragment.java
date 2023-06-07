package com.example.heaven_motor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.TopAdapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.model.Top;

import java.util.ArrayList;


public class TopMuon_Fragment extends Fragment {

    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_muon, container, false);
//        lv = v.findViewById(R.id.lvtop);
//        OrdersDao ordersDao = new OrdersDao(getActivity());
//        list = (ArrayList<Top>) ordersDao.getTop();
//        adapter = new TopAdapter(getActivity(), this, list);
//        lv.setAdapter(adapter);

        return v;
    }
}


