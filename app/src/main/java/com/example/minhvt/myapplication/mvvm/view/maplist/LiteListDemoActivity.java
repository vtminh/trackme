/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.example.minhvt.myapplication.mvvm.view.maplist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minhvt.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This shows to include a map in lite mode in a ListView.
 * Note the use of the view holder pattern with the
 * {@link com.google.android.gms.maps.OnMapReadyCallback}.
 */
public class LiteListDemoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lite_list_demo);

        mGridLayoutManager = new GridLayoutManager(this, 2);
        mLinearLayoutManager = new LinearLayoutManager(this);

        // Set up the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(new MapAdapter(LIST_LOCATIONS));
        mRecyclerView.setRecyclerListener(mRecycleListener);
    }

//    /** Create a menu to switch between Linear and Grid LayoutManager. */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.lite_list_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.layout_linear:
//                mRecyclerView.setLayoutManager(mLinearLayoutManager);
//                break;
//            case R.id.layout_grid:
//                mRecyclerView.setLayoutManager(mGridLayoutManager);
//                break;
//        }
//        return true;
//    }

    /**
     * Adapter that displays a title and {@link com.google.android.gms.maps.MapView} for each item.
     * The layout is defined in <code>lite_list_demo_row.xml</code>. It contains a MapView
     * that is programatically initialised in
     * {@link #(int, android.view.View, android.view.ViewGroup)}
     */


    /**
     * RecycleListener that completely clears the {@link com.google.android.gms.maps.GoogleMap}
     * attached to a row in the RecyclerView.
     * Sets the map type to {@link com.google.android.gms.maps.GoogleMap#MAP_TYPE_NONE} and clears
     * the map.
     */
    private RecyclerView.RecyclerListener mRecycleListener = new RecyclerView.RecyclerListener() {

        @Override
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            MapAdapter.ViewHolder mapHolder = (MapAdapter.ViewHolder) holder;
            if (mapHolder != null && mapHolder.map != null) {
                // Clear the map and free up resources by changing the map type to none.
                // Also reset the map when it gets reattached to layout, so the previous map would
                // not be displayed.
                mapHolder.map.clear();
                mapHolder.map.setMapType(GoogleMap.MAP_TYPE_NONE);
            }
        }
    };


    /**
     * A list of locations to show in this ListView.
     */
     static final NamedLocation[] LIST_LOCATIONS = new NamedLocation[]{
            new NamedLocation("Cape Town", new LatLng(-33.920455, 18.466941)),
            new NamedLocation("Beijing", new LatLng(39.937795, 116.387224)),
            new NamedLocation("Bern", new LatLng(46.948020, 7.448206)),
            new NamedLocation("Breda", new LatLng(51.589256, 4.774396)),
            new NamedLocation("Brussels", new LatLng(50.854509, 4.376678)),
            new NamedLocation("Copenhagen", new LatLng(55.679423, 12.577114)),
            new NamedLocation("Hannover", new LatLng(52.372026, 9.735672)),
            new NamedLocation("Helsinki", new LatLng(60.169653, 24.939480)),
            new NamedLocation("Hong Kong", new LatLng(22.325862, 114.165532)),
            new NamedLocation("Istanbul", new LatLng(41.034435, 28.977556)),
            new NamedLocation("Johannesburg", new LatLng(-26.202886, 28.039753)),
            new NamedLocation("Lisbon", new LatLng(38.707163, -9.135517)),
            new NamedLocation("London", new LatLng(51.500208, -0.126729)),
            new NamedLocation("Madrid", new LatLng(40.420006, -3.709924)),
            new NamedLocation("Mexico City", new LatLng(19.427050, -99.127571)),
            new NamedLocation("Moscow", new LatLng(55.750449, 37.621136)),
            new NamedLocation("New York", new LatLng(40.750580, -73.993584)),
            new NamedLocation("Oslo", new LatLng(59.910761, 10.749092)),
            new NamedLocation("Paris", new LatLng(48.859972, 2.340260)),
            new NamedLocation("Prague", new LatLng(50.087811, 14.420460)),
            new NamedLocation("Rio de Janeiro", new LatLng(-22.90187, -43.232437)),
            new NamedLocation("Rome", new LatLng(41.889998, 12.500162)),
            new NamedLocation("Sao Paolo", new LatLng(-22.863878, -43.244097)),
            new NamedLocation("Seoul", new LatLng(37.560908, 126.987705)),
            new NamedLocation("Stockholm", new LatLng(59.330650, 18.067360)),
            new NamedLocation("Sydney", new LatLng(-33.873651, 151.2068896)),
            new NamedLocation("Taipei", new LatLng(25.022112, 121.478019)),
            new NamedLocation("Tokyo", new LatLng(35.670267, 139.769955)),
            new NamedLocation("Tulsa Oklahoma", new LatLng(36.149777, -95.993398)),
            new NamedLocation("Vaduz", new LatLng(47.141076, 9.521482)),
            new NamedLocation("Vienna", new LatLng(48.209206, 16.372778)),
            new NamedLocation("Warsaw", new LatLng(52.235474, 21.004057)),
            new NamedLocation("Wellington", new LatLng(-41.286480, 174.776217)),
            new NamedLocation("Winnipeg", new LatLng(49.875832, -97.150726))
    };

}
