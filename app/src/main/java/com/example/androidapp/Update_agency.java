package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp.SQlist.Agency;
import com.example.androidapp.SQlist.DBEngine;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Update_agency extends AppCompatActivity {

    private EditText update_agency_name;
    private EditText update_futureTime_type;
    private RadioGroup RG_btn;
    private RadioGroup update_RG_type;
    private TextView UpdateCancle_btn;
    private TextView UpdateDetermine_btn;
    private TextView delete_btn;

    private Agency update_agency;

    private String name;
    private String type;
    private String futureTime;
    private String nowTimeStr;
    private String stateStr = "未完成";
    private String typeStr = "学习";

    //            实例化 数据库引擎
    DBEngine dbEngine = new DBEngine(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_agency);

//        接收 要更新的Agency
        Intent intent = getIntent();
        Bundle updateAgency_bundle = intent.getBundleExtra("updateAgency");
        update_agency = (Agency)updateAgency_bundle.getSerializable("updateAgency");

        Log.e("update_agency", "onCreate: "+update_agency );
//        获取控件
        update_agency_name = findViewById(R.id.update_agency_name);
        RG_btn = findViewById(R.id.RG_state_group);
        update_RG_type = findViewById(R.id.update_RG_type);
        update_futureTime_type = findViewById(R.id.update_futureTime_type);

        UpdateCancle_btn = findViewById(R.id.UpdateCancle_btn);
        UpdateDetermine_btn = findViewById(R.id.UpdateDetermine_btn);
        delete_btn = findViewById(R.id.delete_btn);

//        初始数据
        update_agency_name.setText(update_agency.getAgency_name());
        update_futureTime_type.setText(update_agency.getAgency_futureTime());

//        监听 states 按钮   获取 更新待办state 属性值 并转换 为字符串形式
        RG_btn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radbtn = (RadioButton) findViewById(i);
                stateStr = radbtn.getText().toString();
            }
        });

        //        监听 type 按钮   获取 更新待办 type 属性值 并转换 为字符串形式
        update_RG_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radbtn = (RadioButton) findViewById(i);
                typeStr = radbtn.getText().toString();
            }
        });

//        确定更新 按钮
        UpdateDetermine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //        实现 数据更新
                name = update_agency_name.getText().toString();
                futureTime = update_futureTime_type.getText().toString();

//                获取 当前时间
                NowDataStr();

//                更新 数据操作
                updateSQList();
            }
        });

//        返回按钮
        UpdateCancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCancle = new Intent(Update_agency.this, MainActivity.class);
                startActivity(toCancle);
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSQList();
            }
        });

    }

//    删除 数据库 操作
    public void deleteSQList(){
        dbEngine.deleteAgencies(update_agency);
        //            跳转 页面
        Intent toDetermine = new Intent(Update_agency.this, MainActivity.class);
        startActivity(toDetermine);
    }

    //    更新数据库数据 操作
    public void updateSQList(){
        if(name == null||typeStr ==null||futureTime == null||stateStr==null){
            Toast.makeText(Update_agency.this,"修改的数据不能为空哦！",Toast.LENGTH_LONG);
        } else {
            update_agency.setAgency_name(name);
            update_agency.setAgency_type(typeStr);
            update_agency.setAgency_futureTime(futureTime);
            update_agency.setAgency_nowstate(stateStr);

//            操作数据库更新数据
            dbEngine.updateAgencies(update_agency);

//            跳转 页面
            Intent toDetermine = new Intent(Update_agency.this, MainActivity.class);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(toDetermine);
        }
    }

//    获取 时间
    public void NowDataStr(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        nowTimeStr = format.format(date);
    };


}