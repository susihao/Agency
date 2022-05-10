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

public class HistoryFragment extends Fragment {

    private List<Agency> OKDataAll;
    private List<Map<String,Object>> OKData = new ArrayList<>();

    private ListView OK_ls_v;

    //    实例化 控件
    View agency_study_btn;
    View agency_travel_btn;
    View agency_work_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        //    接收 处理 Main 传递 序列化数据
        handleNevData();

        OK_ls_v = v.findViewById(R.id.OK_ls_v);

        //        获取 控件
        agency_study_btn = v.findViewById(R.id.agency_study_btn);
        agency_travel_btn = v.findViewById(R.id.agency_travel_btn);
        agency_work_btn = v.findViewById(R.id.agency_work_btn);

//        数据 Map 化
        String[] from = {"agency_type","agency_name","agency_nowstate","agency_nowTime","agency_futureTime"};

        int[] to = {R.id.item_agency_type,R.id.item_agency_name,R.id.item_agency_nowstate,R.id.item_agency_nowTime,R.id.item_agency_futureTime};

        OKData.clear();

        for (int i = 0; i < OKDataAll.size(); i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("agency_type",OKDataAll.get(i).getAgency_type());
            map.put("agency_name",OKDataAll.get(i).getAgency_name());
            map.put("agency_nowstate",OKDataAll.get(i).getAgency_nowstate());
            map.put("agency_nowTime",OKDataAll.get(i).getAgency_nowTime());
            map.put("agency_futureTime",OKDataAll.get(i).getAgency_futureTime());
            OKData.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), OKData, R.layout.activity_item, from, to);

        OK_ls_v.setAdapter(simpleAdapter);

        //        点击 item 对象操作
        OK_ls_v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toUpdate = new Intent(getContext(), Update_agency.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("updateAgency",(Serializable) OKDataAll.get(i));
                toUpdate.putExtra("updateAgency",bundle);
                startActivity(toUpdate);
            }
        });

        agency_study_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OKData.clear();
                for (int i = 0; i < OKDataAll.size(); i++) {
                    if(OKDataAll.get(i).getAgency_type().equals("学习")){
                        Map<String,Object> map = new HashMap<>();
                        map.put("agency_type",OKDataAll.get(i).getAgency_type());
                        map.put("agency_name",OKDataAll.get(i).getAgency_name());
                        map.put("agency_nowstate",OKDataAll.get(i).getAgency_nowstate());
                        map.put("agency_nowTime",OKDataAll.get(i).getAgency_nowTime());
                        map.put("agency_futureTime",OKDataAll.get(i).getAgency_futureTime());
                        OKData.add(map);
                    }
                };
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), OKData, R.layout.activity_item, from, to);

                OK_ls_v.setAdapter(simpleAdapter);
            }
        });
        agency_travel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OKData.clear();
                for (int i = 0; i < OKDataAll.size(); i++) {
                    if(OKDataAll.get(i).getAgency_type().equals("游玩")){
                        Map<String,Object> map = new HashMap<>();
                        map.put("agency_type",OKDataAll.get(i).getAgency_type());
                        map.put("agency_name",OKDataAll.get(i).getAgency_name());
                        map.put("agency_nowstate",OKDataAll.get(i).getAgency_nowstate());
                        map.put("agency_nowTime",OKDataAll.get(i).getAgency_nowTime());
                        map.put("agency_futureTime",OKDataAll.get(i).getAgency_futureTime());
                        OKData.add(map);
                    }
                };
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), OKData, R.layout.activity_item, from, to);

                OK_ls_v.setAdapter(simpleAdapter);
            }
        });
        agency_work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OKData.clear();
                for (int i = 0; i < OKDataAll.size(); i++) {
                    if(OKDataAll.get(i).getAgency_type().equals("工作")){
                        Map<String,Object> map = new HashMap<>();
                        map.put("agency_type",OKDataAll.get(i).getAgency_type());
                        map.put("agency_name",OKDataAll.get(i).getAgency_name());
                        map.put("agency_nowstate",OKDataAll.get(i).getAgency_nowstate());
                        map.put("agency_nowTime",OKDataAll.get(i).getAgency_nowTime());
                        map.put("agency_futureTime",OKDataAll.get(i).getAgency_futureTime());
                        OKData.add(map);
                    }
                };
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), OKData, R.layout.activity_item, from, to);

                OK_ls_v.setAdapter(simpleAdapter);
            }
        });
        return v;
    }


    //    接收 处理 Main 传递 序列化数据
    public void handleNevData(){
        Bundle bundle = getArguments();
        OKDataAll = (List<Agency>)bundle.getSerializable("OKData");
    }
}