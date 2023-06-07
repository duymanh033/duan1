package com.example.heaven_motor.Sign_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heaven_motor.Login_MainActivity2;
import com.example.heaven_motor.R;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.Users;

public class Sign_up_MainActivity2 extends AppCompatActivity {
    EditText edTenDN,edPassword,edName,edDate,edPhone,edCCCD,edAddress;
    Button btnSignUp;
    Users u;
    UserDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_main2);
        dao = new UserDAO(this);
        anhXa();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (checkTuoi(edDate.getText().toString())>=18){
                    try {
                        Insert();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(Sign_up_MainActivity2.this, "Tuổi của bạn chưa đủ!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void anhXa(){
        edTenDN = findViewById(R.id.Sign_up_edId);
        edPassword = findViewById(R.id.Sign_up_edPassword);
        edName = findViewById(R.id.Sign_up_edName);
        edDate = findViewById(R.id.Sign_up_edDate);
        edPhone = findViewById(R.id.Sign_up_edPhone);
        edCCCD = findViewById(R.id.Sign_up_edCCCD);
        edAddress = findViewById(R.id.Sign_up_edAddress);
        btnSignUp = findViewById(R.id.Sign_up_btnSign_up);
    }
    private void Insert(){
        u = new Users();

        u.setId(edTenDN.getText().toString());
        u.setName(edName.getText().toString());
        u.setPasswork(edPassword.getText().toString());
        u.setDate(Integer.parseInt(edDate.getText().toString()));
        u.setCCCD(edCCCD.getText().toString());
        u.setPhone(edPhone.getText().toString());
        u.setAddress(edAddress.getText().toString());

        int kq = dao.insert(u);
        if (kq >0){
            Toast.makeText(this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Login_MainActivity2.class);
            intent.putExtra("edTenDN",edTenDN.getText().toString());
            intent.putExtra("edPassword",edPassword.getText().toString());
            intent.putExtra("nameUser",edName.getText().toString());

            startActivity(intent);
        }else {
            Toast.makeText(this, "Tạo tài khoản không thành công!", Toast.LENGTH_SHORT).show();
      }
    }
    public int checkTuoi(String strDate){
        return Integer.parseInt(strDate);
    }
}