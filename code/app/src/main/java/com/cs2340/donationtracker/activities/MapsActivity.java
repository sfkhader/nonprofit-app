package com.cs2340.donationtracker.activities;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cs2340.donationtracker.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


/**
 * Controller for the Map Display.   Mostly generated from the MapActivity wizard
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    /** holds the map object returned from Google */
    private GoogleMap mMap;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //save the map instance returned from Google
        mMap = googleMap;

        //mMap.getUiSettings().setZoomControlsEnabled(true);
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        addLocs();
        LatLng atlanta = new LatLng(33.7, -84.3);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atlanta, 10));
        //reference to our GRASP Controller interface to the model
        //final DataServiceFacade dataService = DataServiceFacade.getInstance();

        // Setting a click event handler for the map
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(LatLng latLng) {
//
//                // Creating a marker
//                MarkerOptions markerOptions = new MarkerOptions();
//
//                // Setting the position for the marker
//                markerOptions.position(latLng);
//
//                //add a new item where the touch happened, for non-hardcoded data, we would need
//                //to launch an activity with a form to enter the data.
//                dataService.addDataElement( new Location(latLng.latitude, latLng.longitude));
//
//                // Setting the title for the marker.
//                // This will be displayed on taping the marker
//                markerOptions.title(dataService.getLastElementAdded().getName());
//
//                // Animating to the touched position
//                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//
//                // Placing a marker on the touched position
//                mMap.addMarker(markerOptions);
//            }
//        });

        //get the data to display
        //List<Location> dataList = dataService.getData();

        //iterate through the list and add a pin for each element in the model
//        for (Location location : dataList) {
//            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(loc).title(location.getName()));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
//        }

        //Use a custom layout for the pin data
        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    private void addLocs() {

        mDatabase.child("locations").addListenerForSingleValueEvent(new ValueEventListener() {
            double lat;
            double lng;
            String locName;
            String number;
            LatLng locO;
            String address;
            //int i = 1;
            //String iStr;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    //iStr = Integer.toString(i);
                    lat =  Double.valueOf(Objects.requireNonNull(childSnapshot.child("latitude").getValue()).toString());
                    lng = Double.valueOf(Objects.requireNonNull(childSnapshot.child("longitude").getValue()).toString());
                    locName = Objects.requireNonNull(childSnapshot.child("name").getValue()).toString();
                    number = Objects.requireNonNull(childSnapshot.child("phone").getValue()).toString();
                    address = Objects.requireNonNull(childSnapshot.child("streetAddress").getValue().toString());
                    locO = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions()
                            .position(locO)
                            .title(locName)
                            .snippet(number + " || " + address));
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    /**
     * This class implements a custom layout for the pin
     */
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        // hook up the custom layout view in res/custom_map_pin_layout.xml
        private final View myContentsView = getLayoutInflater().inflate(R.layout.custom_map_pin_layout, null);

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = (myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = (myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }
}
