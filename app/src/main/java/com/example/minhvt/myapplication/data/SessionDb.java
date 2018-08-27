package com.example.minhvt.myapplication.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {Session.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class SessionDb extends RoomDatabase {
    private static final String LOG_TAG = SessionDb.class.getSimpleName();
    private static final String DATABASE_NAME = "weather";
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static SessionDb sInstance;
    public static SessionDb getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        SessionDb.class, SessionDb.DATABASE_NAME).build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }
    // The associated DAOs for the database
    public abstract SessionDao weatherDao();
}