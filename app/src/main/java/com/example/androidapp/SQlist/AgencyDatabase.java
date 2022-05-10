package com.example.androidapp.SQlist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//数据库 关联 之前的表 数据库信息
@Database(entities = {Agency.class},version = 1,exportSchema = false)
public abstract class AgencyDatabase extends RoomDatabase {

//    暴露 dao 给用户操作 数据库
    public abstract AgencyDao getAgencyDao();

//    单例模式 返回 db
    private static AgencyDatabase INSTANCE;
    static synchronized AgencyDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AgencyDatabase.class,"Agency_database")
//                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
