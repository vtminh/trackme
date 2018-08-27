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

package com.example.minhvt.myapplication.utils;

import android.content.Context;

import com.example.minhvt.myapplication.data.SessionDb;
import com.example.minhvt.myapplication.mvvm.viewmodel.MainViewModelFactory;
import com.example.minhvt.myapplication.mvvm.viewmodel.MapListViewModelFactory;
import com.example.minhvt.myapplication.mvvm.viewmodel.MapsViewModelFactory;
import com.example.minhvt.myapplication.mvvm.viewmodel.SessionRepository;


/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    public static SessionRepository provideRepository(Context context) {
        SessionDb database = SessionDb.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();

        return SessionRepository.getInstance(database.weatherDao(), executors);
    }



//    public static DetailViewModelFactory provideDetailViewModelFactory(Context context, Date date) {
//        SunshineRepository repository = provideRepository(context.getApplicationContext());
//        return new DetailViewModelFactory(repository, date);
//    }
//
    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        SessionRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

    public static MapsViewModelFactory provideMapsActivityViewModelFactory(Context context) {
        SessionRepository repository = provideRepository(context.getApplicationContext());
        return new MapsViewModelFactory(repository);
    }

    public static MapListViewModelFactory provideMapListViewModelFactory(Context context) {
        SessionRepository repository = provideRepository(context.getApplicationContext());
        return new MapListViewModelFactory(repository);
    }

}