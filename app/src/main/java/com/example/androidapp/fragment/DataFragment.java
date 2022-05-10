package com.example.androidapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidapp.AddAgency;
import com.example.androidapp.Login;
import com.example.androidapp.R;
import com.example.androidapp.SQlist.Agency;
import com.example.androidapp.SQlist.DBEngine;

import java.util.List;

public class DataFragment extends Fragment {

//    实例化 控件
    private TextView add_data_agency_btn;
    private TextView OKNums_text;
    private ImageView User_btn;
    private TextView NevNums_text;

    private List<Agency> AllData;
    private List<Agency> NevData;
    private List<Agency> OKData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data, container, false);

//        获取 控件
        add_data_agency_btn = v.findViewById(R.id.add_data_agency_btn);
        OKNums_text = v.findViewById(R.id.OKNums_text);
        User_btn = v.findViewById(R.id.User_btn);
        NevNums_text = v.findViewById(R.id.NevNums_text);

//        实现 跳转 用户登录 页面
        User_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(getContext(), Login.class);
                startActivity(toLogin);
            }
        });

//        实现 跳转 添加待办 页面
        add_data_agency_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAdd = new Intent(v.getContext(), AddAgency.class);
                startActivity(toAdd);
            }
        });
//        接收 处理 Main 传递 序列化数据
        handleSQLData();

//        显示 待办数据
        String length_OK = String.valueOf(OKData.size());
        OKNums_text.setText(length_OK);
        String length_Nev = String.valueOf(NevData.size());
        NevNums_text.setText(length_Nev);
        return v;

    }
//    获取数据 处理数据 并 发送数据
    public void handleSQLData(){
        Bundle bundle = getArguments();
        NevData = (List<Agency>) bundle.getSerializable("NevData");
        OKData = (List<Agency>) bundle.getSerializable("OKData");
    }
    //    从SQList 中获取数据 处理数据 并 发送数据
//    public void handleSQLData(){
////        获取 数据库引擎
//        DBEngine dbEngine = new DBEngine(getContext());
////        获取 数据库数据 初始化数据
//        dbEngine.getAllAgencies();
////            休眠 3000 获取 数据
//        try {
//            Thread.sleep(3000);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        AllData = dbEngine.getAllAgenciesData();
////        数据 分类
//        for (Agency agency : AllData) {
//            if(agency.getAgency_nowstate()=="未完成"){
//                NevData.add(agency);
//            }
//            if(agency.getAgency_nowstate()=="完成"){
//                OKData.add(agency);
//            }
//        }
//
//    }
}