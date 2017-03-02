package com.example.sowmya.samplewatch;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.DismissOverlayView;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends WearableActivity implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener {




        private static final LatLng KansasCity = new LatLng(38.969273, -94.584664);

        private DismissOverlayView mDismissOverlay;

        private GoogleMap mMap;

        private MapFragment mMapFragment;


    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        // Set the layout. It only contains a MapFragment and a DismissOverlay.
        setContentView(R.layout.activity_maps);
        setAmbientEnabled();
        // Retrieve the containers for the root of the layout and the map. Margins will need to be
        // set on them to account for the system window insets.
        final FrameLayout topFrameLayout = (FrameLayout) findViewById(R.id.root_container);
        final FrameLayout mapFrameLayout = (FrameLayout) findViewById(R.id.map_container);

        // Set the system view insets on the containers when they become available.
        topFrameLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                // Call through to super implementation and apply insets
                insets = topFrameLayout.onApplyWindowInsets(insets);

                FrameLayout.LayoutParams params =
                        (FrameLayout.LayoutParams) mapFrameLayout.getLayoutParams();

                // Add Wearable insets to FrameLayout container holding map as margins
                params.setMargins(
                        insets.getSystemWindowInsetLeft(),
                        insets.getSystemWindowInsetTop(),
                        insets.getSystemWindowInsetRight(),
                        insets.getSystemWindowInsetBottom());
                mapFrameLayout.setLayoutParams(params);

                return insets;
            }
        });

        // Obtain the DismissOverlayView and display the introductory help text.
       // mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
       // mDismissOverlay.setIntroText(R.string.intro_text);
        //mDismissOverlay.showIntroIfNecessary();

        // Obtain the MapFragment and set the async listener to be notified when the map is ready.
        MapFragment mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        mMapFragment.onEnterAmbient(ambientDetails);
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        mMapFragment.onExitAmbient();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLongClickListener(this);

        mMap.addMarker(new MarkerOptions().position(KansasCity)
                .title("Kansas City Holmes"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KansasCity, 10));
    }




     @Override
    public void onMapLongClick(LatLng latLng) {
        //Display the dismiss overlay with a button to exit this activity.
        mDismissOverlay.show();
   }
}
