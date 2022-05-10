package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Item extends AppCompatActivity {

    private TextView item_nowstate_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

//        获取 控件 修改信息
        item_nowstate_btn = findViewById(R.id.item_agency_nowstate);

//        监听 跳转事件
        item_nowstate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}