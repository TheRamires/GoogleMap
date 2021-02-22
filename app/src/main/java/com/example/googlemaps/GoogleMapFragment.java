package com.example.googlemaps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.googlemaps.databinding.FragmentGoogleMapBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.googlemaps.MainActivity.LOG;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGoogleMapBinding binding=FragmentGoogleMapBinding.inflate(inflater,container,false);
        binding.setFragment(this);

        initializeMap();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initializeMap() {
        if (mMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);

        }
    }
    private void init() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(LOG, "onMapClick: " + latLng.latitude + "," + latLng.longitude);
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.d(LOG, "onMapLongClick: " + latLng.latitude + "," + latLng.longitude);
            }
        });

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition camera) {
                Log.d(LOG, "onCameraChange: " + camera.target.latitude + "," + camera.target.longitude);
            }
        });
    }

    public void onClickTest(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL  );
        init();
/*
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setCompassEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setZoomControlsEnabled(false);
 */

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(45.03686300387497,38.97431217133999))
                .zoom(18)
                // .bearing(45)
                .tilt(20)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(cameraUpdate);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.03686300387497,38.97431217133999))
                .anchor(0.5f,1)
                .flat(true)
                .title("Рис Красная"));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.06272398515516,38.961551897227764))
                .anchor(0.5f,1)
                .flat(true)
                .title("Рис ФМР"));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.030886534863406,38.910713978111744))
                .anchor(0.5f,1)
                .flat(true)
                .title("Рис ЮМР"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //setUpMap();

    }
}