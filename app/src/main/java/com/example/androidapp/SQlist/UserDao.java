package com.example.androidapp.SQlist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User... users);
    @Update
    void updateUser(User... users);
    @Delete
    void deleteUser(User... users);
    @Query("SELECT * FROM User ORDER BY ID DESC")
    List<User> getAllUser();
}
