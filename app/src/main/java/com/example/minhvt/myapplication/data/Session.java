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
    long mId;
    Date mDate;
    float mDistance;
    float mSpeed;

    @TypeConverters(RouteConverter.class)
    List<LatLng> mRoute;
    public Session(long id, float distance, float speed, Date date, List<LatLng> route){
        this.mId = id;
        this.mDistance = distance;
        this.mSpeed = speed;
        this.mDate = date;
        this.mRoute = route;
    }

    void test(){
        int x = 0;
    }

}
