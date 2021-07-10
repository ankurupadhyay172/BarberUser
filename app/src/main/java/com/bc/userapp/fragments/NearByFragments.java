package com.bc.userapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bc.userapp.R;
import com.bc.userapp.adapters.TopSalonAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NearByFragments extends Fragment implements OnMapReadyCallback {


    public NearByFragments() {
        // Required empty public constructor
    }

    private GoogleMap mMap;
    LatLng origin, destination;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_near_by_fragments, container, false);


        recyclerView = view.findViewById(R.id.recyclerview);


//        TopSalonAdapter adapter1 = new TopSalonAdapter(getActivity());
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter1);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {






        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        origin = new LatLng(21.710387, 72.973798);
        destination = new LatLng(21.708015, 72.972213);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                origin).zoom(15).build();


        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_hairdresser);
        LatLng sydney = new LatLng(21.714487, 72.973698);
        MarkerOptions markerOptions = new MarkerOptions().position(sydney)
                .title("Marker in vadodara")
                .snippet("Looks Unisex\n" +
                        "Salon")
                .icon(icon);
        mMap.addMarker(markerOptions);


        BitmapDescriptor icon1 = BitmapDescriptorFactory.fromResource(R.drawable.ic_hairdresser);
        LatLng sydney1 = new LatLng(21.71115, 72.973913);
        MarkerOptions markerOptions1 = new MarkerOptions().position(sydney1)
                .title("Marker in vadodara")
                .snippet("Looks Unisex\n" +
                        "Salon")
                .icon(icon1);
        mMap.addMarker(markerOptions1);


        BitmapDescriptor icon2 = BitmapDescriptorFactory.fromResource(R.drawable.ic_hairdresser);
        LatLng sydney2 = new LatLng(21.708950, 72.972769);
        MarkerOptions markerOptions2 = new MarkerOptions().position(sydney2)
                .title("Marker in vadodara")
                .snippet("Looks Unisex\n" +
                        "Salon")
                .icon(icon2);
        mMap.addMarker(markerOptions2);

        BitmapDescriptor icon3 = BitmapDescriptorFactory.fromResource(R.drawable.ic_placeholder);
        LatLng sydney3 = new LatLng(21.711265, 72.973341);
        MarkerOptions markerOptions3 = new MarkerOptions().position(sydney3)
                .title("Marker in vadodara")
                .snippet("Looks Unisex\n" +
                        "Salon")
                .icon(icon3);
        mMap.addMarker(markerOptions3);

        BitmapDescriptor icon4 = BitmapDescriptorFactory.fromResource(R.drawable.ic_hairdresser);
        LatLng sydney4 = new LatLng(21.722503, 72.984517);
        MarkerOptions markerOptions4 = new MarkerOptions().position(sydney4)
                .title("Marker in vadodara")
                .snippet("Looks Unisex\n" +
                        "Salon")
                .icon(icon4);
        mMap.addMarker(markerOptions4);


        BitmapDescriptor icon5 = BitmapDescriptorFactory.fromResource(R.drawable.ic_hairdresser);
        LatLng sydney5 = new LatLng(21.732303, 72.986517);
        MarkerOptions markerOptions5 = new MarkerOptions().position(sydney5)
                .title("Marker in vadodara")
                .snippet("Looks Unisex\n" +
                        "Salon")
                .icon(icon5);
        mMap.addMarker(markerOptions5);

        BitmapDescriptor icon6 = BitmapDescriptorFactory.fromResource(R.drawable.ic_hairdresser);
        LatLng sydney6 = new LatLng(21.733903, 72.997517);
        MarkerOptions markerOptions6 = new MarkerOptions().position(sydney6)
                .title("Marker in vadodara")
                .snippet("Looks Unisex\n" +
                        "Salon")
                .icon(icon6);
        mMap.addMarker(markerOptions6);


//        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
//        LatLng sydney = new LatLng(22.306456, 73.179591);
//        MarkerOptions markerOptions = new MarkerOptions().position(sydney)
//                .title("Marker in Sydney")
//                .snippet("snippet snippet snippet snippet snippet...")
//                .icon(icon);
//        mMap.addMarker(markerOptions);

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//        try {
//            // Customise the styling of the base map using a JSON object defined
//            // in a raw resource file.
//            boolean success = googleMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(
//                            this, R.raw.mapstyle));
//
//            if (!success) {
//                Log.e("MapActivity", "Style parsing failed.");
//            }
//        } catch (Resources.NotFoundException e) {
//            Log.e("MapActivity", "Can't find style. Error: ", e);
//        }
    }
}