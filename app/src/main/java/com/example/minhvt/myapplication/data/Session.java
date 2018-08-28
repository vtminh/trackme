package com.example.minhvt.myapplication.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "session")
public class Session {
    @PrimaryKey(autoGenerate = true)
    public long mId;
    public Date mDate;
    public double mDistance; //unit: meter
    public long mTime; //unit: millisecond

    @TypeConverters(RouteConverter.class)
    public List<LatLng> mRoute;
    public Session(long id, double distance, long time,  Date date, List<LatLng> route){
        this.mId = id;
        this.mDistance = distance;
        this.mDate = date;
        this.mRoute = route;
        this.mTime = time;
    }

    void test(){
        int x = 0;
    }

}
