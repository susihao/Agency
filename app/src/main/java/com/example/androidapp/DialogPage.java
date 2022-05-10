package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.androidapp.SQlist.DBEngine;

public class DialogPage extends AppCompatActivity {

    private ImageView Dialog_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_page);

//        实例化 跳转事务
        Intent toMain_intent = new Intent(this, MainActivity.class);

//        启动 跳转事务
        Thread toMain = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    startActivity(toMain_intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        toMain.start();

        Dialog_view = findViewById(R.id.Dialog_View);

//        TODO      监听 点击跳转事件
//        Dialog_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toMain.stop();
//                startActivity(toMain_intent);
//            }
//        });
    }
}