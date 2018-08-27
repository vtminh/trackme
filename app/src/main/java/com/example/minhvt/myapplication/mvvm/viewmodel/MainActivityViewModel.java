package com.example.minhvt.myapplication.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.example.minhvt.myapplication.data.Session;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    // Weather forecast the user is looking at
    private LiveData<List<Session>> mSessions;

    SessionRepository mRepo;

    public MainActivityViewModel(SessionRepository repository) {
        mSessions = new MutableLiveData<>();
        mRepo = repository;
        mSessions  = mRepo.getAllSessions();

    }

    public void insertForTest(){
        ArrayList<LatLng> route = new ArrayList<>();
        route.add(new LatLng(1,1));
        route.add(new LatLng(1,1.001));
        route.add(new LatLng(1,1.002));

        mRepo.insertSession(new Session(System.currentTimeMillis(),2f,3f, new Date(), route));
    }

    public LiveData<List<Session>> getSessions() {
        return mSessions;
    }

//    public void setSessions(List<Session> sessions) {
//        mSessions.postValue(sessions);
//    }
}
