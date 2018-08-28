package com.example.minhvt.myapplication.utils;

import android.app.IntentService;
import android.content.Intent;

public class TrackService extends IntentService {

    public static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_DATA = "EXTRA_DATA";

    public TrackService(String name) {
        super(name);
    }

    public TrackService() {
        super("TrackService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        int type = intent.getIntExtra(EXTRA_TYPE, 0);
        String extraData = intent.getStringExtra(EXTRA_DATA);

        NotificationUtils.localPushNotifyV2(getApplicationContext(), message, type, extraData, cinemaName);
    }
}
