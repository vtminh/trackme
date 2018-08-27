package com.example.minhvt.myapplication.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;


@Dao
public interface SessionDao {
    @Query("SELECT * FROM session")
    LiveData< List<Session>> getAllSession();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(Session... weather);

}

