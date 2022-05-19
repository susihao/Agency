package com.example.androidapp.SQlist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase{
    //    暴露 dao 给用户操作 数据库
    public abstract UserDao getUserDao();

    //    单例模式 返回 db
    private static UserDatabase INSTANCE;
    static synchronized UserDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,"User_database")
                    .build();
        }
        return INSTANCE;
    }
}
