package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.androidapp.SQlist.Agency;
import com.example.androidapp.SQlist.DBEngine;
import com.example.androidapp.fragment.DataFragment;
import com.example.androidapp.fragment.FutureFragment;
import com.example.androidapp.fragment.HistoryFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    //    控件
    private ImageView newView_btn;
    private ImageView addView_btn;

    //    Fragment 管理器
    private FragmentManager manager;
    private FragmentTransaction transaction;

    //    Fragment 页面
    private FutureFragment futureFragment;
    private HistoryFragment historyFragment;
    private DataFragment dataFragment;

    //    登录用户数据
    private String userName;

    private List<Agency> AllData;
    private List<Agency> OKData;
    private List<Agency> NevData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        实例化 Fragment 对象
        futureFragment = new FutureFragment();
        historyFragment = new HistoryFragment();
        dataFragment = new DataFragment();

//        获取 刷新 控件
        newView_btn = findViewById(R.id.newView_btn);
        addView_btn = findViewById(R.id.addView_btn);

        userLogin();

        handleSQLData();

        toFraView(dataFragment);
        addView_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAdd = new Intent(MainActivity.this, AddAgency.class);
                startActivity(toAdd);
            }
        });
//        监听 点击刷新事件
        newView_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSQLData();
                toFraView(dataFragment);
            }
        });
    }

    //    获取 登录用户的数据
    public void userLogin() {
        SharedPreferences myUser = getSharedPreferences("myUser", MODE_PRIVATE);
        userName = myUser.getString("name", "未登录");
    }

    //    设置 点击跳转 事件
    public void Fra_btn(View view) {
        switch (view.getId()) {
            case R.id.agency_future_btn:
                toFraView(futureFragment);
                break;
            case R.id.agency_history_btn:
                toFraView(historyFragment);
                break;
            case R.id.agency_data_btn:
                toFraView(dataFragment);
                break;
        }
    }

    //    实现 使用 FragmentTransaction 实现Fragment转换
    public void toFraView(Fragment fragment) {
        //        配置 Fragment 管理器
//        1、  获取 Fragment 管理器
        FragmentManager manager = getSupportFragmentManager();
//        2、  获取 Fragment 事务（开启事务）
        FragmentTransaction transaction = manager.beginTransaction();
//        3、  动态添加 Fragment
//                参数1：容器 id
//                参数2：Fragment 对象
        transaction.replace(R.id.fragment_view, fragment);
//        4、  提交事务
        transaction.commit();
    }

    //    从SQList 中获取数据 处理数据 并 发送数据
    public void handleSQLData() {
        //        获取 数据库引擎
        DBEngine dbEngine = new DBEngine(this);

//        获取 数据库数据 初始化数据
        dbEngine.getAllAgencies();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //    接收 数据库 数据
        AllData = dbEngine.getAllAgenciesData();
        OKData = new ArrayList<>();
        NevData = new ArrayList<>();
//        数据 分类
        for (Agency agency : AllData) {
            if (agency.getAgency_nowstate().equals("完成")) {
                OKData.add(agency);
            } else {
                NevData.add(agency);
            }
        }
//        发送数据
        sendFra();

    }

    //        -> Fra 发送数据
    private void sendFra() {
        //        futureFragment
        Bundle bundleOK = new Bundle();
//        将 数据序列化 传递,在fragment 中 反序列化取出
        bundleOK.putSerializable("OKData", (Serializable) OKData);
//        向 各个 fragment 发送数据
        historyFragment.setArguments(bundleOK);
//        historyFragment
        Bundle bundleNev = new Bundle();
//        将 数据序列化 传递,在fragment 中 反序列化取出
        bundleNev.putSerializable("NevData", (Serializable) NevData);
//        向 各个 fragment 发送数据
        futureFragment.setArguments(bundleNev);
//        historyFragment
        Bundle bundleData = new Bundle();
//        将 数据序列化 传递,在fragment 中 反序列化取出
        bundleData.putSerializable("OKData", (Serializable) OKData);
        bundleData.putSerializable("NevData", (Serializable) NevData);
        bundleData.putString("UserName", userName);
//        向 各个 fragment 发送数据
        dataFragment.setArguments(bundleData);
    }
}