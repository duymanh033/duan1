package com.example.heaven_motor.Tintuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.heaven_motor.R;

public class epxress extends AppCompatActivity {
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epxress);
        webView = findViewById(R.id.wb);
        Intent intent = getIntent();
        String abc = intent.getStringExtra("dataress");
        webView.loadUrl(abc);
    }
}