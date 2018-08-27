package com.example.minhvt.trackme.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.minhvt.myapplication.R
import com.example.minhvt.myapplication.data.Session
import com.example.minhvt.myapplication.mvvm.viewmodel.MainActivityViewModel
import com.example.minhvt.myapplication.utils.InjectorUtils


class MainActivity : AppCompatActivity() {

    val TAG = "TAG-MainActivity";
    internal lateinit var mViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = InjectorUtils.provideMainActivityViewModelFactory(this)
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java);
        mViewModel.sessions.observe(this, object : Observer<MutableList<Session>> {
            override fun onChanged(t: MutableList<Session>?) {
                bindDataToUI(t)
            }

        })

    }

    fun bindDataToUI(sessions: MutableList<Session>?) {
        Log.i(TAG, sessions?.size.toString())
    }
}


