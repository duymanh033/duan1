package com.example.heaven_motor.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.heaven_motor.MainActivity;
import com.example.heaven_motor.R;
import com.example.heaven_motor.Tintuc.DownloadTinTuc;

public class TinTucFragment extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tin_tuc, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa();
        load_tintuc_listview();

    }
    public void load_tintuc_listview(){
        String urlRss = "https://vnexpress.net/rss/oto-xe-may.rss";
        Log.d("zzzz", urlRss);

        DownloadTinTuc downloadTinTuc = new DownloadTinTuc(TinTucFragment.this);
        downloadTinTuc.execute(urlRss);
    }

    public void anhxa(){
    }
}