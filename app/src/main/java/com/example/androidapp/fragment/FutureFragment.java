package com.example.androidapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.androidapp.R;
import com.example.androidapp.SQlist.Agency;
import com.example.androidapp.SQlist.DBEngine;
import com.example.androidapp.Update_agency;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FutureFragment extends Fragment {

    private List<Agency> NevDataAll;
    private List<Map<String,Object>> NevData = new ArrayList<>();

//    实例化 控件
    View agency_study_btn;
    View agency_travel_btn;
    View agency_work_btn;

    private ListView Nev_ls_v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_future, container, false);
//    接收 处理 Main 传递 序列化数据
        handleOKData();

//        获取 控件
        agency_study_btn = v.findViewById(R.id.agency_study_btn);
        agency_travel_btn = v.findViewById(R.id.agency_travel_btn);
        agency_work_btn = v.findViewById(R.id.agency_work_btn);

        Nev_ls_v = v.findViewById(R.id.Nev_ls_v);

//        数据 Map 化
        String[] from = {"agency_type","agency_name","agency_nowstate","agency_nowTime","agency_futureTime"};

        int[] to = {R.id.item_agency_type,R.id.item_agency_name,R.id.item_agency_nowstate,R.id.item_agency_nowTime,R.id.item_agency_futureTime};

//        清口 数据
        NevData.clear();

//        初始化 数据
        for (int i = 0; i < NevDataAll.size(); i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("agency_type",NevDataAll.get(i).getAgency_type());
            map.put("agency_name",NevDataAll.get(i).getAgency_name());
            map.put("agency_nowstate",NevDataAll.get(i).getAgency_nowstate());
            map.put("agency_nowTime",NevDataAll.get(i).getAgency_nowTime());
            map.put("agency_futureTime",NevDataAll.get(i).getAgency_futureTime());
            NevData.add(map);
        }

//        实现 数据分类
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), NevData, R.layout.item_nev, from, to);

        Nev_ls_v.setAdapter(simpleAdapter);

//        点击 item 对象操作
        Nev_ls_v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toUpdate = new Intent(getContext(), Update_agency.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("updateAgency",(Serializable) NevDataAll.get(i));
                toUpdate.putExtra("updateAgency",bundle);
                startActivity(toUpdate);
            }
        });

        //    数据 分类
        agency_study_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAdapter("学习");
//                提醒 适配器 数据 更新
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), NevData, R.layout.item_nev_study, from, to);
                Nev_ls_v.setAdapter(simpleAdapter);
            }
        });
        agency_travel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAdapter("旅游");
//                提醒 适配器 数据 更新
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), NevData, R.layout.item_nev_travel, from, to);
                Nev_ls_v.setAdapter(simpleAdapter);
            }
        });
        agency_work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAdapter("工作");
//                提醒 适配器 数据 更新
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), NevData, R.layout.item_nev_work, from, to);
                Nev_ls_v.setAdapter(simpleAdapter);
            }
        });
        return v;
    }

//    适配器 数据更新
    private void updateAdapter(String type){
        NevData.clear();
        for (int i = 0; i < NevDataAll.size(); i++) {
            if(NevDataAll.get(i).getAgency_type().equals(type)){
                Map<String,Object> map = new HashMap<>();
                map.put("agency_type",NevDataAll.get(i).getAgency_type());
                map.put("agency_name",NevDataAll.get(i).getAgency_name());
                map.put("agency_nowstate",NevDataAll.get(i).getAgency_nowstate());
                map.put("agency_nowTime",NevDataAll.get(i).getAgency_nowTime());
                map.put("agency_futureTime",NevDataAll.get(i).getAgency_futureTime());
                NevData.add(map);
            }
        };
    }

//    接收 处理 Main 传递 序列化数据
    private void handleOKData(){
        Bundle bundle = getArguments();
        NevDataAll = (List<Agency>)bundle.getSerializable("NevData");
    }
}