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

package com.example.minhvt.myapplication.mvvm.view.maplist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.minhvt.myapplication.R
import com.example.minhvt.myapplication.data.Session
import com.example.minhvt.myapplication.mvvm.view.trackroute.MapsActivity
import com.example.minhvt.myapplication.mvvm.view.viewutils.VerticalSpaceItemDecoration
import com.example.minhvt.myapplication.mvvm.viewmodel.MainActivityViewModel
import com.example.minhvt.myapplication.mvvm.viewmodel.MapListViewModel
import com.example.minhvt.myapplication.utils.InjectorUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.lite_list_demo.*

/**
 * This shows to include a map in lite mode in a ListView.
 * Note the use of the view holder pattern with the
 * [com.google.android.gms.maps.OnMapReadyCallback].
 */
class LiteListDemoActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null

    private var mLinearLayoutManager: LinearLayoutManager? = null

    internal lateinit var mViewModel: MapListViewModel

    internal  var adapter = MapAdapter()


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
     * Adapter that displays a title and [com.google.android.gms.maps.MapView] for each item.
     * The layout is defined in `lite_list_demo_row.xml`. It contains a MapView
     * that is programatically initialised in
     * [.]
     */


    /**
     * RecycleListener that completely clears the [com.google.android.gms.maps.GoogleMap]
     * attached to a row in the RecyclerView.
     * Sets the map type to [com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE] and clears
     * the map.
     */
    private val mRecycleListener = RecyclerView.RecyclerListener { holder ->
        val mapHolder = holder as MapAdapter.ViewHolder
        if (mapHolder != null && mapHolder.map != null) {
            // Clear the map and free up resources by changing the map type to none.
            // Also reset the map when it gets reattached to layout, so the previous map would
            // not be displayed.
            mapHolder.map.clear()
            mapHolder.map.mapType = GoogleMap.MAP_TYPE_NONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lite_list_demo)
        mLinearLayoutManager = LinearLayoutManager(this)

        // Set up the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = mLinearLayoutManager
        mRecyclerView!!.adapter = adapter
        mRecyclerView!!.setRecyclerListener(mRecycleListener)

        val dividerHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                resources.displayMetrics).toInt()
        val dividerItemDecoration = VerticalSpaceItemDecoration(dividerHeight, true)
        mRecyclerView!!.addItemDecoration(dividerItemDecoration)

        val factory = InjectorUtils.provideMapListViewModelFactory(this)
        mViewModel = ViewModelProviders.of(this, factory).get(MapListViewModel::class.java);
        mViewModel.sessions.observe(this, object : Observer<MutableList<Session>> {
            override fun onChanged(t: MutableList<Session>?) {
                adapter.replaceData(t)
            }

        })

        ivPlay.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)

        }
    }


}
