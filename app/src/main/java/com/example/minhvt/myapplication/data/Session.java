package com.example.minhvt.myapplication.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "session")
public class Session {
    @PrimaryKey(autoGenerate = true)
    long mId;
    Date mDate;
    float mDistance;
    float mSpeed;
    public Session(long id, float distance, float speed, Date date){
        this.mId = id;
        this.mDistance = distance;
        this.mSpeed = speed;
        this.mDate = date;
    }

    void test(){
        int x = 0;
    }

}
