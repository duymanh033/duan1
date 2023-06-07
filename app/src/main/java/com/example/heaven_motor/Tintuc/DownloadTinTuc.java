package com.example.heaven_motor.Tintuc;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.fragment.TinTucFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadTinTuc extends AsyncTask<String, Void, List<TinTuc>> {
    TinTucFragment fragment = new TinTucFragment();
    Context context;
    ListView listView;

    public DownloadTinTuc(TinTucFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected List<TinTuc> doInBackground(String... strings) {

        TinTucLoader loader = new TinTucLoader();
        List<TinTuc> list = new ArrayList<TinTuc>();
        String urlRss = strings[0];
        try {
            URL url = new URL(urlRss);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                // kết nối thành công thì mới lấy luồng dữ liệu
                InputStream inputStream = urlConnection.getInputStream();
                list = loader.getTinTucList(inputStream);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    protected void onPostExecute(List<TinTuc> tinTucs) {
        super.onPostExecute(tinTucs);


//        Log.d("zzzzz1", "onPostExecute: số lượng bài viết = " + tinTucs.size());
//        for (int i = 0; i < tinTucs.size(); i++) {
//            Log.d("zzzzz", tinTucs.get(i).getTitle());
//        }
//
//        listView = fragment.findViewById(R.id.lvrss);
//        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, tinTucs);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(context, epxress.class);
//
//                intent.putExtra("dataress", tinTucs.get(position).getDescription());
//                fragment.startActivity(intent);
//            }
//        });


    }

}
