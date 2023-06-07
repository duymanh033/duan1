package com.example.heaven_motor.Sign_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.heaven_motor.Login_MainActivity2;
import com.example.heaven_motor.R;

public class Chao_Sign_Up_MainActivity extends AppCompatActivity {
    TextView tvTK;
    Button btnNext;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao_sign_up_main);
        tvTK = findViewById(R.id.chao_Sign_up_tvBack);
        btnNext = findViewById(R.id.chao_Sign_up_btnNext);
        tvTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Login_MainActivity2.class);
                startActivity(intent);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Sign_up_MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}