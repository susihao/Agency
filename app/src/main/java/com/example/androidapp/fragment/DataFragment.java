package com.example.androidapp.fragment;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidapp.AddAgency;
import com.example.androidapp.Login;
import com.example.androidapp.R;
import com.example.androidapp.SQlist.Agency;
import com.example.androidapp.SQlist.DBEngine;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DataFragment extends Fragment {

    //    实例化 控件
    private ImageView User_img;
    private TextView User_name;
    private TextView Login_btn;

    private TextView OKNums_text;
    private TextView NevNums_text;


    private List<Agency> NevData;
    private List<Agency> OKData;

    private String UserName;
    private int day;

    public DataFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data, container, false);

//        获取 控件
        OKNums_text = v.findViewById(R.id.OKNums_text);
        NevNums_text = v.findViewById(R.id.NevNums_text);
        User_img = v.findViewById(R.id.User_img);
        User_name = v.findViewById(R.id.User_name);
        Login_btn = v.findViewById(R.id.Login_btn);


//        实现 跳转 用户登录 页面
        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(getContext(), Login.class);
                startActivity(toLogin);
            }
        });

//        接收 处理 Main 传递 序列化数据
        handleSQLData();

//        显示 待办数据
        String length_OK = String.valueOf(OKData.size());
        OKNums_text.setText(length_OK);
        String length_Nev = String.valueOf(NevData.size());
        NevNums_text.setText(length_Nev);
        User_name.setText(UserName);
//        try {
//            havaDays();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return v;

    }

    //    获取数据 处理数据 并 发送数据
    public void handleSQLData() {
        Bundle bundle = getArguments();
        NevData = (List<Agency>) bundle.getSerializable("NevData");
        OKData = (List<Agency>) bundle.getSerializable("OKData");
        UserName = bundle.getString("UserName");
    }

//    日期处理
//    方法一、 LocalDate
//    public static void calculateTimeDifferenceByChronoUnit() {
//        LocalDate startDate = LocalDate.of(2003, Month.MAY, 9);
//        System.out.println("开始时间：" + startDate);
//
//        LocalDate endDate = LocalDate.of(2015, Month.JANUARY, 26);
//        System.out.println("结束时间：" + endDate);
//
//        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
//        System.out.println("两个时间之间的天数差为：" + daysDiff);
////  long hoursDiff = ChronoUnit.HOURS.between(startDate, endDate);  //这句会抛出异常，因为LocalDate表示的时间中不包含时分秒等信息
//    }

    //    方法二、 SimpleDateFormat
//    SimpleDateFormat
//    public void havaDays() throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date fromDate = simpleDateFormat.parse("2022-05-17");
//        Date toDate = simpleDateFormat.parse("2023-05-20");
//        long from = fromDate.getTime();
//        long to = toDate.getTime();
//        day = (int) ((to - from) / (1000 * 60 * 60 * 24));
//        Log.e("TAG", "havaDays: "+day );
//    }

    ;
}

