package com.example.minhvt.myapplication.mvvm.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class MapListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SessionRepository mRepository;

    public MapListViewModelFactory(SessionRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MapListViewModel(mRepository);
    }
}