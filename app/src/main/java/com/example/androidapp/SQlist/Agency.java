package com.example.androidapp.SQlist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Agency")
public class Agency implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String agency_name;
    private String agency_nowTime;
    private String agency_futureTime;


    private String agency_okTime;
    private String agency_type;
    private String agency_nowstate;
    private String agency_context;

    public Agency(String agency_name, String agency_nowTime, String agency_futureTime, String agency_type, String agency_nowstate, String agency_context) {
        this.agency_name = agency_name;
        this.agency_nowTime = agency_nowTime;
        this.agency_futureTime = agency_futureTime;
        this.agency_type = agency_type;
        this.agency_nowstate = agency_nowstate;
        this.agency_context = agency_context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getAgency_nowTime() {
        return agency_nowTime;
    }

    public void setAgency_nowTime(String agency_nowTime) {
        this.agency_nowTime = agency_nowTime;
    }

    public String getAgency_futureTime() {
        return agency_futureTime;
    }

    public void setAgency_futureTime(String agency_futureTime) {
        this.agency_futureTime = agency_futureTime;
    }

    public String getAgency_type() {
        return agency_type;
    }

    public void setAgency_type(String agency_type) {
        this.agency_type = agency_type;
    }

    public String getAgency_nowstate() {
        return agency_nowstate;
    }

    public void setAgency_nowstate(String agency_nowstate) {
        this.agency_nowstate = agency_nowstate;
    }

    public String getAgency_context() {
        return agency_context;
    }

    public void setAgency_context(String agency_context) {
        this.agency_context = agency_context;
    }

    public String getAgency_okTime() {
        return agency_okTime;
    }

    public void setAgency_okTime(String agency_okTime) {
        this.agency_okTime = agency_okTime;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", agency_name='" + agency_name + '\'' +
                ", agency_nowTime='" + agency_nowTime + '\'' +
                ", agency_futureTime='" + agency_futureTime + '\'' +
                ", agency_type='" + agency_type + '\'' +
                ", agency_nowstate='" + agency_nowstate + '\'' +
                ", agency_context='" + agency_context + '\'' +
                '}';
    }
}
