package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.logging.Handler;

public class DialogPage extends AppCompatActivity {

    private ImageView Dialog_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_page);

        Dialog_view = findViewById(R.id.Dialog_View);
//        实例化 跳转事务
        Intent toMain_intent = new Intent(this, MainActivity.class);

//        启动 跳转事务
        Thread toMain = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    startActivity(toMain_intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        toMain.start();

//         监听 点击跳转事件
        Dialog_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toMain_intent);
            }
        });
    }

}