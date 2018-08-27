package com.example.minhvt.myapplication.mvvm.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class MapsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SessionRepository mRepository;

    public MapsViewModelFactory(SessionRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MapsActivityViewModel(mRepository);
    }
}