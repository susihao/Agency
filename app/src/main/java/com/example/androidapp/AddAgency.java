package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp.SQlist.Agency;
import com.example.androidapp.SQlist.DBEngine;
import com.example.androidapp.fragment.DataFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAgency extends AppCompatActivity {

    //        实例化 控件
    private EditText add_name_eti;
    private EditText add_futureTime_eti;
    private EditText add_context_eti;
    private RadioGroup RG_state_group;
    private RadioGroup add_RG_type;

    private TextView AddCancle_btn;
    private TextView AddDetermine_btn;

    //    新待办 数据:
    private String name;
    private String futureTimeStr;
    private String typeStr = "学习";
    private String nowTimeStr;
    private String stateStr = "完成";
    private String contextStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agency);

//        获取 控件
        add_name_eti = findViewById(R.id.add_agency_name);
        add_futureTime_eti = findViewById(R.id.add_futureTime_type);
        add_context_eti = findViewById(R.id.add_agency_context);

        AddCancle_btn = findViewById(R.id.AddCancle_btn);
        AddDetermine_btn = findViewById(R.id.AddDetermine_btn);
        RG_state_group = findViewById(R.id.RG_state_group);
        add_RG_type = findViewById(R.id.add_RG_type);

        DBEngine dbEngine = new DBEngine(this);

//       确认 添加
        AddDetermine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                获取 新待办 数据:
                name = add_name_eti.getText().toString();
                futureTimeStr = add_futureTime_eti.getText().toString();
                contextStr = add_context_eti.getContext().toString();
                NowDataStr();

                if (name == null || futureTimeStr == null) {
                    Toast.makeText(AddAgency.this, "待办名 或者 预计时间不能为空哦！", Toast.LENGTH_LONG);
                } else {
//                实例化 一个 Agency 对象
                    Agency new_agency = new Agency(name, nowTimeStr, futureTimeStr, typeStr, stateStr,contextStr);
//                向 数据库 添加 对象
                    dbEngine.insertAgencies(new_agency);
//                跳转 回 数据页面
                    Intent intent = new Intent(AddAgency.this, MainActivity.class);

                    startActivity(intent);
                }

            }
        });

//        监听 type 按钮   获取 新待办type 属性值 并转换 为字符串形式
        add_RG_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radbtn = (RadioButton) findViewById(i);
                typeStr = radbtn.getText().toString();
            }
        });

//        监听 states 按钮   获取 新待办state 属性值 并转换 为字符串形式
        RG_state_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radbtn = (RadioButton) findViewById(i);
                stateStr = radbtn.getText().toString();
            }
        });

//       取消 添加 放回主页
        AddCancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAgency.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //        获取 新待办 当前时间 并转换为 字符串方式
    public void NowDataStr() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        nowTimeStr = format.format(date);
    }


}