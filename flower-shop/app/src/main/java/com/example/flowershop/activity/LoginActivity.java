package com.example.flowershop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flowershop.R;
import com.example.flowershop.entity.User;
import com.example.flowershop.sqlite.DBUser;
import com.example.flowershop.utils.CodeUtils;
import com.example.flowershop.utils.CurrentUserUtils;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    private TextView tvCode;
    private EditText etName, etPassword, etCode;

    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();
        initView();
    }


    private void bindView() {
        btnLogin = findViewById(R.id.btn_login);
        etPassword = findViewById(R.id.et_password);
        tvCode = findViewById(R.id.tv_code);
        etName = findViewById(R.id.et_name);
        etCode = findViewById(R.id.et_code);
        btnRegister = findViewById(R.id.btn_register);
    }

    private void initView() {
        User user = CurrentUserUtils.getCurrentUser();
        if (user!=null){
            etName.setText(user.getName());
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etCode.getText().toString().equals(code)) {
                    Toast.makeText(LoginActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                String n = etName.getText().toString();
                String p = etPassword.getText().toString();
                if (DBUser.check(n, p)) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    CurrentUserUtils.setCurrentUser(DBUser.get(n));
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败，用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        code = CodeUtils.getCode();
        tvCode.setText(code);
    }
}