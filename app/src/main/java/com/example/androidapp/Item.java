package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Item extends AppCompatActivity {

    private TextView item_agency_type;
    private TextView item_agency_name;
    private TextView item_nowstate_btn;
    private TextView item_agency_nowTime;
    private TextView item_agency_futureTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

//        获取 控件 修改信息
        item_agency_type = findViewById(R.id.item_agency_type);
        item_agency_name = findViewById(R.id.item_agency_name);
        item_nowstate_btn = findViewById(R.id.item_agency_nowstate);
        item_agency_nowTime = findViewById(R.id.item_agency_nowTime);
        item_agency_futureTime = findViewById(R.id.item_agency_futureTime);
         Thread setBg =  new Thread(new Runnable() {
            @Override
            public void run() {
                //        监听 内容 设置背景
                if(item_agency_type.getText().toString().equals("学习")){
                    item_agency_type.setBackground(getResources().getDrawable(R.drawable.item_study_bg));
                }else if(item_agency_type.getText().toString().equals("旅游")){
                    item_agency_type.setBackground(getResources().getDrawable(R.drawable.item_travel_bg));
                }else {
                    item_agency_type.setBackground(getResources().getDrawable(R.drawable.item_wordk_bg));
                }
                if(item_nowstate_btn.getText().toString().equals("完成")){
                    item_agency_nowTime.setBackground(getResources().getDrawable(R.drawable.item_ok_bg));
                } else {
                    item_agency_nowTime.setBackground(getResources().getDrawable(R.drawable.item_nev_bg));
                }
            Log.e("item", "onCreate: " );
            }
        });
        try {
            Thread.sleep(2000);
            setBg.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        item_nowstate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}