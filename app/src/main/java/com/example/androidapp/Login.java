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

import java.util.ArrayList;
import java.util.List;

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
    private User user_one = new User("suan","123456");

    private List<User> allUser = new ArrayList<>();
    private DBEngine db;
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

        db = new DBEngine(this);
//        获取 注册数据
        getRegister();

//      判断登录
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //        获取 登录数据
                userName = userName_Et.getText().toString();
                userPassWord = userPassword_Et.getText().toString();
                for (int i = 0; i < allUser.size(); i++) {
                    if (userName.equals(allUser.get(i).getUser_name()) && userPassWord.equals(allUser.get(i).getUser_password())) {

                        userLogin();
                        Toast.makeText(Login.this, "登录成功！", Toast.LENGTH_LONG).show();

                        Intent LoginMain = new Intent(Login.this, MainActivity.class);
                        startActivity(LoginMain);
                        break;
                    } else {
                        if(i == allUser.size()){
                            Toast.makeText(getApplicationContext(), "用户名或者密码输入不正确哦！", Toast.LENGTH_LONG).show();
                        }
                        continue;
                    }
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
    public void getRegister() {

        db.getAllUser();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        获取 所有已注册用户数据
        allUser = db.getAllUsers();
    }

//   通过 SharedPrefereces 记录登录用户 的数据功能
    public void userLogin(){
        SharedPreferences myUser = getSharedPreferences("myUser", MODE_PRIVATE);
        SharedPreferences.Editor edit = myUser.edit();
        edit.putString("name", userName);
        edit.commit();
    }
}