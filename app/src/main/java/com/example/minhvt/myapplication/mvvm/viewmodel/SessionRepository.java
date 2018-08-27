/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.minhvt.myapplication.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.minhvt.myapplication.data.Session;
import com.example.minhvt.myapplication.data.SessionDao;
import com.example.minhvt.myapplication.utils.AppExecutors;

import java.util.List;


public class SessionRepository {
    private static final String LOG_TAG = SessionRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static SessionRepository sInstance;
    private final SessionDao mDao;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private SessionRepository(SessionDao weatherDao,
                              AppExecutors executors) {
        mDao = weatherDao;
        mExecutors = executors;


    }

    public synchronized static SessionRepository getInstance(
            SessionDao weatherDao,
            AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SessionRepository(weatherDao,
                        executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<Session>> getAllSessions() {
        return mDao.getAllSession();
    }

    public void insertSession(Session session) {
        mExecutors.diskIO().execute(() -> {
            mDao.bulkInsert(session);
        });
    }



    /**
     * Database related operations
     **/

    /**
     * Deletes old weather data because we don't need to keep multiple days' data
     */
    private void deleteOldData() {
        // TODO Finish this method when instructed
    }

    /**
     * Checks if there are enough days of future weather for the app to display all the needed data.
     *
     * @return Whether a fetch is needed
     */
    private boolean isFetchNeeded() {
        // TODO Finish this method when instructed
        return true;
    }


}