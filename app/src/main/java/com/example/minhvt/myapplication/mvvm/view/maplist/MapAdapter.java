package com.example.minhvt.myapplication.mvvm.view.maplist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewHolder> {


    private NamedLocation[] namedLocations;

    public MapAdapter(NamedLocation[] locations) {
        super();
        namedLocations = locations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lite_list_demo_row, parent, false));
    }

    /**
     * This function is called when the user scrolls through the screen and a new item needs
     * to be shown. So we will need to bind the holder with the details of the next item.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return namedLocations.length;
    }

    /**
     * Holder for Views used in the {@link MapAdapter}.
     * Once the  the <code>map</code> field is set, otherwise it is null.
     * When the {@link #onMapReady(com.google.android.gms.maps.GoogleMap)} callback is received and
     * the {@link com.google.android.gms.maps.GoogleMap} is ready, it stored in the {@link #map}
     * field. The map is then initialised with the NamedLocation that is stored as the tag of the
     * MapView. This ensures that the map is initialised with the latest data that it should
     * display.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

        MapView mapView;
        TextView title;
        GoogleMap map;
        View layout;

        private ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            mapView = layout.findViewById(R.id.lite_listrow_map);
            title = layout.findViewById(R.id.lite_listrow_text);
            if (mapView != null) {
                // Initialise the MapView
                mapView.onCreate(null);
                // Set the map ready callback to receive the GoogleMap object
                mapView.getMapAsync(this);
            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(layout.getContext().getApplicationContext());
            map = googleMap;
            setMapLocation();
        }

        /**
         * Displays a {@link NamedLocation} on a
         * {@link com.google.android.gms.maps.GoogleMap}.
         * Adds a marker and centers the camera on the NamedLocation with the normal map type.
         */
        private void setMapLocation() {
            if (map == null) return;

            NamedLocation data = (NamedLocation) mapView.getTag();
            if (data == null) return;

            // Add a marker for this item and set the camera
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(data.location, 13f));
            map.addMarker(new MarkerOptions().position(data.location));

            // Set the map type back to normal.
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        private void bindView(int pos) {
            NamedLocation item = namedLocations[pos];
            // Store a reference of the ViewHolder object in the layout.
            layout.setTag(this);
            // Store a reference to the item in the mapView's tag. We use it to get the
            // coordinate of a location, when setting the map location.
            mapView.setTag(item);
            setMapLocation();
            title.setText(item.name);
        }
    }
}