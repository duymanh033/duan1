package com.example.heaven_motor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.heaven_motor.Sign_Up.Chao_Sign_Up_MainActivity;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.Users;

public class Login_MainActivity2 extends AppCompatActivity {
    EditText UserName,Pass;
    Button Login;
    CheckBox checkBox;
    Users users;
    UserDAO userDAO;
    TextView tvTaoTK;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main2);

        userDAO = new UserDAO(this);
        UserName = findViewById(R.id.edUserName);
        Pass = findViewById(R.id.edPassword);
        checkBox = findViewById(R.id.checkBox);
        Login = findViewById(R.id.btnLogin);
        tvTaoTK = findViewById(R.id.tvTaoTK);


        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        UserName.setText(preferences.getString("USERNAME", ""));
        Pass.setText(preferences.getString("PASSWORD", ""));
        checkBox.setChecked(preferences.getBoolean("REMEMBER", false));
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        tvTaoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Chao_Sign_Up_MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void checkLogin() {

        String strUsername = UserName.getText().toString();
        String strPass = Pass.getText().toString();
        if (strUsername.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
        } else {
            if (userDAO.checkLogin(strUsername, strPass) > 0) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                remeberUser(strUsername, strPass, checkBox.isChecked());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", strUsername);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void remeberUser(String u, String p, boolean stastus) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!stastus) {
            editor.clear();
        } else {
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", stastus);
        }
        // lưu lại toàn bộ
        editor.commit();
    }

    @Override
    protected void onStart() {
        SharedPreferences preferences = getSharedPreferences("user_file", MODE_PRIVATE);
        String mUser = preferences.getString("USERNAME", String.valueOf(false));
        String mPass = preferences.getString("PASSWORD", String.valueOf(false));
        Boolean mBoo = preferences.getBoolean("REMEMBER", false);
        if (mBoo) {
            UserName.setText(mUser);
            Pass.setText(mPass);
            checkBox.setChecked(mBoo);
        }
        super.onStart();
    }

}