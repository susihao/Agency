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

public class Login extends AppCompatActivity {

    private EditText userName_Et;
    private EditText userPassword_Et;
    private TextView login_btn;
    private TextView cancle_btn;
    private TextView register_btn;

//    数据的 user 数据
    private String userName;
    private String userPassWord;

//    已经 注册的 user 数据
    private String name;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName_Et = findViewById(R.id.userName_Et);
        userPassword_Et = findViewById(R.id.userPassword_Et);
        login_btn = findViewById(R.id.login_btn);
        cancle_btn = findViewById(R.id.cancle_btn);
        register_btn = findViewById(R.id.register_btn);


//        获取 注册数据
        getRegister();

//        TODO 判断登录
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //        获取 登录数据
                userName = userName_Et.getText().toString();
                userPassWord = userPassword_Et.getText().toString();

                if(userName.equals(name)&&userPassWord.equals(password)){
                    Toast.makeText(Login.this,"登录成功！",Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent LoginMain = new Intent(Login.this, MainActivity.class);
                    startActivity(LoginMain);
                } else {
                    Toast.makeText(getApplicationContext(),"用户名或者密码输入不正确哦！",Toast.LENGTH_LONG).show();
                }
            }
        });

//        实现 取消登录 放回Main
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCancle = new Intent(Login.this, MainActivity.class);
                startActivity(toCancle);
            }
        });

//        实现 跳转注册页面
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegister = new Intent(Login.this, Register.class);
                startActivity(toRegister);
            }
        });
    }

    //        获取 已经注册得用户
    public void getRegister(){
        SharedPreferences myUser = getSharedPreferences("myUser", MODE_PRIVATE);

        name = myUser.getString("name", "suan");
        password = myUser.getString("password", "sshlxjm");
    }
}