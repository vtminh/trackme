package com.example.minhvt.myapplication.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.minhvt.myapplication.data.Session;
import com.example.minhvt.myapplication.mvvm.viewmodel.SessionRepository;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapListViewModel extends ViewModel {

    // Weather forecast the user is looking at
    private LiveData<List<Session>> mSessions;

    SessionRepository mRepo;

    public MapListViewModel(SessionRepository repository) {
        mSessions = new MutableLiveData<>();
        mRepo = repository;
        mSessions  = mRepo.getAllSessions();

    }


    public LiveData<List<Session>> getSessions() {
        return mSessions;
    }

//    public void setSessions(List<Session> sessions) {
//        mSessions.postValue(sessions);
//    }
}
