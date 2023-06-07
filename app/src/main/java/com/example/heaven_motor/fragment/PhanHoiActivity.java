package com.example.heaven_motor.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.PhanHoiAdapter;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.Users;

import java.util.ArrayList;
import java.util.List;

public class PhanHoiActivity extends AppCompatActivity {
    EditText editText;
    Button gui, huy;
    UserDAO userDAO;
    Users users;
    List<Users> list;
    String ten;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi);
        editText = findViewById(R.id.edPhanHoi);
        gui = findViewById(R.id.btnguiPhanHoi);
        huy = findViewById(R.id.btnHuyPhanHoi);
        userDAO = new UserDAO(getApplicationContext());
        users = new Users();



        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) {
                    Toast.makeText(PhanHoiActivity.this, "Vui lòng nhập phản hồi", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = getIntent();
                    String maUser = null;
                    if (getIntent().hasExtra("user")) {
                        maUser = intent.getStringExtra("user");
                    }
                    users.setName(maUser);
                    users.setPhanhoi(editText.getText().toString());
                    if (userDAO.insert(users) > 0) {
                        Toast.makeText(PhanHoiActivity.this, "Gửi phản hồi thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(PhanHoiActivity.this, "Gửi phàn hổi không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}