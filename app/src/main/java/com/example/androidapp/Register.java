package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp.SQlist.DBEngine;
import com.example.androidapp.SQlist.User;

public class Register extends AppCompatActivity {

    private EditText userName_Et;
    private EditText userPassword_Et;
    private EditText userPassword_Et_one;
    private TextView register_btn;
    private TextView cancle_btn;

    private String userName;
    private String userPassword;
    private String userPassword_one;

    private DBEngine db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName_Et = findViewById(R.id.userName_Et);
        userPassword_Et = findViewById(R.id.userPassword_Et);
        userPassword_Et_one = findViewById(R.id.userPassword_Et_one);
        register_btn = findViewById(R.id.register_btn);
        cancle_btn = findViewById(R.id.cancle_btn);

        db = new DBEngine(this);
//       实现 注册功能
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //        获取 注册数据
                userName = userName_Et.getText().toString();
                userPassword = userPassword_Et.getText().toString();
                userPassword_one = userPassword_Et_one.getText().toString();

//                情况区分
                if (!userPassword.equals(userPassword_one)) {
//                    两次密码不相同 弹框提示 并 清空
                    Toast.makeText(Register.this, "两次密码输入不能不相同哦！", Toast.LENGTH_SHORT).show();
                    userPassword_Et.setText(' ');
                    userPassword_Et_one.setText(' ');
                } else {
                    User user = new User(userName, userPassword);
//                    注册用户
                    db.insertUser(user);
                    Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_SHORT).show();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent toLogin = new Intent(Register.this, Login.class);
                    startActivity(toLogin);
                }
            }
        });

//        实现 取消注册 跳转回 Login
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(Register.this, Login.class);
                startActivity(toLogin);
            }
        });

    }
}