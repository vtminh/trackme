package com.example.minhvt.myapplication.mvvm.view.maplist;

import com.google.android.gms.maps.model.LatLng;

public class NamedLocation {
    public final String name;
    public final LatLng location;

    NamedLocation(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }
}
