package com.example.androidapp.SQlist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AgencyDao {

    @Insert
    void insertAgencies(Agency... agencies);

    @Update
    void updateAgencies(Agency... agencies);

    @Delete
    void deleteAgencies(Agency... agencies);

    @Query("SELECT * FROM Agency ORDER BY ID DESC")
    List<Agency> getAllAgency();
}
